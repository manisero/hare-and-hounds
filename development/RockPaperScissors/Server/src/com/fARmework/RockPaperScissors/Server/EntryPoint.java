package com.fARmework.RockPaperScissors.Server;

import com.fARmework.RockPaperScissors.Server.GuiceModules.*;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.GestureProcessor;
import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Server.Logic.IGameManager;
import com.fARmework.modules.ScreenGestures.Data.GestureData;
import com.fARmework.core.data.IDataRegistry;
import com.google.inject.*;

public class EntryPoint
{
	public static void main(String[] args)
	{
		// register modules
		Module coreModule = new CoreModule();
		Module logicModule = new LogicModule();
		Injector injector = Guice.createInjector(coreModule, logicModule); 
		
		// register data
		IDataRegistry dataRegistry = injector.getInstance(IDataRegistry.class);
		new com.fARmework.RockPaperScissors.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		new com.fARmework.modules.ScreenGestures.Data.DataRegistrar.DataRegistrar().registerData(dataRegistry);
		
		// register data handlers
		IConnectionHandler connectionHandler = injector.getInstance(IConnectionHandler.class);
		connectionHandler.registerHandler(GestureData.class, new GestureProcessor());
		
		// run
		IGameManager gameManager = injector.getInstance(IGameManager.class);
		gameManager.run();
	}
}
