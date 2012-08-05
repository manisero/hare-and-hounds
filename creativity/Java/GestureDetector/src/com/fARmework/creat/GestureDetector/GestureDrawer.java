package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class GestureDrawer 
{
	public BufferedImage drawGesture(GestureData data)
	{
		Rectangle boundingBox = getBoundingBox(data);
		
		BufferedImage image = new BufferedImage(boundingBox.width,
												boundingBox.height,
												BufferedImage.TYPE_BYTE_GRAY);
		
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
		
		return image;
	}
	
	private Rectangle getBoundingBox(GestureData data)
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
