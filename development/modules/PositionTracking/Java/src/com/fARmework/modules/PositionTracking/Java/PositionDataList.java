package com.fARmework.modules.PositionTracking.Java;

import com.fARmework.modules.PositionTracking.Data.*;

import java.util.*;

public class PositionDataList extends ArrayList<PositionData> 
{
	private static final long serialVersionUID = 1L;
	
	private IDistanceCalculator _distanceCalculator;
	
	public PositionDataList(IDistanceCalculator distanceCalculator)
	{
		_distanceCalculator = distanceCalculator;
	}	
	
	public boolean isNearAnyPosition(PositionData position, double maxRadius)
	{	
		for (PositionData data : this)
		{
			if(_distanceCalculator.calculateDistance(data, position) <= maxRadius)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public PositionData getNextPosition(PositionData position, double maxRadius)
	{
		Iterator<PositionData> iterator = iterator();
		
		while (iterator.hasNext())
		{
			PositionData data = iterator.next();
			
			if (_distanceCalculator.calculateDistance(data, position) <= maxRadius)
			{
				if (iterator.hasNext())
				{
					return iterator.next();
				}
			}
		}
		
		return null;
	}
	
	public boolean isFinalPosition(PositionData position, double maxRadius)
	{
		if (size() == 0)
		{
			return false;
		}
		
		return _distanceCalculator.calculateDistance(position, get(size() - 1)) <= maxRadius;
	}
}
