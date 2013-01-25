package com.fARmework.modules.PositionTracking.WiFi.Java.Logic._impl;

import java.util.*;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Java.Data.*;
import com.fARmework.modules.PositionTracking.WiFi.Java.Infrastructure.*;
import com.fARmework.modules.PositionTracking.WiFi.Java.Logic.*;

public class PositionCalculator implements IPositionCalculator
{
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
		
		PositionsPair intersectionPoints1 = getIntersectionPoints(firstAccessPointData.Position, firstAccessPointData.Distance, secondAccessPointData.Position, secondAccessPointData.Distance);
		PositionsPair intersectionPoints2 = getIntersectionPoints(firstAccessPointData.Position, firstAccessPointData.Distance, thirdAccessPointData.Position, thirdAccessPointData.Distance);
		PositionsPair intersectionPoints3 = getIntersectionPoints(secondAccessPointData.Position, secondAccessPointData.Distance, thirdAccessPointData.Position, thirdAccessPointData.Distance);
		
		double minCircumference = Double.MAX_VALUE;
		PositionData position1 = null;
		PositionData position2 = null;
		PositionData position3 = null;;
		
		for (PositionData point1 : intersectionPoints1.toArray())
		{
			for (PositionData point2 : intersectionPoints2.toArray())
			{
				for (PositionData point3 : intersectionPoints3.toArray())
				{
					double circumference = calculateTriangleCircumference(point1, point2, point3);
					
					if (circumference < minCircumference)
					{
						minCircumference = circumference;
						position1 = point1;
						position2 = point2;
						position3 = point3;
					}
				}
			}
		}
		
		return getTriangleCircumcenter(position1, position2, position3);
	}
	
	private PositionsPair getIntersectionPoints(PositionData point1, double radius1, PositionData point2, double radius2)
	{
		// algorithm based on: http://mysite.verizon.net/res148h4j/javascript/script_circle_intersections.html
		
	    double d  = Math.sqrt((point1.Latitude - point2.Latitude) * (point1.Latitude - point2.Latitude) + (point1.Longitude - point2.Longitude) * (point1.Longitude - point2.Longitude));

	    if (d > (radius1 + radius2) || d < Math.abs(radius1 - radius2)) // no intersection
	    {
	        return null;
	    }

	    double a = (radius1 * radius1 - radius2 * radius2 + d * d) / (2 * d);  
	    double h = Math.sqrt(radius1 * radius1 - a * a); // h is a common leg for two right triangles

	    // locate midpoint between intersections along line of centers
	    double midpointX = point1.Latitude + a * (point2.Latitude - point1.Latitude) / d;
	    double midpointY = point1.Longitude + a * (point2.Longitude - point1.Longitude) / d;

	    double firstIntersectionX = midpointX + h * (point2.Longitude - point1.Longitude) / d;
	    double firstIntersectionY = midpointY - h * (point2.Latitude - point1.Latitude) / d;

	    double secondIntersectionX = midpointX - h * (point2.Longitude - point1.Longitude) / d;
	    double secondIntersectionY = midpointY + h * (point2.Latitude - point1.Latitude) / d;
	    
	    return new PositionsPair(new PositionData(firstIntersectionX, firstIntersectionY), new PositionData(secondIntersectionX, secondIntersectionY));
	}
	
	private double calculateTriangleCircumference(PositionData point1, PositionData point2, PositionData point3)
	{
		double side1Length = Math.sqrt(Math.pow(point1.Latitude - point2.Latitude, 2.0) + Math.pow(point1.Longitude - point2.Longitude, 2.0));
		double side2Length = Math.sqrt(Math.pow(point2.Latitude - point3.Latitude, 2.0) + Math.pow(point2.Longitude - point3.Longitude, 2.0));
		double side3Length = Math.sqrt(Math.pow(point3.Latitude - point1.Latitude, 2.0) + Math.pow(point3.Longitude - point1.Longitude, 2.0));
		
		return side1Length + side2Length + side3Length;
	}
	
	private PositionData getTriangleCircumcenter(PositionData point1, PositionData point2, PositionData point3)
	{
		double point1Square = point1.Latitude * point1.Latitude + point1.Longitude * point1.Longitude;
		double point2Square = point2.Latitude * point2.Latitude + point2.Longitude * point2.Longitude;
		double point3Square = point3.Latitude * point3.Latitude + point3.Longitude * point3.Longitude;
		
		double numeratorX = point1Square * (point2.Longitude - point3.Longitude) +
							point2Square * (point3.Longitude - point1.Longitude) +
							point3Square * (point1.Longitude - point2.Longitude);
		
		double numeratorY = point1Square * (point3.Latitude - point2.Latitude) +
							point2Square * (point1.Latitude - point3.Latitude) +
							point3Square * (point2.Latitude - point1.Latitude);
		
		double denominator = 2 * (point1.Latitude * (point2.Longitude - point3.Longitude) +
								  point2.Latitude * (point3.Longitude - point1.Longitude) +
				  				  point3.Latitude * (point1.Longitude - point2.Longitude));
		
		return new PositionData(numeratorX / denominator, numeratorY / denominator);
	}
}
