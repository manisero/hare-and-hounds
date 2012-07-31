package com.fARmework.RockPaperScissors.Client.ViewModels;

import gueei.binding.Command;
import gueei.binding.observables.StringObservable;

import android.view.View;

import com.fARmework.RockPaperScissors.Client.Infrastructure.INavigationManager;
import com.fARmework.RockPaperScissors.Data.*;
import com.fARmework.RockPaperScissors.Data.GestureData.GestureType;
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
			ConnectionManager.send(new GestureData(GestureType.Rock));
		}
	};
	
	public Command sendPaper = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set("Waiting for the other player...");
			ConnectionManager.send(new GestureData(GestureType.Paper));
		}
	};
	
	public Command sendScissors = new Command()
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			status.set("Waiting for the other player...");
			ConnectionManager.send(new GestureData(GestureType.Scissors));
		}
	};
	
	@Inject
	protected GameViewModel(IConnectionManager connectionManager, INavigationManager navigationManager)
	{
		super(connectionManager, navigationManager);
		
		ConnectionManager.registerDataHandler(VictoryInfo.class, new IDataHandler<VictoryInfo>()
		{
			@Override
			public void handle(VictoryInfo data)
			{
				status.set("victory");
			}
		});
		
		ConnectionManager.registerDataHandler(DefeatInfo.class, new IDataHandler<DefeatInfo>()
		{
			@Override
			public void handle(DefeatInfo data)
			{
				status.set("defeat");
			}
		});
		
		ConnectionManager.registerDataHandler(DrawInfo.class, new IDataHandler<DrawInfo>()
		{
			@Override
			public void handle(DrawInfo data)
			{
				status.set("draw");
			}
		});
	}
}
