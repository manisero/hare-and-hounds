package com.fARmework.HareAndHounds.Server.GuiceModules;

import com.fARmework.modules.PositionTracking.Java.*;
import com.fARmework.modules.PositionTracking.Java._impl.*;
import com.google.inject.*;

public class PositionTrackingModule extends AbstractModule
{
	@Override
	protected void configure()
	{
		bind(IDistanceCalculator.class).to(DistanceCalculator.class);
		bind(IDirectionCalculator.class).to(DirectionCalculator.class);
	}
}
