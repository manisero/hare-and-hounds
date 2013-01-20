package com.fARmework.modules.PositionTracking.WiFi.Java.Logic._impl;

import java.util.*;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Java.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Java.Infrastructure.*;
import com.fARmework.modules.PositionTracking.WiFi.Java.Logic.*;

public class PositionCalculator implements IPositionCalculator
{
	private static final double DOUBLE_EQUALITY_THRESHOLD = 0.000001; // the threshold should be low since it is used to compare geographic coordinates
	
	private final ISettingsProvider _settingsProvider;
	
	private static class PostionRangePair
	{
		public PositionData Position;
		public Integer Range;
		
		public PostionRangePair(PositionData position, Integer range)
		{
			Position = position;
			Range = range;
		}
	}
	
	private static class PositionPair
	{
		public PositionData Position1;
		public PositionData Position2;
		
		public PositionPair(PositionData position1, PositionData position2)
		{
			Position1 = position1;
			Position2 = position2;
		}
	}
	
	public PositionCalculator(ISettingsProvider settingsProvider)
	{
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public PositionData getPositionFromWiFi(WiFiData wifiData)
	{
		List<AccessPointData> accessPointsDatas = _settingsProvider.getAccessPointsData();
		
		Map<String, PostionRangePair> accessPointsDataMap = new HashMap<String, PostionRangePair>();
		
		for (AccessPointData accessPointData : accessPointsDatas)
		{
			accessPointsDataMap.put(accessPointData.MacAddress, new PostionRangePair(accessPointData.Position, accessPointData.MaxRange));
		}
		
		final Map<String, Integer> knownAccessPointsStrengths = new HashMap<String, Integer>();
		
		for (Map.Entry<String, Integer> singleData : wifiData.WiFiSignalStrengths.entrySet())
		{
			if (accessPointsDataMap.containsKey(singleData.getKey()))
			{
				knownAccessPointsStrengths.put(singleData.getKey(), singleData.getValue());
			}
		}
		
		TreeMap<String, Integer> sortedKnownAccessPointsStrengths = new TreeMap<String, Integer>(new Comparator<String>()
		{
			@Override
			public int compare(String firstAccessPointName, String secondAccessPointName)
			{
				if (knownAccessPointsStrengths.get(firstAccessPointName) >= knownAccessPointsStrengths.get(secondAccessPointName))
				{
					return 1;
				}
				
				return -1;
			}
		});
		
		sortedKnownAccessPointsStrengths.putAll(knownAccessPointsStrengths);
		
		if (sortedKnownAccessPointsStrengths.size() < 3)
		{
			return null;
		}
		
		String[] keys = sortedKnownAccessPointsStrengths.keySet().toArray(new String[] { });
		
		PostionRangePair firstPositionRangePair = accessPointsDataMap.get(keys[0]);
		PostionRangePair secondPositionRangePair = accessPointsDataMap.get(keys[1]);
		PostionRangePair thirdPositionRangePair = accessPointsDataMap.get(keys[2]);
		
		double firstRadius = getDistanceFromSignalStrength(sortedKnownAccessPointsStrengths.get(keys[0]), firstPositionRangePair.Range);
		double secondRadius = getDistanceFromSignalStrength(sortedKnownAccessPointsStrengths.get(keys[1]), secondPositionRangePair.Range);
		double thirdRadius = getDistanceFromSignalStrength(sortedKnownAccessPointsStrengths.get(keys[2]), thirdPositionRangePair.Range);
		
		PositionPair firstSecondIntersectionPoints = getIntersectionPoints(firstPositionRangePair.Position, firstRadius, secondPositionRangePair.Position, secondRadius);
		PositionPair firstThirdIntersectionPoints = getIntersectionPoints(firstPositionRangePair.Position, firstRadius, thirdPositionRangePair.Position, thirdRadius);
		
		if (positionsEqual(firstSecondIntersectionPoints.Position1, firstThirdIntersectionPoints.Position1))
		{
			return firstSecondIntersectionPoints.Position1;
		}
		
		if (positionsEqual(firstSecondIntersectionPoints.Position1, firstThirdIntersectionPoints.Position2))
		{
			return firstSecondIntersectionPoints.Position1;
		}
		
		if (positionsEqual(firstSecondIntersectionPoints.Position2, firstThirdIntersectionPoints.Position1))
		{
			return firstSecondIntersectionPoints.Position2;
		}
		
		if (positionsEqual(firstSecondIntersectionPoints.Position2, firstThirdIntersectionPoints.Position2))
		{
			return firstSecondIntersectionPoints.Position2;
		}
		
		return null;
	}
	
	private double getDistanceFromSignalStrength(int signalStrength, int range)
	{
		return (100 - signalStrength) * range;
	}
	
	private PositionPair getIntersectionPoints(PositionData firstPosition, double firstRadius, PositionData secondPosition, double secondRadius)
	{
		// algorithm based on: http://mysite.verizon.net/res148h4j/javascript/script_circle_intersections.html
		
	    double d  = Math.sqrt((firstPosition.Latitude - secondPosition.Latitude) * (firstPosition.Latitude - secondPosition.Latitude) + (firstPosition.Longitude - secondPosition.Longitude) * (firstPosition.Longitude - secondPosition.Longitude));

	    if (d > (firstRadius + secondRadius) || d < Math.abs(firstRadius - secondRadius)) // no intersection
	    {
	        return null;
	    }

	    double a = (firstRadius * firstRadius - secondRadius * secondRadius + d * d) / (2 * d);  
	    double h = Math.sqrt(firstRadius * firstRadius - a * a); // h is a common leg for two right triangles

	    // locate midpoint between intersections along line of centers
	    double midpointX = firstPosition.Latitude + a * (secondPosition.Latitude - firstPosition.Latitude) / d;
	    double midpointY = firstPosition.Longitude + a * (secondPosition.Longitude - firstPosition.Longitude) / d;

	    double firstIntersectionX = midpointX + h * (secondPosition.Longitude - firstPosition.Longitude) / d;
	    double firstIntersectionY = midpointY - h * (secondPosition.Latitude - firstPosition.Latitude) / d;

	    double secondIntersectionX = midpointX - h * (secondPosition.Longitude - firstPosition.Longitude) / d;
	    double secondIntersectionY = midpointY + h * (secondPosition.Latitude - firstPosition.Latitude) / d;
	    
	    return new PositionPair(new PositionData(firstIntersectionX, firstIntersectionY), new PositionData(secondIntersectionX, secondIntersectionY));
	}
	
	private boolean positionsEqual(PositionData firstPosition, PositionData secondPosition)
	{
		if (Math.abs(firstPosition.Latitude - secondPosition.Latitude) > DOUBLE_EQUALITY_THRESHOLD)
		{
			return false;
		}
		
		if (Math.abs(firstPosition.Longitude - secondPosition.Longitude) > DOUBLE_EQUALITY_THRESHOLD)
		{
			return false;
		}
		
		return true;
	}
}
