package com.fARmework.modules.PositionTracking.Tests;

import com.fARmework.modules.PositionTracking.Tests.DirectionCalculations.*;
import com.fARmework.modules.PositionTracking.Tests.DistanceCalculations.*;

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
