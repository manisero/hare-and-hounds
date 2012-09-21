package com.fARmework.modules.PositionTracking.Android;

import com.fARmework.modules.PositionTracking.Data.PositionData;

public interface IPositionService
{
	public interface IPositionListener
	{
		void onPosition(PositionData position);
	}
	
	void getSinglePosition(IPositionListener positionListener);
	
	void startGettingPosition(IPositionListener positionListener);
	void stopGettingPosition(IPositionListener positionListener);
}
