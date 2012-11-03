package com.fARmework.modules.SpaceGestures.Android;

import android.hardware.SensorEventListener;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;

public interface ISpaceGestureRecorder extends SensorEventListener
{
	public void startRecording();
	public SpaceGestureData stopRecording();
}