package com.fARmework.modules.PositionTracking.WiFi.Java.Logic._impl;

import java.util.*;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Java.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Java.Infrastructure.*;
import com.fARmework.modules.PositionTracking.WiFi.Java.Logic.*;

public class PositionCalculator implements IPositionCalculator
{
	private static final double COORDINATE_EQUALITY_THRESHOLD = 0.000001; // the threshold should be low since it is used to compare geographic coordinates
	
	private final IDistanceCalculator _distanceCalculator;
	private final ISettingsProvider _settingsProvider;
	
	public PositionCalculator(IDistanceCalculator distanceCalculator, ISettingsProvider settingsProvider)
	{
		_distanceCalculator = distanceCalculator;
		_settingsProvider = settingsProvider;
	}
	
	@Override
	public PositionData getPositionFromWiFi(WiFiData wifiData)
	{
		List<AccessPointDataWithSignalStrength> knownAccessPointsData = getKnownAccessPointsData(wifiData, _settingsProvider.getAccessPointsData());
		
		return calculatePosition(knownAccessPointsData);
	}
	
	private List<AccessPointDataWithSignalStrength> getKnownAccessPointsData(WiFiData wifiData, List<AccessPointData> registeredAccessPointsData)
	{
		Map<String, AccessPointData> accessPointsDataMap = new HashMap<String, AccessPointData>();
		
		for (AccessPointData accessPointData : registeredAccessPointsData)
		{
			accessPointsDataMap.put(accessPointData.MacAddress, accessPointData);
		}
		
		final List<AccessPointDataWithSignalStrength> knownAccessPointsData = new ArrayList<AccessPointDataWithSignalStrength>();
		
		for (Map.Entry<String, Integer> singleWifiData : wifiData.WiFiSignalStrengths.entrySet())
		{
			if (accessPointsDataMap.containsKey(singleWifiData.getKey()))
			{
				knownAccessPointsData.add(new AccessPointDataWithSignalStrength(accessPointsDataMap.get(singleWifiData.getKey()), singleWifiData.getValue()));
			}
		}
		
		Collections.sort(knownAccessPointsData, new Comparator<AccessPointDataWithSignalStrength>()
		{
			@Override
			public int compare(AccessPointDataWithSignalStrength firstAccessPoint, AccessPointDataWithSignalStrength secondAccessPoint)
			{
				if (firstAccessPoint.SignalStrength >= secondAccessPoint.SignalStrength)
				{
					return 1;
				}
				
				return -1;
			}
		});
		
		return knownAccessPointsData;
	}
	
	private PositionData calculatePosition(List<AccessPointDataWithSignalStrength> knownAccessPointsData)
	{
		if (knownAccessPointsData.size() < 3)
		{
			return null;
		}
		
		AccessPointDataWithSignalStrengthAndDistance firstAccessPointData = new AccessPointDataWithSignalStrengthAndDistance(knownAccessPointsData.get(0), 
																															 _distanceCalculator.calculateDistance(knownAccessPointsData.get(0).SignalStrength, knownAccessPointsData.get(0).Range));
		
		AccessPointDataWithSignalStrengthAndDistance secondAccessPointData = new AccessPointDataWithSignalStrengthAndDistance(knownAccessPointsData.get(1),
																															  _distanceCalculator.calculateDistance(knownAccessPointsData.get(1).SignalStrength, knownAccessPointsData.get(1).Range));
		
		AccessPointDataWithSignalStrengthAndDistance thirdAccessPointData = new AccessPointDataWithSignalStrengthAndDistance(knownAccessPointsData.get(2),
																															 _distanceCalculator.calculateDistance(knownAccessPointsData.get(2).SignalStrength, knownAccessPointsData.get(2).Range));
		
		PositionsPair firstSecondIntersectionPoints = getIntersectionPoints(firstAccessPointData.Position, firstAccessPointData.Distance, secondAccessPointData.Position, secondAccessPointData.Distance);
		PositionsPair firstThirdIntersectionPoints = getIntersectionPoints(firstAccessPointData.Position, firstAccessPointData.Distance, thirdAccessPointData.Position, thirdAccessPointData.Distance);
		PositionsPair secondThirdIntersectionPoints = getIntersectionPoints(secondAccessPointData.Position, secondAccessPointData.Distance, thirdAccessPointData.Position, thirdAccessPointData.Distance);
		
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
	
	private PositionsPair getIntersectionPoints(PositionData firstPosition, double firstRadius, PositionData secondPosition, double secondRadius)
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
	    
	    return new PositionsPair(new PositionData(firstIntersectionX, firstIntersectionY), new PositionData(secondIntersectionX, secondIntersectionY));
	}
	
	private boolean positionsEqual(PositionData firstPosition, PositionData secondPosition)
	{
		if (Math.abs(firstPosition.Latitude - secondPosition.Latitude) > COORDINATE_EQUALITY_THRESHOLD)
		{
			return false;
		}
		
		if (Math.abs(firstPosition.Longitude - secondPosition.Longitude) > COORDINATE_EQUALITY_THRESHOLD)
		{
			return false;
		}
		
		return true;
	}
}
