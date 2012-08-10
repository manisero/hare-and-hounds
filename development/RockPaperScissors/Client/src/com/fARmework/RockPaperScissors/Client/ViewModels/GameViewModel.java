package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.IntegerObservable;
import gueei.binding.observables.StringObservable;

import android.os.Bundle;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.Impl.ContextManager.IDialogListener;
import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class GameViewModel extends ViewModel
{
	public static final String OPPONENT_NAME_KEY = GameViewModel.class.getCanonicalName() + "OPPONENT_NAME";
	
	public StringObservable playerName = new StringObservable();
	public StringObservable opponentName = new StringObservable();
	public IntegerObservable playerScore = new IntegerObservable(0);
	public IntegerObservable opponentScore = new IntegerObservable(0);
	public StringObservable playerGesture = new StringObservable();
	public StringObservable opponentGesture = new StringObservable();
	public StringObservable status = new StringObservable();
	public BooleanObservable isPlayerGestureSent = new BooleanObservable(false);
	public BooleanObservable isWaitingForOpponent = new BooleanObservable(false);
	public BooleanObservable hasGameEnded = new BooleanObservable(false);
	
	public Command sendRock = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			sendGesture(GestureType.Rock);
		}
	};
	
	public Command sendPaper = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			sendGesture(GestureType.Paper);
		}
	};
	
	public Command sendScissors = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			sendGesture(GestureType.Scissors);
		}
	};
	
	public Command sendGesture = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			// TODO: Finish implementing when gesture recognition is finished
			ConnectionManager.send(arg1[0]);
		}
	};
	
	@Inject
	protected GameViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		playerName.set(settingsProvider.getUserName());
		opponentName.set(settingsProvider.getServerAddress());
		status.set(ResourcesProvider.getString(R.string.game_chooseGesture));
		
		ConnectionManager.registerDataHandler(GameResultInfo.class, new IDataHandler<GameResultInfo>()
		{
			@Override
			public void handle(GameResultInfo data)
			{
				isWaitingForOpponent.set(false);
				hasGameEnded.set(true);
				
				playerScore.set(data.PlayerScore);
				opponentScore.set(data.OpponentScore);
				displayGesture(playerGesture, data.PlayerGesture);
				displayGesture(opponentGesture, data.OpponentGesture);
				
				String result;
				
				switch (data.GameResult)
				{
					case Victory:
						status.set(ResourcesProvider.getString(R.string.game_victory));
						result = ResourcesProvider.getString(R.string.game_victory);
						break;
					case Defeat:
						status.set(ResourcesProvider.getString(R.string.game_defeat));
						result = ResourcesProvider.getString(R.string.game_defeat);
						break;
					default:
						status.set(ResourcesProvider.getString(R.string.game_draw));
						result = ResourcesProvider.getString(R.string.game_draw);
						break;
				}
				
				ConnectionManager.registerDataHandler(NextGameInfo.class, new IDataHandler<NextGameInfo>()
				{
					@Override
					public void handle(NextGameInfo data)
					{
						if (data.WantsNextGame)
						{
							isWaitingForOpponent.set(false);
						}
					}
				});
				
				ContextManager.showYesNoDialog(
					String.format("%1$s %2$s", result, ResourcesProvider.getString(R.string.game_continue)),
					ResourcesProvider.getString(R.string.game_continue_yes),
					ResourcesProvider.getString(R.string.game_continue_no),
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							isWaitingForOpponent.set(true);
							status.set(String.format(ResourcesProvider.getString(R.string.game_waitingForOpponent), opponentName.get()));
							
							ConnectionManager.send(new NextGameInfo(true));
						}
					},
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							ConnectionManager.send(new NextGameInfo(false));
						}
					});
			}
		});
	}
	
	@Override
	public void setData(Bundle data)
	{
		opponentName.set(data.getString(OPPONENT_NAME_KEY));
	}
	
	private void sendGesture(GestureType gesture)
	{
		isPlayerGestureSent.set(true);
		isWaitingForOpponent.set(true);
		status.set(String.format(ResourcesProvider.getString(R.string.game_waitingForOpponent), opponentName.get()));
		ConnectionManager.send(new GestureInfo(gesture));
	}
	
	private void displayGesture(StringObservable target, GestureType gesture)
	{
		switch (gesture)
		{
			case Rock:
				target.set(ResourcesProvider.getString(R.string.gestures_rock));
				break;
			case Paper:
				target.set(ResourcesProvider.getString(R.string.gestures_paper));
				break;
			default:
				target.set(ResourcesProvider.getString(R.string.gestures_scissors));
				break;
		}
	}
}
