package com.fARmework.modules.ScreenGestures.Tests.Processing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;
import com.fARmework.modules.ScreenGestures.Java.Processing._impl.ScreenGestureProcessor;

@RunWith(Suite.class)
@SuiteClasses(
		{
			GestureProcessorTests.TheGetGestureGridMethod.class
		})
public class GestureProcessorTests
{
	public static class TheGetGestureGridMethod
	{
		@Test
		public void ProcessesDoubleSquareGesture()
		{
			ScreenGestureData gesture = new ScreenGestureData();
			
			gesture.addPoint(0, 0);
			gesture.addPoint(30, 0);
			gesture.addPoint(25, 15);
			gesture.addPoint(25, 30);
			gesture.addPoint(5, 25);
			gesture.addPoint(0, 15);
			gesture.addPoint(0, 0);
			gesture.addPoint(0, 0);
			gesture.addPoint(0, 15);
			gesture.addPoint(5, 25);
			gesture.addPoint(25, 30);
			gesture.addPoint(25, 15);
			gesture.addPoint(30, 0);
			gesture.addPoint(0, 0);
			
			ScreenGestureProcessor processor = new ScreenGestureProcessor();
			
			Boolean[][] result = processor.getGestureGrid(gesture, 3);

			assertEquals(true, result[0][0]);
			assertEquals(true, result[0][1]);
			assertEquals(true, result[0][2]);
			assertEquals(true, result[1][0]);
			assertEquals(false, result[1][1]);
			assertEquals(true, result[1][2]);
			assertEquals(true, result[2][0]);
			assertEquals(true, result[2][1]);
			assertEquals(true, result[2][2]);
		}
	}
}
