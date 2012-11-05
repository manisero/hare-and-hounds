package com.fARmework.HareAndHounds.Client.RoboGuiceModules;

import com.fARmework.modules.PositionTracking.Android.Infrastructure.*;
import com.fARmework.modules.PositionTracking.Android.Logic.*;
import com.fARmework.modules.PositionTracking.Android.Logic._impl.*;
import com.google.inject.AbstractModule;

public class PositionTrackingModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ISettingsProvider.class).to(com.fARmework.HareAndHounds.Client.Infrastructure.ISettingsProvider.class);
		
		bind(ILocationManagerResolver.class).to(LocationManagerResolver.class);
		bind(IPositionProvider.class).to(PositionProvider.class);
	}
}
