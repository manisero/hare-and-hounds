package com.fARmework.modules.PositionTracking.Android;

import com.fARmework.modules.PositionTracking.Data.PositionData;

public interface IPositionProvider
{
	public interface IPositionListener
	{
		void onPosition(PositionData position);
	}
	
	void getSinglePosition(IPositionListener positionListener);
	
	void startGettingPosition(int updateInterval, IPositionListener positionListener);
	void stopGettingPosition(IPositionListener positionListener);
}
