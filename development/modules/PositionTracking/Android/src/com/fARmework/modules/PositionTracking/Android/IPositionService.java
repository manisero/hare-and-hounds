package com.fARmework.modules.PositionTracking.Android;

public interface IPositionService
{
	public interface IPositionListener
	{
		void onPosition(double longitude, double latitude);
	}
	
	void GetPosition(IPositionListener positionListener);
}
