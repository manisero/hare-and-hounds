package com.fARmework.HareAndHounds.Server.GuiceModules;

import com.fARmework.modules.PositionTracking.Java.DirectionCalculating.*;
import com.fARmework.modules.PositionTracking.Java.DirectionCalculating._impl.*;
import com.fARmework.modules.PositionTracking.Java.DistanceCalculating.*;
import com.fARmework.modules.PositionTracking.Java.DistanceCalculating._impl.*;
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
