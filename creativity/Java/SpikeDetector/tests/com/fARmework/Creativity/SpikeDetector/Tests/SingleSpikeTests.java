package com.fARmework.Creativity.SpikeDetector.Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fARmework.Creativity.SpikeDetector.SingleSpike;

@RunWith(Suite.class)
@SuiteClasses(
		{
			SingleSpikeTests.TheAddNextPointMethod.class,
			SingleSpikeTests.TheGetRiseLengthMethod.class
		})
public class SingleSpikeTests 
{
	public static class TheAddNextPointMethod
	{
		@Test
		public void AddsNextPointOfTheSpike()
		{
			float initialPoint = 10.0f;
			
			SingleSpike singleSpike = new SingleSpike(initialPoint);
			
			float firstPoint = 20.0f;
			float secondPoint = 22.8f;
			float thirdPoint = 15.0f;
			float fourthPoint = 10.0f;
			
			boolean firstRisingPointAdded = singleSpike.addNextPoint(firstPoint);
			boolean secondRisingPointAdded = singleSpike.addNextPoint(secondPoint);
			boolean firstFallingPointAdded = singleSpike.addNextPoint(thirdPoint);
			boolean secondFallingPointAdded = singleSpike.addNextPoint(fourthPoint);
			
			assertEquals(true, firstRisingPointAdded);
			assertEquals(true, secondRisingPointAdded);
			assertEquals(true, firstFallingPointAdded);
			assertEquals(true, secondFallingPointAdded);
		}
		
		@Test
		public void StopsAddingPointsAfterSpikeIsFinished()
		{
			float initialPoint = 22.73f;
			
			SingleSpike singleSpike = new SingleSpike(initialPoint);
			
			float firstPoint = 23.631f;
			float secondPoint = 27.2912f;
			float thirdPoint = 11.08f;
			float fourthPoint = 10.2091f;
			float fifthPoint = 14.232f;
			float sixthPoint = 16.2213f;
			
			boolean firstRisingPointAdded = singleSpike.addNextPoint(firstPoint);
			boolean secondRisingPointAdded = singleSpike.addNextPoint(secondPoint);
			boolean firstFallingPointAdded = singleSpike.addNextPoint(thirdPoint);
			boolean secondFallingPointAdded = singleSpike.addNextPoint(fourthPoint);
			boolean firstRisingPointAfterSpikeAdded = singleSpike.addNextPoint(fifthPoint);
			boolean secondRisingPointAfterSpikeAdded = singleSpike.addNextPoint(sixthPoint);
			
			assertEquals(true, firstRisingPointAdded);
			assertEquals(true, secondRisingPointAdded);
			assertEquals(true, firstFallingPointAdded);
			assertEquals(true, secondFallingPointAdded);
			assertEquals(false, firstRisingPointAfterSpikeAdded);
			assertEquals(false, secondRisingPointAfterSpikeAdded);
		}
	}
	
	public static class TheGetRiseLengthMethod
	{
		@Test
		public void CalculatesRiseLength()
		{
			float initialPoint = 15.692f;
			
			SingleSpike singleSpike = new SingleSpike(initialPoint);
			
			float firstPoint = 17.8293f;
			float secondPoint = 22.2129f;
			float thirdPoint = 27.0f;
			float fourthPoint = 29.62123f;
			float fifthPoint = 18.2912f;
			float sixthPoint = 21.32354f;
			float seventhPoint = 22.421f;
			
			singleSpike.addNextPoint(firstPoint);
			singleSpike.addNextPoint(secondPoint);
			singleSpike.addNextPoint(thirdPoint);
			singleSpike.addNextPoint(fourthPoint);
			singleSpike.addNextPoint(fifthPoint);
			singleSpike.addNextPoint(sixthPoint);
			singleSpike.addNextPoint(seventhPoint);
			
			assertEquals(fourthPoint, singleSpike.getRiseLength(), 0.0f);
		}
		
		@Test
		public void CalculatesRiseLengthWithInitialLength()
		{
			float initialPoint = 2.9302387f;
			
			SingleSpike singleSpike = new SingleSpike(initialPoint);
			
			float firstPoint = 4.7900267f;
			float secondPoint = 3.563587f;
			float thirdPoint = 3.1680799f;
			
			singleSpike.addNextPoint(firstPoint);
			singleSpike.addNextPoint(secondPoint);
			singleSpike.addNextPoint(thirdPoint);
			
			assertEquals(firstPoint, singleSpike.getRiseLength(), 0.0f);
		}		
	}
}
