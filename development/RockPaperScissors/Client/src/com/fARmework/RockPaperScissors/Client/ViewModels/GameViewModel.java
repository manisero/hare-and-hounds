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
import com.fARmework.RockPaperScissors.Data.GameResultInfo.GameResult;
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
	public StringObservable status = new StringObservable();
	public BooleanObservable isWaiting = new BooleanObservable(false);
	
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
				isWaiting.set(false);
				
				playerScore.set(data.PlayerScore);
				opponentScore.set(data.OpponentScore);
				
				String result = getResultString(data.GameResult);
				status.set(result);
				
				ConnectionManager.registerDataHandler(NextGameInfo.class, new IDataHandler<NextGameInfo>()
				{
					@Override
					public void handle(NextGameInfo data)
					{
						if (data.WantsNextGame)
						{
							isWaiting.set(false);
						}
					}
				});
				
				ContextManager.showYesNoDialog(
					String.format(ResourcesProvider.getString(R.string.game_result_pattern), result, getGestureString(data.PlayerGesture), opponentName.get(), getGestureString(data.OpponentGesture)),
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							isWaiting.set(true);
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
		isWaiting.set(true);
		status.set(String.format(ResourcesProvider.getString(R.string.game_waitingForOpponent), opponentName.get()));
		ConnectionManager.send(new GestureInfo(gesture));
	}
	
	private String getResultString(GameResult result)
	{
		switch (result)
		{
			case Victory:
				return ResourcesProvider.getString(R.string.game_victory);
			case Defeat:
				return ResourcesProvider.getString(R.string.game_defeat);
			default:
				return ResourcesProvider.getString(R.string.game_draw);
		}
	}
	
	private String getGestureString(GestureType gesture)
	{
		switch (gesture)
		{
			case Rock:
				return ResourcesProvider.getString(R.string.gestures_rock);
			case Paper:
				return ResourcesProvider.getString(R.string.gestures_paper);
			default:
				return ResourcesProvider.getString(R.string.gestures_scissors);
		}
	}
}
