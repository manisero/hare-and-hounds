package com.fARmework.HareAndHounds.Client.Activities;

import android.os.*;
import android.view.*;

import com.fARmework.HareAndHounds.Client.ViewModels.*;
import com.fARmework.modules.SpaceGraphics.Android.*;
import com.fARmework.utils.Android.*;

public class CheckpointActivity extends BoundActivity<CheckpointViewModel>
{
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        // Set SpaceGraphicsView's Model and DirectionProvider
        SpaceGraphicsView spaceGraphicsView = (SpaceGraphicsView)((ViewGroup)View).getChildAt(0); // TODO: Find the view by id once SpaceGraphicsView's constructor is fixed
        spaceGraphicsView.setModel(ViewModel.getArrowModel());
        spaceGraphicsView.setDirectionProvider(ViewModel.getDirectionProvider());
    }
}
