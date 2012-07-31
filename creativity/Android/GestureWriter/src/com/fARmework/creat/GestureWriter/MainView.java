package com.fARmework.creat.GestureWriter;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

public class MainView extends View 
{
	private static final int INVALIDATE_RADIUS = 10;	
	
	private Bitmap _bitmap;
	private Canvas _canvas;
	private Paint _paint;
	
	public MainView(Context context) 
	{
		super(context);
		
		initialize();
	}
	
	public MainView(Context context, AttributeSet attributes)
	{
		super(context, attributes);
		
		initialize();
	}
	
	private void initialize()
	{
		_canvas = new Canvas();
		_paint = new Paint();
		
		_paint.setARGB(255, 255, 255, 255);
	}
	
	@Override
	protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight)
	{
		int currentWidth = 0;
		int currentHeight = 0;
		
		if(_bitmap != null)
		{
			currentWidth = _bitmap.getWidth();
			currentHeight = _bitmap.getHeight();
		}
		
		if(currentWidth >= width && currentHeight >= height)
		{
			return;
		}
		
		if(currentWidth < width)
		{
			currentWidth = width;
		}
		
		if(currentHeight < height)
		{
			currentHeight = height;
		}
		
		Bitmap bitmap = Bitmap.createBitmap(currentWidth, currentHeight,
				Bitmap.Config.RGB_565);
		
		Canvas canvas = new Canvas();
		canvas.setBitmap(bitmap);
		
		if(_bitmap != null)
		{
			canvas.drawBitmap(_bitmap, 0, 0, null);
		}
		
		_bitmap = bitmap;
		_canvas = canvas;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{					
		if(_bitmap != null)
		{	
			Rect rectangle = new Rect(	(int) event.getX() - INVALIDATE_RADIUS, 
										(int) event.getY() - INVALIDATE_RADIUS, 
										(int) event.getX() + INVALIDATE_RADIUS, 
										(int) event.getY() + INVALIDATE_RADIUS);	
			
			_canvas.drawPoint(event.getX(), event.getY(), _paint);
			
			invalidate(rectangle);
		}
		
		return true;
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{	
		if(_bitmap != null)
		{
			canvas.drawBitmap(_bitmap, 0, 0, null);
		}
	}
}
