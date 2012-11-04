package com.fARmework.HareAndHounds.Client.Activities;

import android.os.*;
import android.view.*;

import com.fARmework.HareAndHounds.Client.ViewModels.*;
import com.fARmework.modules.SpaceGraphics.Android.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection.*;
import com.fARmework.utils.Android.Activities.*;

public class CheckpointActivity extends BoundActivity<CheckpointViewModel>
{
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        SpaceGraphicsView spaceGraphicsView = (SpaceGraphicsView)((ViewGroup)View).getChildAt(0); // TODO: Find the view by id once SpaceGraphicsView's constructor is fixed
        IGraphicsRenderer graphicsRenderer = spaceGraphicsView.getGraphicsRenderer();
        graphicsRenderer.setDirectionProvider(ViewModel.getDirectionProvider());
        graphicsRenderer.setModel(ViewModel.getArrowModel());
    }
}
