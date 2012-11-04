package com.fARmework.modules.PositionTracking.Tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.SuiteClasses;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			PositionDataListTests.TheIsNearAnyPositionMethod.class,
			PositionDataListTests.TheGetNextPositionMethod.class,
			PositionDataListTests.TheIsFinalPositionMethod.class,
			PositionDataListTests.TheAddMethod.class
		})
public class PositionDataListTests 
{
	public static class TheIsNearAnyPositionMethod
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
			
			assertFalse(dataList.isNearAnyPosition(new PositionData(51.0, 21.0), 1.0));
			assertFalse(dataList.isNearAnyPosition(new PositionData(100.0, 100.0), 12.0));
			assertTrue(dataList.isNearAnyPosition(new PositionData(63.2712, 85.2732), 1.0));
			assertTrue(dataList.isNearAnyPosition(new PositionData(52.642, 21.248), 1.0));
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
	
	public static class TheIsFinalPositionMethod
	{
		@Test
		public void ReturnsTrueForFinalLocation()
		{
			// Arrange
			PositionData finalPosition = new PositionData(0, 0);
			PositionData position = new PositionData(0, 0);
			double maxDistance = 7;
			
			IDistanceCalculator calculator = mock(IDistanceCalculator.class);
			when(calculator.calculateDistance(position, finalPosition)).thenReturn(maxDistance - 1);
			
			PositionDataList list = new PositionDataList(calculator);
			list.add(new PositionData(0, 0));
			list.add(finalPosition);
			
			// Act
			boolean result = list.isFinalPosition(position, maxDistance);
			
			// Assert
			assertTrue(result);
			verify(calculator).calculateDistance(position, finalPosition);
		}
		
		@Test
		public void ReturnsFalseForNotFinalLocation()
		{
			// Arrange
			PositionData finalPosition = new PositionData(0, 0);
			PositionData position = new PositionData(0, 0);
			double maxDistance = 7;
			
			IDistanceCalculator calculator = mock(IDistanceCalculator.class);
			when(calculator.calculateDistance(position, finalPosition)).thenReturn(maxDistance + 1);
			
			PositionDataList list = new PositionDataList(calculator);
			list.add(new PositionData(0, 0));
			list.add(finalPosition);
			
			// Act
			boolean result = list.isFinalPosition(position, maxDistance);
			
			// Assert
			assertFalse(result);
			verify(calculator).calculateDistance(position, finalPosition);
		}
		
		@Test
		public void ReturnsFalseForEmptyList()
		{
			// Arrange
			PositionDataList list = new PositionDataList(null);
			
			// Act
			boolean result = list.isFinalPosition(new PositionData(0, 0), Double.MAX_VALUE);
			
			// Assert
			assertFalse(result);
		}
	}
	
	public static class TheAddMethod
	{
		@Test
		public void AddsDistantPositions()
		{
			// Arrange
			PositionData gdanskCityPosition = new PositionData(54.358157, 18.643799);
			PositionData poznanCityPosition = new PositionData(52.409121, 16.907959);
			PositionData warsawCityPosition = new PositionData(52.227799, 21.005859);
			PositionData lodzCityPosition = new PositionData(51.76104, 19.451294);
			PositionData wroclawCityPosition = new PositionData(51.106971, 17.039795);
			PositionData cracowCityPosition = new PositionData(50.064192, 19.945679);
			
			double maxRadius = 5.0;
			
			IDistanceCalculator distanceCalculator = mock(IDistanceCalculator.class);
			when(distanceCalculator.calculateDistance(any(PositionData.class), any(PositionData.class))).thenReturn(maxRadius * 100.0);
			
			PositionDataList positionDataList = new PositionDataList(distanceCalculator);
			
			// Act
			boolean addsGdansk = positionDataList.add(gdanskCityPosition, maxRadius);
			boolean addsPoznan = positionDataList.add(poznanCityPosition, maxRadius);
			boolean addsWarsaw = positionDataList.add(warsawCityPosition, maxRadius);
			boolean addsLodz = positionDataList.add(lodzCityPosition, maxRadius);
			boolean addsWroclaw = positionDataList.add(wroclawCityPosition, maxRadius);
			boolean addsCracow = positionDataList.add(cracowCityPosition, maxRadius);
			
			// Assert
			assertTrue(addsGdansk);
			assertTrue(addsPoznan);
			assertTrue(addsWarsaw);
			assertTrue(addsLodz);
			assertTrue(addsWroclaw);
			assertTrue(addsCracow);
			assertEquals(6, positionDataList.size());
		}
		
		@Test
		public void OmitsClosePositions()
		{
			// Arrange
			PositionData kabatyStationPosition = new PositionData(52.131012, 21.065619);
			PositionData natolinStationPosition = new PositionData(52.140007, 21.057508);
			PositionData imielinStationPosition = new PositionData(52.149831, 21.045427);
			PositionData stoklosyStationPosition = new PositionData(52.156137, 21.03457);
			PositionData ursynowStationPosition = new PositionData(52.162021, 21.02766);
			PositionData sluzewStationPosition = new PositionData(52.173366, 21.026201);
			
			double maxRadius = 5.0;
			
			IDistanceCalculator distanceCalculator = mock(IDistanceCalculator.class);
			when(distanceCalculator.calculateDistance(any(PositionData.class), any(PositionData.class))).thenReturn(maxRadius + 1.0);
			when(distanceCalculator.calculateDistance(kabatyStationPosition, natolinStationPosition)).thenReturn(maxRadius - 1.0);
			when(distanceCalculator.calculateDistance(natolinStationPosition, imielinStationPosition)).thenReturn(maxRadius - 1.0);
			when(distanceCalculator.calculateDistance(imielinStationPosition, stoklosyStationPosition)).thenReturn(maxRadius - 1.0);
			when(distanceCalculator.calculateDistance(stoklosyStationPosition, ursynowStationPosition)).thenReturn(maxRadius - 1.0);
			when(distanceCalculator.calculateDistance(ursynowStationPosition, sluzewStationPosition)).thenReturn(maxRadius - 1.0);
			
			PositionDataList positionDataList = new PositionDataList(distanceCalculator);
			
			// Act
			boolean addsKabatyStation = positionDataList.add(kabatyStationPosition, maxRadius);
			boolean addsNatolinStation = positionDataList.add(natolinStationPosition, maxRadius);
			boolean addsImielinStation = positionDataList.add(imielinStationPosition, maxRadius);
			boolean addsStoklosyStation = positionDataList.add(stoklosyStationPosition, maxRadius);
			boolean addsUrsynowStation = positionDataList.add(ursynowStationPosition, maxRadius);
			boolean addsSluzewStation = positionDataList.add(sluzewStationPosition, maxRadius);
			
			// Assert
			assertTrue(addsKabatyStation);
			assertFalse(addsNatolinStation);
			assertTrue(addsImielinStation);
			assertFalse(addsStoklosyStation);
			assertTrue(addsUrsynowStation);
			assertFalse(addsSluzewStation);
			assertEquals(3, positionDataList.size());
		}
		
		@Test
		public void AddsClosePositionsIfNotSuccessive()
		{
			// Arrange
			PositionData kabatyStationPosition = new PositionData(52.131012, 21.065619);
			PositionData natolinStationPosition = new PositionData(52.140007, 21.057508);
			PositionData imielinStationPosition = new PositionData(52.149831, 21.045427);
			PositionData stoklosyStationPosition = new PositionData(52.156137, 21.03457);
			PositionData ursynowStationPosition = new PositionData(52.162021, 21.02766);
			PositionData sluzewStationPosition = new PositionData(52.173366, 21.026201);
			
			double maxRadius = 5.0;
			
			IDistanceCalculator distanceCalculator = mock(IDistanceCalculator.class);
			when(distanceCalculator.calculateDistance(any(PositionData.class), any(PositionData.class))).thenReturn(maxRadius + 1.0);
			when(distanceCalculator.calculateDistance(kabatyStationPosition, kabatyStationPosition)).thenReturn(maxRadius - 1.0);
			when(distanceCalculator.calculateDistance(natolinStationPosition, natolinStationPosition)).thenReturn(maxRadius - 1.0);
			when(distanceCalculator.calculateDistance(imielinStationPosition, imielinStationPosition)).thenReturn(maxRadius - 1.0);
			when(distanceCalculator.calculateDistance(stoklosyStationPosition, stoklosyStationPosition)).thenReturn(maxRadius - 1.0);
			when(distanceCalculator.calculateDistance(ursynowStationPosition, ursynowStationPosition)).thenReturn(maxRadius - 1.0);
			when(distanceCalculator.calculateDistance(sluzewStationPosition, sluzewStationPosition)).thenReturn(maxRadius - 1.0);
			
			PositionDataList positionDataList = new PositionDataList(distanceCalculator);
			
			// Act
			boolean addsKabatyStationPosition = positionDataList.add(kabatyStationPosition);
			boolean addsNatolinStationPoisition = positionDataList.add(natolinStationPosition);
			boolean addsKabatyStationPositionAgain = positionDataList.add(kabatyStationPosition);
			boolean addsImielinStationPosition = positionDataList.add(imielinStationPosition);
			boolean addsStoklosyStationPosition = positionDataList.add(stoklosyStationPosition);
			boolean addsNatolinStationPositionAgain = positionDataList.add(natolinStationPosition);
			boolean addsUrsynowStationPosition = positionDataList.add(ursynowStationPosition);
			boolean addsStoklosyStationPositionAgain = positionDataList.add(stoklosyStationPosition);
			boolean addsSluzewStationPosition = positionDataList.add(sluzewStationPosition);
			boolean addsImielinStationPositionAgain = positionDataList.add(imielinStationPosition);
			boolean addsUrsynowStationPositionAgain = positionDataList.add(ursynowStationPosition);
			boolean addsSluzewStationPositionAgain = positionDataList.add(sluzewStationPosition);
			
			// Assert
			assertTrue(addsKabatyStationPosition);
			assertTrue(addsNatolinStationPoisition);
			assertTrue(addsImielinStationPosition);
			assertTrue(addsStoklosyStationPosition);
			assertTrue(addsUrsynowStationPosition);
			assertTrue(addsSluzewStationPosition);
			assertTrue(addsKabatyStationPositionAgain);
			assertTrue(addsNatolinStationPositionAgain);
			assertTrue(addsImielinStationPositionAgain);
			assertTrue(addsStoklosyStationPositionAgain);
			assertTrue(addsUrsynowStationPositionAgain);
			assertTrue(addsSluzewStationPositionAgain);
			assertEquals(12, positionDataList.size());
		}
	}
}
