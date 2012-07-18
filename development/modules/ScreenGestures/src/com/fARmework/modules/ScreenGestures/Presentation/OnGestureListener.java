package com.fARmework.modules.ScreenGestures.Presentation;

import com.fARmework.modules.ScreenGestures.Data.GestureData;

import android.view.View;

public interface OnGestureListener
{
	void onGesture(View v, GestureData gesture);
}
