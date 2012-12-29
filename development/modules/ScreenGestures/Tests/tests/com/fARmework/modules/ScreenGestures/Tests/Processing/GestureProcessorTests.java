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
		
		@Test
		public void ProcessesSquareGesture()
		{
			ScreenGestureData gesture = new ScreenGestureData();
			
			gesture.addPoint(82.327774f, 73.4393f); 
			gesture.addPoint(85.33403f, 73.4393f); 
			gesture.addPoint(97.359085f, 71.4368f); 
			gesture.addPoint(132.43216f, 70.43555f); 
			gesture.addPoint(183.53864f, 67.43179f); 
			gesture.addPoint(228.63257f, 51.411774f); 
			gesture.addPoint(233.643f, 49.40927f); 
			gesture.addPoint(235.64719f, 49.40927f); 
			gesture.addPoint(237.65137f, 49.40927f); 
			gesture.addPoint(237.65137f, 51.411774f); 
			gesture.addPoint(237.65137f, 55.41678f); 
			gesture.addPoint(237.65137f, 57.41928f); 
			gesture.addPoint(237.65137f, 127.5069f); 
			gesture.addPoint(237.65137f, 138.52066f); 
			gesture.addPoint(237.65137f, 172.5632f); 
			gesture.addPoint(237.65137f, 177.56946f); 
			gesture.addPoint(235.64719f, 203.60202f); 
			gesture.addPoint(235.64719f, 209.60953f); 
			gesture.addPoint(235.64719f, 220.62329f); 
			gesture.addPoint(235.64719f, 232.6383f); 
			gesture.addPoint(220.61588f, 231.63705f); 
			gesture.addPoint(181.53445f, 231.63705f); 
			gesture.addPoint(112.390396f, 220.62329f); 
			gesture.addPoint(93.35074f, 220.62329f); 
			gesture.addPoint(87.33821f, 220.62329f); 
			gesture.addPoint(78.31943f, 218.62079f); 
			gesture.addPoint(78.31943f, 202.60077f); 
			gesture.addPoint(78.31943f, 196.59326f); 
			gesture.addPoint(78.31943f, 179.57196f); 
			gesture.addPoint(78.31943f, 112.48813f); 
			gesture.addPoint(78.31943f, 77.444305f); 
			gesture.addPoint(78.31943f, 55.41678f); 
			gesture.addPoint(78.31943f, 56.41803f); 
			gesture.addPoint(78.31943f, 57.41928f); 
			gesture.addPoint(78.31943f, 57.41928f);
			
			ScreenGestureProcessor processor = new ScreenGestureProcessor();
			
			Boolean[][] result = processor.getGestureGrid(gesture, 5);
			
			assertEquals(true, result[0][0]);
			assertEquals(true, result[0][1]);
			assertEquals(true, result[0][2]);
			assertEquals(true, result[0][3]);
			assertEquals(true, result[0][4]);
			assertEquals(true, result[1][0]);
			assertEquals(false, result[1][1]);
			assertEquals(false, result[1][2]);
			assertEquals(false, result[1][3]);
			assertEquals(true, result[1][4]);
			assertEquals(true, result[2][0]);
			assertEquals(false, result[2][1]);
			assertEquals(false, result[2][2]);
			assertEquals(false, result[2][3]);
			assertEquals(true, result[2][4]);
			assertEquals(true, result[3][0]);
			assertEquals(false, result[3][1]);
			assertEquals(false, result[3][2]);
			assertEquals(false, result[3][3]);
			assertEquals(true, result[3][4]);
			assertEquals(true, result[4][0]);
			assertEquals(true, result[4][1]);
			assertEquals(true, result[4][2]);
			assertEquals(true, result[4][3]);
			assertEquals(true, result[4][4]);
		}
	}
}
