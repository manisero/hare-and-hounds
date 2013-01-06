package com.fARmework.creativity.IndoorPositioning;

import java.util.ArrayList;
import java.util.List;

public class PositionRepository
{
	private List<IndoorPositionData> _positions = new ArrayList<IndoorPositionData>();
	
	public void addPosition(IndoorPositionData positionData)
	{
		_positions.add(positionData);
	}
	
	public List<IndoorPositionData> getPositions()
	{
		return _positions;
	}
}
