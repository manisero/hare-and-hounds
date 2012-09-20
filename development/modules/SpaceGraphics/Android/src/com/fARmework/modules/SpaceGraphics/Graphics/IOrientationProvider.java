package com.fARmework.modules.SpaceGraphics.Graphics;

public interface IOrientationProvider
{
	public interface IOrientationListener
	{
		void onOrientationChanged(float azimuth, float pitch, float roll);
	}
	
	void getOrientation(IOrientationListener orientationListener);
}
