package com.fARmework.creat.GestureDetector.Utilities;

import com.fARmework.modules.ScreenGestures.Data.*;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class GestureDrawer 
{	
	public BufferedImage drawGesture(GestureData data)
	{
		Rectangle boundingBox = getBoundingBox(data);
		
		BufferedImage image = new BufferedImage(boundingBox.width,
												boundingBox.height,
												BufferedImage.TYPE_INT_RGB);
		
		for(int x = 0; x < boundingBox.width; ++x)
		{
			for(int y = 0; y < boundingBox.height; ++y)
			{
				image.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
		
		LinkedList<GestureData.Point> points = data.Points;
		
		int pointsSize = points.size();
		int pointNo = 0;
		
		for(GestureData.Point point : points)
		{	
			++pointNo;
			
			double red = 255 * (double) pointNo / (double) pointsSize;
			
			int x = (int) point.X - boundingBox.x;
			int y = (int) point.Y - boundingBox.y;
			
			Color color = new Color((int) red, 0, 0, 0);
			
			image.setRGB(x, y, color.getRGB());
		}
		
		return image;
	}
	
	private Rectangle getBoundingBox(GestureData data)
	{
		LinkedList<GestureData.Point> points = data.Points;
		
		int xMin, xMax, yMin, yMax;
	
		xMin = xMax = (int) points.getFirst().X;
		yMin = yMax = (int) points.getFirst().Y;
		
		for(GestureData.Point point : points)
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
