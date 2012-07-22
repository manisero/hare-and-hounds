package com.fARmework.RockPaperScissors.Server;

import com.fARmework.core.data.IDataRegistry;
import com.fARmework.core.data.Impl.DataRegistry;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IDataRegistry.class).to(DataRegistry.class);
	}
}
