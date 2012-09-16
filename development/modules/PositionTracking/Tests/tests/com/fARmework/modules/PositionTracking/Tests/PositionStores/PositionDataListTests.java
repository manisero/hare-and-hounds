package com.fARmework.modules.PositionTracking.Tests.PositionStores;

import static org.junit.Assert.*;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.DistanceCalculating.*;
import com.fARmework.modules.PositionTracking.Java.PositionStoring.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			PositionDataListTests.TheIsInPositionRadiusMethod.class,
			PositionDataListTests.TheGetNextPositionMethod.class
		})
public class PositionDataListTests 
{
	public static class TheIsInPositionRadiusMethod
	{
		@Test
		public void FindsPointsInPositionRadius()
		{
			IDistanceCalculator distanceCalculator = new IDistanceCalculator() 
			{		
				@Override
				public double calculateDistance(PositionData first, PositionData second) 
				{
					if(first.Latitude == second.Latitude && first.Longitude == second.Longitude)
					{
						return 0.0;
					}
					
					return 10000.0;
				}
			};
			
			PositionDataList dataList = new PositionDataList(distanceCalculator);
			
			dataList.add(new PositionData(52.642, 21.248));
			dataList.add(new PositionData(58.232, 22.4523));
			dataList.add(new PositionData(63.2712, 85.2732));
			dataList.add(new PositionData(52.74552, 21.27452));
			dataList.add(new PositionData(54.97261, 20.92621));
			
			assertFalse(dataList.isInPositionRadius(new PositionData(51.0, 21.0), 1.0));
			assertFalse(dataList.isInPositionRadius(new PositionData(100.0, 100.0), 12.0));
			assertTrue(dataList.isInPositionRadius(new PositionData(63.2712, 85.2732), 1.0));
			assertTrue(dataList.isInPositionRadius(new PositionData(52.642, 21.248), 1.0));
		}
	}
	
	public static class TheGetNextPositionMethod
	{
		@Test
		public void ReturnsNullIfNextDoesNotExists()
		{
			IDistanceCalculator distanceCalculator = new IDistanceCalculator() 
			{	
				@Override
				public double calculateDistance(PositionData first, PositionData second) 
				{
					return 5.0;
				}
			};
			
			PositionDataList dataList = new PositionDataList(distanceCalculator);
			
			dataList.add(new PositionData(52.8271, 21.2872));
			dataList.add(new PositionData(51.271, 22.0));
			dataList.add(new PositionData(52.1623, 21.2721));
			
			assertNull(dataList.getNextPosition(new PositionData(10.0, 10.0), 1.0));
			assertNull(dataList.getNextPosition(new PositionData(51.0, 21.0), 2.0));
			assertNull(dataList.getNextPosition(new PositionData(52.8271, 21.2872), 3.0));
		}
		
		@Test
		public void ReturnsNextPosition()
		{
			IDistanceCalculator distanceCalculator = new IDistanceCalculator() 
			{	
				@Override
				public double calculateDistance(PositionData first, PositionData second) 
				{
					if(first.Latitude == second.Latitude && first.Longitude == second.Longitude)
					{
						return 0.0;
					}
					
					return 10000.0;
				}
			};
			
			PositionDataList dataList = new PositionDataList(distanceCalculator);
			
			dataList.add(new PositionData(52.0, 21.0));
			dataList.add(new PositionData(52.182, 22.1271));
			dataList.add(new PositionData(50.2871, 17.2812));
			dataList.add(new PositionData(51.2812, 12.2712));
			
			PositionData first = dataList.getNextPosition(new PositionData(52.0, 21.0), 1.0);
			PositionData second = dataList.getNextPosition(new PositionData(50.2871, 17.2812), 1.0);
			PositionData third = dataList.getNextPosition(new PositionData(52.182, 22.1271), 1.0);
			
			assertNotNull(first);
			assertNotNull(second);
			assertNotNull(third);
			
			assertEquals(52.182, first.Latitude, 0.0);
			assertEquals(22.1271, first.Longitude, 0.0);
			assertEquals(51.2812, second.Latitude, 0.0);
			assertEquals(12.2712, second.Longitude, 0.0);
			assertEquals(50.2871, third.Latitude, 0.0);
			assertEquals(17.2812, third.Longitude, 0.0);
		}
	}
}
