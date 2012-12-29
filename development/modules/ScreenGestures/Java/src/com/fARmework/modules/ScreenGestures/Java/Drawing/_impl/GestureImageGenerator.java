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
		
		Point previousPoint = null;
		
		for (Point point : gesture.Points)
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
		LinkedList<ScreenGestureData.Point> points = data.Points;
		
		int xMin, xMax, yMin, yMax;

		xMin = xMax = (int) points.getFirst().X;
		yMin = yMax = (int) points.getFirst().Y;
		
		for(ScreenGestureData.Point point : points)
		{
			if(xMin > point.X)
			{
				xMin = (int) point.X;
			}
			
			else if(xMax < point.X)
			{
				xMax = (int) point.X;
			}
			
			if(yMin > point.Y)
			{
				yMin = (int) point.Y;
			}
			
			else if(yMax < point.Y)
			{
				yMax = (int) point.Y;
			}
		}
		
		int x = xMin;
		int y = yMin;
		int width = xMax - xMin + 1;
		int height = yMax - yMin + 1;
		
		return new Rectangle(x, y, width, height);
	}
}
