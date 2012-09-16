package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Server.Logic.IGameManager;
import com.fARmework.HareAndHounds.Server.Logic.IGameManagerFactory;

public class GameManagerFactory implements IGameManagerFactory
{
	@Override
	public IGameManager create()
	{
		return new GameManager();
	}
}
