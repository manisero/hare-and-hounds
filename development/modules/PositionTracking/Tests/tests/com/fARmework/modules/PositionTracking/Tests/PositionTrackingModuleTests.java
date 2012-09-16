package com.fARmework.modules.PositionTracking.Tests;

import com.fARmework.modules.PositionTracking.Tests.DirectionCalculators.*;
import com.fARmework.modules.PositionTracking.Tests.DistanceCalculators.*;
import com.fARmework.modules.PositionTracking.Tests.PositionStores.*;

import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			DirectionCalculatorTests.class,
			DistanceCalculatorTests.class,
			PositionDataListTests.class
		})
public class PositionTrackingModuleTests 
{
}
