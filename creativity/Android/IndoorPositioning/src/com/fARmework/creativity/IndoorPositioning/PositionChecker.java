package com.fARmework.creativity.IndoorPositioning;

import java.util.List;
import java.util.Map;

public class PositionChecker
{
	public static final Integer THRESHOLD = 0;
	
	public boolean isPositionVisited(IndoorPositionData currentPositionData, List<IndoorPositionData> visitedPositions)
	{
		if (currentPositionData.WifiSignalData.isEmpty())
		{
			return false;
		}
		
		for (IndoorPositionData visitedPositionData : visitedPositions)
		{	
			if (currentPositionData.WifiSignalData.keySet().equals(visitedPositionData.WifiSignalData.keySet()))
			{
				boolean result = true;
				
				for (Map.Entry<String, Integer> currentPositionEntry : currentPositionData.WifiSignalData.entrySet())
				{
					Integer currentLevel = currentPositionEntry.getValue();
					Integer visitedLevel = visitedPositionData.WifiSignalData.get(currentPositionEntry.getKey());
					
					if (Math.abs(currentLevel - visitedLevel) > THRESHOLD)
					{
						result = false;
						break;
					}
				}
				
				if (result)
				{
					return true;
				}
			}
		}
		
		return false;
	}
}
