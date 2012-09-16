package com.fARmework.modules.PositionTracking.Tests.DistanceCalculators;

import static org.junit.Assert.*;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.DistanceCalculating.*;
import com.fARmework.modules.PositionTracking.Java.DistanceCalculating._impl.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			DistanceCalculatorTests.TheCalculateDistanceMethod.class
		})
public class DistanceCalculatorTests 
{
	public static class TheCalculateDistanceMethod
	{
		@Test
		public void MeasuresOneDegreeLength()
		{
			final double degreeLength = 111319.4;
			
			PositionData firstPosition = new PositionData(0.0, 179.0);
			PositionData secondPosition = new PositionData(0.0, 180.0);
			
			IDistanceCalculator distanceCalculator = new DistanceCalculator();
			
			double distance = distanceCalculator.calculateDistance(firstPosition, secondPosition);
			
			assertEquals(degreeLength, distance, 0.1);
		}
		
		@Test
		public void MeasuresEquatorLength()
		{
			final double equatorLength = 40075016.6;
			
			PositionData firstPosition = new PositionData(0.0, -180.0);
			PositionData secondPosition = new PositionData(0.0, 180.0);
			
			IDistanceCalculator distanceCalculator = new DistanceCalculator();
			
			double distance = distanceCalculator.calculateDistance(firstPosition, secondPosition);
			
			assertEquals(equatorLength, distance, 0.1);
		}
	}
}
