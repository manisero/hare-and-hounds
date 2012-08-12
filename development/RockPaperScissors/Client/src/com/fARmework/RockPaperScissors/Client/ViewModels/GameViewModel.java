package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.BooleanObservable;
import gueei.binding.observables.IntegerObservable;
import gueei.binding.observables.StringObservable;

import android.os.Bundle;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager;
import com.fARmework.RockPaperScissors.Client.Infrastructure.IContextManager.IDialogListener;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ResourcesProvider;
import com.fARmework.RockPaperScissors.Client.R.string;
import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.fARmework.modules.SpaceGestures.Android.ISpaceGestureListener;
import com.fARmework.modules.SpaceGestures.Android.SpaceGestureListener;
import com.google.inject.Inject;

public class GameViewModel extends ViewModel
{
	public static final String OPPONENT_NAME_KEY = GameViewModel.class.getCanonicalName() + "OPPONENT_NAME";
	
	private ISpaceGestureListener _spaceGestureListener = new SpaceGestureListener();
	
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
			ConnectionManager.send(arg1[0]);
		}
	};
	
	public Command startRecordingGesture = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			_spaceGestureListener.startRecording(ContextManager.getContext());
		}
	};
	
	public Command stopRecordingGesture = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			ConnectionManager.send(_spaceGestureListener.stopRecording());
		}
	};
	
	@Inject
	protected GameViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		
		playerName.set(settingsProvider.getUserName());
		opponentName.set(settingsProvider.getServerAddress());
		status.set(ResourcesProvider.getString(R.string.game_chooseGesture));
		
		ConnectionManager.registerDataHandler(GestureInfo.class, new IDataHandler<GestureInfo>()
		{
			@Override
			public void handle(GestureInfo data)
			{
				if (data.GestureType != GestureType.Unknown)
				{
					isWaiting.set(true);
					status.set(String.format(ResourcesProvider.getString(R.string.game_waitingForOpponent), opponentName.get()));
				}
				else
				{
					ContextManager.showShortNotification(ResourcesProvider.getString(R.string.game_gestureNotRecognized));
				}
			}
		});
		
		ConnectionManager.registerDataHandler(GameResultInfo.class, new IDataHandler<GameResultInfo>()
		{
			@Override
			public void handle(GameResultInfo data)
			{
				isWaiting.set(false);
				
				playerScore.set(data.PlayerScore);
				opponentScore.set(data.OpponentScore);
				
				ContextManager.showYesNoDialog(
					String.format(ResourcesProvider.getString(R.string.game_result_pattern), ResourcesProvider.getGameResultString(data.GameResult), ResourcesProvider.getGestureString(data.PlayerGesture), opponentName.get(), ResourcesProvider.getGestureString(data.OpponentGesture)),
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
		
		ConnectionManager.registerDataHandler(GameStartInfo.class, new IDataHandler<GameStartInfo>()
		{
			@Override
			public void handle(GameStartInfo data)
			{
				isWaiting.set(false);
				status.set(ResourcesProvider.getString(R.string.game_chooseGesture));
			}
		});
		
		ConnectionManager.registerDataHandler(GameEndInfo.class, new IDataHandler<GameEndInfo>()
		{
			@Override
			public void handle(GameEndInfo data)
			{
				ContextManager.showDialogNotification(
					String.format(ResourcesProvider.getString(string.game_opponentLeft), opponentName.get(), playerName.get(), playerScore.get(), opponentName.get(), opponentScore.get()),
					null);
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
}
