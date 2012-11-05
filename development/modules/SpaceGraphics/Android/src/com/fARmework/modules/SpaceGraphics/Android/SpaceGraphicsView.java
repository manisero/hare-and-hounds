package com.fARmework.modules.SpaceGraphics.Android;

import gueei.binding.*;
import android.content.*;
import android.graphics.*;
import android.opengl.*;
import android.util.*;
import android.view.*;

import com.fARmework.modules.SpaceGraphics.Android.Models.*;
import com.fARmework.modules.SpaceGraphics.Android.Orientation.*;
import com.fARmework.modules.SpaceGraphics.Android.Orientation._impl.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection.*;
import com.fARmework.modules.SpaceGraphics.Android.Projection._impl.*;

public class SpaceGraphicsView extends GLSurfaceView implements IBindableView<SpaceGraphicsView>
{
	private static final String MODEl_ATTRIBUTE_NAME = "model";
	private static final String DIRECTION_PROVIDER_ATTRIBUTE_NAME = "directionProvider";
	
	private IGraphicsRenderer _graphicsRenderer;
	
	// model attribute (Android-Binding support)
	private ViewAttribute<SpaceGraphicsView, Model> _modelAttribute =
				new ViewAttribute<SpaceGraphicsView, Model>(Model.class, SpaceGraphicsView.this, MODEl_ATTRIBUTE_NAME)
				{
				    @Override
				    protected void doSetAttributeValue(final Object newValue)
				    {
				    	_graphicsRenderer.setModel((Model)newValue);
				    }
				
				    @Override
				    public Model get()
				    {
				    	return null;
				    }
				};
	
	// directionProvider attribute (Android-Binding support)
	private ViewAttribute<SpaceGraphicsView, IDirectionProvider> _directionProviderAttribute =
				new ViewAttribute<SpaceGraphicsView, IDirectionProvider>(IDirectionProvider.class, SpaceGraphicsView.this, DIRECTION_PROVIDER_ATTRIBUTE_NAME)
				{
				    @Override
				    protected void doSetAttributeValue(final Object newValue)
				    {
				    	_graphicsRenderer.setDirectionProvider((IDirectionProvider)newValue);
				    }
				
				    @Override
				    public IDirectionProvider get()
				    {
				    	return null;
				    }
				};
	
	public SpaceGraphicsView(Context context) 
	{
		super(context);
		initialize();
	}
	
	public SpaceGraphicsView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initialize();
	}
	
	public SpaceGraphicsView(Context context, IGraphicsRenderer graphicsRenderer) 
	{
		super(context);
		setGraphicsRenderer(graphicsRenderer);
		initialize();
	}
	
	private void initialize()
	{
		setEGLContextClientVersion(1);
		setEGLConfigChooser(8, 8, 8, 8, 8, 0 );
		getHolder().setFormat(PixelFormat.TRANSLUCENT);
		
		if (_graphicsRenderer == null)
		{
			setGraphicsRenderer(new GraphicsRenderer(new GLHandler(), new SensorOrientationProvider(new SensorManagerResolver(getContext()), new DisplayResolver(getContext()))));
		}
		
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
	
	public IGraphicsRenderer getGraphicsRenderer()
	{
		return _graphicsRenderer;
	}
	
	public void setGraphicsRenderer(IGraphicsRenderer graphicsRenderer)
	{
		_graphicsRenderer = graphicsRenderer;
		setRenderer(graphicsRenderer);
	}
	
	// Android-Binding support
	@Override
	public ViewAttribute<? extends View, ?> createViewAttribute(String attribute)
	{
		if (attribute.equals(MODEl_ATTRIBUTE_NAME))
		{
			return _modelAttribute;
		}
		else if (attribute.equals(DIRECTION_PROVIDER_ATTRIBUTE_NAME))
		{
			return _directionProviderAttribute;
		} 
		
		return null;
	}
}
