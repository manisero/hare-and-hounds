package com.fARmework.RockPaperScissors.Server.GuiceModules;

import com.fARmework.core.data.IDataRegistry;
import com.fARmework.core.data.IDataService;
import com.fARmework.core.data.Impl.DataRegistry;
import com.fARmework.core.data.Impl.DataService;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IDataRegistry.class).to(DataRegistry.class);
		bind(IDataService.class).to(DataService.class);
	}
}
