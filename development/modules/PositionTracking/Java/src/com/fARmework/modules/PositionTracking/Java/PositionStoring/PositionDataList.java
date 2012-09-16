package com.fARmework.modules.PositionTracking.Java.PositionStoring;

import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.DistanceCalculating.*;

import java.util.*;

public class PositionDataList extends ArrayList<PositionData> 
{
	private IDistanceCalculator _distanceCalculator;
	
	public PositionDataList(IDistanceCalculator distanceCalculator)
	{
		_distanceCalculator = distanceCalculator;
	}	
	
	public boolean isInPositionRadius(PositionData currentPosition, double maxRadius)
	{	
		for(PositionData data : this)
		{
			if(_distanceCalculator.calculateDistance(data, currentPosition) <= maxRadius)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public PositionData getNextPosition(PositionData currentPosition, double maxRadius)
	{
		if(!isInPositionRadius(currentPosition, maxRadius))
		{
			return null;
		}
		
		Iterator<PositionData> iterator = iterator();
		
		while(iterator.hasNext())
		{
			PositionData data = iterator.next();
			
			if(_distanceCalculator.calculateDistance(data, currentPosition) <= maxRadius)
			{
				if(iterator.hasNext())
				{
					return iterator.next();
				}
			}
		}
		
		return null;
	}
	
	private static final long serialVersionUID = 1L;
}
