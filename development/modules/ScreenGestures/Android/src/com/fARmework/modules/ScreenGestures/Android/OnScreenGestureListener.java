package com.fARmework.modules.ScreenGestures.Android;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;

import android.view.View;

public interface OnScreenGestureListener
{
	void onGesture(View v, ScreenGestureData gesture);
}
