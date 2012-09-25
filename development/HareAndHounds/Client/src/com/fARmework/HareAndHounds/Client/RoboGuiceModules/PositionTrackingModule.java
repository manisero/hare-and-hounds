package com.fARmework.HareAndHounds.Client.RoboGuiceModules;

import com.fARmework.modules.PositionTracking.Android.ILocationManagerResolver;
import com.fARmework.modules.PositionTracking.Android.IPositionService;
import com.fARmework.modules.PositionTracking.Android._impl.LocationManagerResolver;
import com.fARmework.modules.PositionTracking.Android._impl.PositionService;
import com.google.inject.AbstractModule;

public class PositionTrackingModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(ILocationManagerResolver.class).to(LocationManagerResolver.class).asEagerSingleton();
		bind(IPositionService.class).to(PositionService.class);
	}
}
