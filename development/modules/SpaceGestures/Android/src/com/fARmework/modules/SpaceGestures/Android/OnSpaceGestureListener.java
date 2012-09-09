package com.fARmework.modules.SpaceGestures.Android;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData;

import android.view.View;

public interface OnSpaceGestureListener
{
	void onGesture(View v, SpaceGestureData gesture);
}
