package com.fARmework.modules.PositionTracking.Tests;

import com.fARmework.modules.PositionTracking.Tests.DirectionCalculators.*;
import com.fARmework.modules.PositionTracking.Tests.DistanceCalculators.*;

import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			DirectionCalculatorTests.class,
			DistanceCalculatorTests.class
		})
public class PositionTrackingModuleTests 
{
}
