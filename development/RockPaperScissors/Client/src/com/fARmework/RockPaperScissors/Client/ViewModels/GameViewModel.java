package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;

import android.view.View;

import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GestureInfo.GestureType;
import com.fARmework.core.client.Connection.IConnectionManager;
import com.fARmework.core.client.Connection.IDataHandler;
import com.google.inject.Inject;

public class GameViewModel extends ViewModel
{
	public StringObservable status = new StringObservable();
	
	public Command sendRock = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set("Waiting for the other player...");
			ConnectionManager.send(new GestureInfo(GestureType.Rock));
		}
	};
	
	public Command sendPaper = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set("Waiting for the other player...");
			ConnectionManager.send(new GestureInfo(GestureType.Paper));
		}
	};
	
	public Command sendScissors = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set("Waiting for the other player...");
			ConnectionManager.send(new GestureInfo(GestureType.Scissors));
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
	
	@Inject
	protected GameViewModel(IConnectionManager connectionManager, INavigationManager navigationManager)
	{
		super(connectionManager, navigationManager);
		
		ConnectionManager.registerDataHandler(GameResultInfo.class, new IDataHandler<GameResultInfo>()
		{
			@Override
			public void handle(GameResultInfo data)
			{
				switch (data.GameResult)
				{
					case Victory:
						status.set("victory");
						break;
					case Defeat:
						status.set("defeat");
						break;
					default:
						status.set("draw");
						break;
				}
				
			}
		});
	}
}
