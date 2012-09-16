package com.fARmework.modules.PositionTracking.Tests;

import static org.junit.Assert.*;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;
import com.fARmework.modules.PositionTracking.Java._impl.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			DirectionCalculatorTests.TheCalculateDirectionMethod.class
		})
public class DirectionCalculatorTests 
{
	public static class TheCalculateDirectionMethod
	{
		@Test
		public void MatchesNorthDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData northFromCurrent = new PositionData(53.0, 21.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, northFromCurrent);
			
			assertEquals(0.0, direction, 0.0);
		}
		
		@Test
		public void MatchesSouthDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData southFromCurrent = new PositionData(51.0, 21.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, southFromCurrent);
			
			assertEquals(180.0, direction, 0.0);
		}
		
		@Test
		public void MatchesEastDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData eastFromCurrent = new PositionData(52.0, 22.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, eastFromCurrent);
			
			assertEquals(90.0, direction, 0.0);
		}
		
		@Test
		public void MatchesWestDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData westFromCurrent = new PositionData(52.0, 20.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, westFromCurrent);
			
			assertEquals(270.0, direction, 0.0);
		}
		
		@Test
		public void MatchesNorthEastDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData northEastFromCurrent = new PositionData(53.0, 22.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, northEastFromCurrent);
			
			assertEquals(45.0, direction, 0.0);
		}
		
		@Test
		public void MatchesNorthWestDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData northWestFromCurrent = new PositionData(53.0, 20.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, northWestFromCurrent);
			
			assertEquals(315.0, direction, 0.0);
		}
		
		@Test
		public void MatchesSouthEastDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData southEastFromCurrent = new PositionData(51.0, 22.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, southEastFromCurrent);
			
			assertEquals(135.0, direction, 0.0);
		}
		
		@Test 
		public void MatchesSouthWestDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData southWestFromCurrent = new PositionData(51.0, 20.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, southWestFromCurrent);
			
			assertEquals(225.0, direction, 0.0);
		}
	}
}
