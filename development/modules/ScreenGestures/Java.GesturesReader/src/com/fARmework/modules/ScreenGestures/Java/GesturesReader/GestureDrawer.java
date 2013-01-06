package com.fARmework.modules.ScreenGestures.Java.GesturesReader;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class GestureDrawer 
{	
	public BufferedImage drawGesture(ScreenGestureData data)
	{
		Rectangle boundingBox = getBoundingBox(data);
		
		BufferedImage image = new BufferedImage(boundingBox.width,
												boundingBox.height,
												BufferedImage.TYPE_INT_RGB);
		
		for (int x = 0; x < boundingBox.width; ++x)
		{
			for (int y = 0; y < boundingBox.height; ++y)
			{
				image.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
		
		for (int i = 0; i != data.Segments.size(); i++)
		{
			LinkedList<ScreenGestureData.Point> points = data.Segments.get(i);
		
			int pointsSize = points.size();
			int pointNo = 0;
			
			for (ScreenGestureData.Point point : points)
			{	
				++pointNo;
				
				double red = 255 * (double) pointNo / (double) pointsSize;
				
				int x = (int) point.X - boundingBox.x;
				int y = (int) point.Y - boundingBox.y;
				
				Color color = new Color((int) red, 0, 0, 0);
				
				image.setRGB(x, y, color.getRGB());
			}
		}
		
		return image;
	}
	
	private Rectangle getBoundingBox(ScreenGestureData data)
	{
		int xMin = 0;
		int xMax = 0;
		int yMin = 0;
		int yMax = 0;
		
		for (LinkedList<ScreenGestureData.Point> segment : data.Segments)
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
