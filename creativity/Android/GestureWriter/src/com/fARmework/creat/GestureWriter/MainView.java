package com.fARmework.creat.GestureWriter;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import com.fARmework.modules.ScreenGestures.Data.*;
import java.io.*;
import java.util.*;

public class MainView extends View 
{
	private static final int INVALIDATE_RADIUS = 10;	
	private static final String FILENAME = "gestures.dat";
	
	private Bitmap _bitmap;
	private Canvas _canvas;
	private Paint _paint;
	
	private LinkedList<GestureData> _gestures;
	private GestureData _currentGesture;
	
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
	
	public MainView(Context context, AttributeSet attributes, int defStyle)
	{
		super(context, attributes, defStyle);
		
		initialize();
	}
	
	private void initialize()
	{
		_canvas = new Canvas();
		_paint = new Paint();
		_gestures = new LinkedList<GestureData>();
		_currentGesture = new GestureData();
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
		
		clear();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{	
		_currentGesture.addPoint(event.getX(), event.getY());
		
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
			_paint.setARGB(255, 0, 0, 0);
			canvas.drawBitmap(_bitmap, 0, 0, null);
		}
	}
	
	public void clear()
	{	
		if(_gestures != null && _currentGesture != null)
		{
			_gestures.add(_currentGesture);
			_currentGesture = new GestureData();
		}
		
		if(_bitmap != null)
		{
			_paint.setARGB(255, 255, 255, 255);
			_canvas.drawPaint(_paint);	
			invalidate();
		}
	}
	
	public void save()
	{
		clear();
		
		BufferedWriter writer = null;
		
		try
		{
			writer = new BufferedWriter(
						new OutputStreamWriter(
							getContext().openFileOutput(
								FILENAME, 
								Context.MODE_WORLD_WRITEABLE)));
			
			writer.write(_gestures.size() + "\n");
			
			for(GestureData data : _gestures)
			{
				LinkedList<GestureData.Point> points = data.Points;
				
				writer.write(points.size() + "\n");
				
				for(GestureData.Point point : points)
				{
					writer.write(point.X + " " + point.Y + "\n");
				}
			}
		}
		catch(Exception e)
		{
			Log.d("Exception", e.getMessage());
		}
		finally 
		{
			if(writer != null)
			{
				try
				{
					writer.close();
				}
				catch(IOException e)
				{
					Log.d("Exception", e.getMessage());
				}
			}
		}
		
		_gestures.clear();
	}
}
