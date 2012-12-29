package com.fARmework.modules.ScreenGestures.Java.Drawing._impl;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;
import com.fARmework.modules.ScreenGestures.Java.Drawing.IGestureImageGenerator;
import com.fARmework.modules.ScreenGestures.Java.Drawing.IGestureImageViewer;
import com.google.inject.Inject;

public class GestureImageViewer implements IGestureImageViewer
{
	private IGestureImageGenerator _imageGenerator;
	
	@Inject
	public GestureImageViewer(IGestureImageGenerator imageGenerator)
	{
		_imageGenerator = imageGenerator;
	}
	
	@Override
	public void showGesture(ScreenGestureData gesture)
	{
		showGesture(gesture, 0);
	}

	@Override
	public void showGesture(ScreenGestureData gesture, int gridSize)
	{
		final BufferedImage gestureImage = (gridSize > 0) ? 
				_imageGenerator.generateImage(gesture, gridSize) :
				_imageGenerator.generateImage(gesture);
		
		JFrame frame = new JFrame("Gesture");
		
		frame.setContentPane(new Panel()
		{
			private static final long serialVersionUID = 2047087041008430980L;

			@Override
			public void paint(Graphics graphics)
			{
				graphics.drawImage(gestureImage, 0, 0, null);
			}
		});
		
		frame.pack();
		frame.setVisible(true);
	}
}
