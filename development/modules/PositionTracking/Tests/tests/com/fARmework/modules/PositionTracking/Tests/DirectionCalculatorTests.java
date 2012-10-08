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
		/*
		 * 	Test data for cities taken from:
		 * 		http://www.timeanddate.com/worldclock/distance.html
		 */
		
		@Test
		public void MatchesMiamiToTokyoInitialBearing()
		{
			PositionData miami = new PositionData(25.783, -80.217);
			PositionData tokyo = new PositionData(35.683, 139.7);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(miami, tokyo);
			
			assertEquals(326.8, direction, 0.1);
		}
		
		@Test
		public void MatchesRomeToJerusalemInitialBearing()
		{
			PositionData rome = new PositionData(41.9, 12.483);
			PositionData jerusalem = new PositionData(31.783, 35.217);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(rome, jerusalem);
			
			assertEquals(111.8, direction, 0.1);
		}
		
		@Test
		public void MatchesTashkentToLusakaInitialBearing()
		{
			PositionData tashkent = new PositionData(41.267, 69.217);
			PositionData lusaka = new PositionData(-15.333, 28.233);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(tashkent, lusaka);
			
			assertEquals(223.0, direction, 0.1);			
		}
		
		@Test
		public void MatchesStockholmToBelfastInitialBearing()
		{
			PositionData stockholm = new PositionData(59.333, 18.067);
			PositionData belfast = new PositionData(54.6, -5.933);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(stockholm, belfast);
			
			assertEquals(260.5, direction, 0.1);
		}
		
		@Test
		public void MatchesNorthDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData northFromCurrent = new PositionData(53.0, 21.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, northFromCurrent);
			
			assertEquals(0.0, direction, 1.0);
		}
		
		@Test
		public void MatchesSouthDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData southFromCurrent = new PositionData(51.0, 21.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, southFromCurrent);
			
			assertEquals(180.0, direction, 1.0);
		}
		
		@Test
		public void MatchesEastDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData eastFromCurrent = new PositionData(52.0, 22.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, eastFromCurrent);
			
			assertEquals(90.0, direction, 1.0);
		}
		
		@Test
		public void MatchesWestDirection()
		{
			PositionData currentPosition = new PositionData(52.0, 21.0);
			PositionData westFromCurrent = new PositionData(52.0, 20.0);
			
			IDirectionCalculator directionCalculator = new DirectionCalculator();
			
			double direction = directionCalculator.calculateDirection(currentPosition, westFromCurrent);
			
			assertEquals(270.0, direction, 1.0);
		}
	}
}
