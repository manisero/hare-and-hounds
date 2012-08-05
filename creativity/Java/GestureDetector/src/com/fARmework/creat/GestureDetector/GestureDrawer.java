package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class GestureDrawer 
{
	private static int RECTANGLE_SIZE = 15;
	
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
		
		for(GestureData.Point point : points)
		{
			int x = (int) point.X - boundingBox.x;
			int y = (int) point.Y - boundingBox.y;
			
			image.setRGB(x, y, Color.black.getRGB());
		}
		
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		
		for(int x = 0; x < boundingBox.width; x += RECTANGLE_SIZE + 1)
		{
			for(int y = 0; y < boundingBox.height; y += RECTANGLE_SIZE + 1)
			{
				Rectangle rectangle = new Rectangle(x, y, RECTANGLE_SIZE, RECTANGLE_SIZE);
				
				if(isRectangleFilled(data, rectangle))
				{
					graphics.setColor(Color.RED);
				}
				else
				{
					graphics.setColor(Color.BLACK);
				}
				
				graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			}
		}
		
		graphics.dispose();
		
		return image;
	}
	
	private boolean isRectangleFilled(GestureData data, Rectangle rectangle)
	{
		LinkedList<GestureData.Point> points = data.Points;
		
		Rectangle boundingBox = getBoundingBox(data);
		
		for(GestureData.Point point : points)
		{
			int x = (int) point.X - boundingBox.x;
			int y = (int) point.Y - boundingBox.y;
			
			if(rectangle.contains(x, y))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Rectangle getBoundingBox(GestureData data)
	{
		LinkedList<GestureData.Point> points = data.Points;
		
		if(points.size() == 0)
		{
			return null;
		}
		
		Rectangle rectangle = new Rectangle((int) points.getFirst().X,
											(int) points.getFirst().Y,
											1,
											1);
		
		for(GestureData.Point point : points)
		{
			rectangle.add(point.X, point.Y);
		}
		
		return rectangle;
	}
}
