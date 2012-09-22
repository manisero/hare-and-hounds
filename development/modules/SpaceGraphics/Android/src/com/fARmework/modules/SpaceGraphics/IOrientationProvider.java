package com.fARmework.modules.SpaceGraphics;

public interface IOrientationProvider
{
	public class Orientation
	{
		public float Azimuth;
		public float Pitch;
		public float Roll;
		
		public Orientation(float azimuth, float pitch, float roll)
		{
			Azimuth = azimuth;
			Pitch = pitch;
			Roll = roll;
		}
	}
	
	Orientation getOrientation();
	
	float[] getRotationMatrix();
}
