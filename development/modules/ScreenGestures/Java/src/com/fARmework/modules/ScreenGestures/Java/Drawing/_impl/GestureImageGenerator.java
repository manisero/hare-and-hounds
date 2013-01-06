package com.fARmework.modules.ScreenGestures.Java.Drawing._impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;
import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData.Point;
import com.fARmework.modules.ScreenGestures.Java.Drawing.IGestureImageGenerator;

public class GestureImageGenerator implements IGestureImageGenerator
{
	@Override
	public BufferedImage generateImage(ScreenGestureData gesture)
	{
		Rectangle boundingBox = getGestureBoundingBox(gesture);
		
		BufferedImage plainImage = new BufferedImage(boundingBox.width, boundingBox.height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D plainImageGraphics = plainImage.createGraphics();
		
		plainImageGraphics.setBackground(Color.WHITE);
		plainImageGraphics.fillRect(0, 0, plainImage.getWidth(), plainImage.getHeight());
		plainImageGraphics.setColor(Color.BLACK);
		plainImageGraphics.setStroke(new BasicStroke(1));
		
		for (LinkedList<Point> segment : gesture.Segments)
		{
			Point previousPoint = null;
			
			for (Point point : segment)
			{
				int relativeX = (int) point.X - boundingBox.x;
				int relativeY = (int) point.Y - boundingBox.y;
				
				plainImage.setRGB(relativeX, relativeY, Color.BLACK.getRGB());
				
				if (previousPoint != null)
				{
					int previousRelativeX = (int) previousPoint.X - boundingBox.x;
					int previousRelativeY = (int) previousPoint.Y - boundingBox.y;
					
					plainImageGraphics.drawLine(previousRelativeX, previousRelativeY, relativeX, relativeY);
				}
				
				previousPoint = point;
			}
		}
		
		return plainImage;
	}

	@Override
	public BufferedImage generateImage(ScreenGestureData gesture, int gridSize)
	{
		BufferedImage image = generateImage(gesture);
		
		double cellWidth = image.getWidth() / (double) gridSize;
		double cellHeight = image.getHeight() / (double) gridSize;
		
		Graphics2D graphics2D = image.createGraphics();
		
		graphics2D.setColor(Color.RED);
		graphics2D.setStroke(new BasicStroke(2));
		
		for (int i = 1; i < gridSize; ++i)
		{
			graphics2D.drawLine(0, (int) (i * cellHeight), image.getWidth(), (int) (i * cellHeight));
			graphics2D.drawLine((int) (i * cellWidth), 0, (int) (i * cellWidth), image.getHeight());
		}
		
		return image;
	}
	
	private Rectangle getGestureBoundingBox(ScreenGestureData data) 
	{
		if (data.Segments.isEmpty() || data.Segments.get(0).isEmpty())
		{
			return null;
		}
		
		Point firstPoint = data.Segments.get(0).get(0);
		
		int xMin = (int)firstPoint.X;
		int xMax = (int)firstPoint.X;
		int yMin = (int)firstPoint.Y;
		int yMax = (int)firstPoint.Y;
		
		for (LinkedList<Point> segment : data.Segments)	
		{
			for (ScreenGestureData.Point point : segment)
			{
				if (xMin > point.X)
				{
					xMin = (int) point.X;
				}
				else if (xMax < point.X)
				{
					xMax = (int) point.X;
				}
				
				if (yMin > point.Y)
				{
					yMin = (int) point.Y;
				}
				else if (yMax < point.Y)
				{
					yMax = (int) point.Y;
				}
			}
		}
		
		return new Rectangle(xMin, yMin, xMax - xMin + 1, yMax - yMin + 1);
	}
}
