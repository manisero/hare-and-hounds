package com.fARmework.HareAndHounds.HareBot.GuiceModules;

import com.fARmework.utils.Java.*;
import com.fARmework.utils.Java._impl.*;
import com.google.inject.*;

public class UtilsModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsReader.class).to(SettingsReader.class);	
	}
}
