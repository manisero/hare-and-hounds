package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.*;

import android.os.Bundle;
import android.view.View;

import com.fARmework.RockPaperScissors.Client.R;
import com.fARmework.RockPaperScissors.Client.Infrastructure.*;
import com.fARmework.RockPaperScissors.Client.Infrastructure.ISettingsProvider;
import com.fARmework.RockPaperScissors.Client.R.string;
import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;
import com.fARmework.core.client.Connection.*;
import com.fARmework.utils.Android.Infrastructure.*;
import com.fARmework.utils.Android.Infrastructure.IContextManager.*;
import com.fARmework.utils.Android.ViewModels.*;
import com.google.inject.Inject;

public class GameViewModel extends ViewModel
{
	public static final String OPPONENT_NAME_KEY = GameViewModel.class.getCanonicalName() + "OPPONENT_NAME";
	
	private final ISettingsProvider _settingsProvider;
	
	public StringObservable PlayerName = new StringObservable();
	public StringObservable OpponentName = new StringObservable();
	public IntegerObservable PlayerScore = new IntegerObservable(0);
	public IntegerObservable OpponentScore = new IntegerObservable(0);
	public StringObservable Status = new StringObservable();
	public BooleanObservable IsWaiting = new BooleanObservable(false);
	
	public Command SendRock = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			sendGesture(GestureType.Rock);
		}
	};
	
	public Command SendPaper = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			sendGesture(GestureType.Paper);
		}
	};
	
	public Command SendScissors = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			sendGesture(GestureType.Scissors);
		}
	};
	
	public Command SendScreenGesture = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			ConnectionManager.send(arg1[0]);
		}
	};
	
	public Command SendSpaceGesture = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			ConnectionManager.send(arg1[0]);
		}
	};
	
	@Inject
	protected GameViewModel(ISettingsProvider settingsProvider, IConnectionManager connectionManager, IContextManager contextManager)
	{
		super(connectionManager, contextManager);
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public void initialize(Bundle data)
	{
		OpponentName.set(data.getString(OPPONENT_NAME_KEY));
	}
	
	@Override
	public void onEntering()
	{
		PlayerName.set(_settingsProvider.getUserName());
		Status.set(ResourcesProvider.getString(R.string.game_chooseGesture));
		
		ConnectionManager.registerDataHandler(GameStartInfo.class, new IDataHandler<GameStartInfo>()
		{
			@Override
			public void handle(GameStartInfo data)
			{
				IsWaiting.set(false);
				Status.set(ResourcesProvider.getString(R.string.game_chooseGesture));
			}
		});
		
		ConnectionManager.registerDataHandler(GestureInfo.class, new IDataHandler<GestureInfo>()
		{
			@Override
			public void handle(GestureInfo data)
			{
				if (data.GestureType != GestureType.Unknown)
				{
					IsWaiting.set(true);
					Status.set(String.format(ResourcesProvider.getString(R.string.game_waitingForOpponent), OpponentName.get()));
				}
				else
				{
					ContextManager.showNotification(ResourcesProvider.getString(R.string.game_gestureNotRecognized));
				}
			}
		});
		
		ConnectionManager.registerDataHandler(GameResultInfo.class, new IDataHandler<GameResultInfo>()
		{
			@Override
			public void handle(GameResultInfo data)
			{
				IsWaiting.set(false);
				
				PlayerScore.set(data.PlayerScore);
				OpponentScore.set(data.OpponentScore);
				
				ContextManager.showYesNoDialog(
					String.format(ResourcesProvider.getString(R.string.game_result_pattern), ResourcesProvider.getGameResultString(data.GameResult), ResourcesProvider.getGestureString(data.PlayerGesture), OpponentName.get(), ResourcesProvider.getGestureString(data.OpponentGesture)),
					ResourcesProvider.getString(R.string.dialog_yes),
					ResourcesProvider.getString(R.string.dialog_no),
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							IsWaiting.set(true);
							Status.set(String.format(ResourcesProvider.getString(R.string.game_waitingForOpponent), OpponentName.get()));
							ConnectionManager.send(new NextGameInfo());
						}
					},
					new IDialogListener()
					{
						@Override
						public void onDialogResult()
						{
							ConnectionManager.send(new PlayerLeftInfo());
						}
					});
			}
		});
		
		ConnectionManager.registerDataHandler(GameEndInfo.class, new IDataHandler<GameEndInfo>()
		{
			@Override
			public void handle(GameEndInfo data)
			{
				ContextManager.showDialogNotification(
					String.format(ResourcesProvider.getString(string.game_opponentLeft), OpponentName.get(), PlayerName.get(),
								  PlayerScore.get(), OpponentName.get(), OpponentScore.get()),
					ResourcesProvider.getString(R.string.dialog_confirm),
					null);
			}
		});
	}
	
	@Override
	public void onLeaving()
	{
		ConnectionManager.unregisterDataHandlers(GameStartInfo.class);
		ConnectionManager.unregisterDataHandlers(GestureInfo.class);
		ConnectionManager.unregisterDataHandlers(GameResultInfo.class);
		ConnectionManager.unregisterDataHandlers(GameEndInfo.class);
	}
	
	@Override
	public void dispose()
	{
		ConnectionManager.send(new PlayerLeftInfo());
	}
	
	private void sendGesture(GestureType gesture)
	{
		IsWaiting.set(true);
		Status.set(String.format(ResourcesProvider.getString(R.string.game_waitingForOpponent), OpponentName.get()));
		ConnectionManager.send(new GestureInfo(gesture));
	}
}
