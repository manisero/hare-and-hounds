package com.fARmework.HareAndHounds.Client.Activities;

import android.os.*;
import android.widget.*;

import com.fARmework.HareAndHounds.Client.Infrastructure.*;
import com.fARmework.HareAndHounds.Client.ViewModels.*;
import com.fARmework.utils.Android.Activities.*;
import com.fARmework.utils.Android.Views.*;
import com.google.inject.*;

public class CheckpointActivity extends BoundActivity<CheckpointViewModel>
{
	@Inject
	ISettingsProvider SettingsProvider;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        if (SettingsProvider.getDisplayCheckpointCameraPreview())
        {
        	((FrameLayout)View).addView(new CameraPreview(this));
        }
    }
}
