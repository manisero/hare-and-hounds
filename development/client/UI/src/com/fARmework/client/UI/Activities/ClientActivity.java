package com.fARmework.client.UI.Activities;

import android.os.Bundle;
import android.view.View;

import com.fARmework.client.UI.R;
import com.fARmework.client.UI.ViewModels.ClientViewModel;
import com.fARmework.modules.ScreenGestures.Presentation.GesturePicker;
import com.fARmework.modules.ScreenGestures.Presentation.OnGestureListener;
import com.google.inject.Inject;

public class ClientActivity extends RoboBindingActivity
{
	@Inject ClientViewModel viewModel; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAndBindRootView(R.layout.main, viewModel);
        
        GesturePicker gesturePicker = (GesturePicker)findViewById(R.id.gesturePicker);
        gesturePicker.setOnGestureListener(new OnGestureListener()
		{
			@Override
			public void onGesture(View v, String gesture)
			{
				viewModel.sendGesture.Invoke(v, gesture);
			}
		});
    }
    
    @Override
    public void onBackPressed()
    {
    	viewModel.disconnect();
    	super.onBackPressed();
    }
}