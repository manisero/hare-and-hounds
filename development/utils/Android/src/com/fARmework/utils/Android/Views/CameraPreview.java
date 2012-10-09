package com.fARmework.utils.Android.Views;

import java.io.*;

import android.content.*;
import android.hardware.*;
import android.util.*;
import android.view.*;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback
{
	private Display _display;
	private SurfaceHolder _holder;
	private Camera _camera;
	
	public CameraPreview(Context context)
	{
		super(context);
		initialize(context);
	}
	
	public CameraPreview(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initialize(context);
	}
	
	private void initialize(Context context)
	{
		_display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		
		_holder = getHolder();
		_holder.addCallback(this);
		_holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		_camera = Camera.open();
		
		int rotation;
		
		switch (_display.getRotation())
		{
			case Surface.ROTATION_90:
				rotation = 0;
				break;
			case Surface.ROTATION_180:
				rotation = 270;
				break;
			case Surface.ROTATION_270:
				rotation = 180;
				break;
			default:
				rotation = 90;
				break;
		}
		
		try
		{
			_camera.setPreviewDisplay(_holder);
			_camera.setDisplayOrientation(rotation);
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
