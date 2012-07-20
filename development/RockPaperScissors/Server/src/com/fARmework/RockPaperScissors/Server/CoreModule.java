package com.fARmework.RockPaperScissors.Server;

import com.fARmework.core.data.IDataFactory;
import com.fARmework.core.data.Impl.DataFactory;
import com.google.inject.AbstractModule;

public class CoreModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IDataFactory.class).to(DataFactory.class);
	}
}
