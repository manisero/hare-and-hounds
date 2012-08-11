package com.fARmework.creat.GestureDetector.Utilities;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.*;
import java.util.*;

public class GestureRecognizerUtilities 
{
	public static Rectangle getGestureBoundingBox(GestureData data) 
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
	
	public static <T> boolean sizeCheck(T[][] input, T[][] pattern)
	{
		if(input.length != pattern.length || input.length == 0)
		{
			return false;
		}
		
		for(int i = 0; i < input.length; ++i)
		{
			if(input[i].length != pattern[i].length || input[i].length != input.length)
			{
				return false;
			}
		}
		
		return true;
	}
}
