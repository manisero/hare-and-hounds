package com.fARmework.modules.SpaceGestures.Android;

import android.content.Context;
import android.hardware.SensorEventListener;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;

public interface ISpaceGestureListener extends SensorEventListener
{
	public void startRecording(Context context);
	public SpaceGestureData stopRecording();
}