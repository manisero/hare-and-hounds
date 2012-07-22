package com.fARmework.RockPaperScissors.Server;

import com.fARmework.RockPaperScissors.Server.GuiceModules.CoreModule;
import com.fARmework.RockPaperScissors.Server.GuiceModules.LogicModule;
import com.fARmework.RockPaperScissors.Server.Logic.IConnectionHandler;
import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.GestureProcessor;
import com.fARmework.RockPaperScissors.Server.Logic.Impl.MessageProcessor;
import com.fARmework.modules.ScreenGestures.Data.DataRegistrar;
import com.fARmework.modules.ScreenGestures.Data.GestureData;
import com.fARmework.core.data.IDataRegistry;
import com.fARmework.core.server.impl.ServerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

public class EntryPoint
{
	public static void main(String[] args) 
	{
		Module coreModule = new CoreModule();
		Module logicModule = Modules.override(new ServerModule()).with(new LogicModule());
		Injector injector = Guice.createInjector(coreModule, logicModule); 
		
		IDataRegistry dataRegistry = injector.getInstance(IDataRegistry.class);
		new DataRegistrar().registerData(dataRegistry);
		
		MessageProcessor messageProcessor = injector.getInstance(MessageProcessor.class);
		messageProcessor.registerHandler(GestureData.class, new GestureProcessor());
		
		IConnectionHandler connectionHandler = injector.getInstance(IConnectionHandler.class);
		connectionHandler.connect();
	}
}
