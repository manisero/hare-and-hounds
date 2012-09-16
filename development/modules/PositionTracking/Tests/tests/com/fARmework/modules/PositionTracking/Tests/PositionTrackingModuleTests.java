package com.fARmework.modules.PositionTracking.Tests;

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
