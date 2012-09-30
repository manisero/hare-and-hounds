package com.fARmework.utils.Android;

import java.io.*;

import android.content.*;
import android.hardware.*;
import android.util.*;
import android.view.*;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback
{
	private SurfaceHolder _holder;
	private Camera _camera;
	
	public CameraPreview(Context context)
	{
		super(context);
		initialize();
	}
	
	public CameraPreview(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initialize();
	}
	
	private void initialize()
	{
		_holder = getHolder();
		_holder.addCallback(this);
		_holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		_camera = Camera.open();
		
		try
		{
			_camera.setPreviewDisplay(_holder);
			_camera.startPreview();
		}
		catch (IOException e)
		{
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{
		Camera.Parameters parameters = _camera.getParameters();
		parameters.setPreviewSize(width, height);
		_camera.setParameters(parameters);
		_camera.startPreview();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		_camera.stopPreview();
		_camera.release();
	}
	
}
