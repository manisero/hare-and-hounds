package com.fARmework.modules.ScreenGestures.Java.Processing._impl;

import java.awt.*;
import java.util.*;

import com.fARmework.modules.ScreenGestures.Data.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.*;

public class ScreenGestureProcessor implements IScreenGestureProcessor
{
	@Override
	public Boolean[][] getGestureGrid(ScreenGestureData data, int gridSize)
	{
		Boolean[][] grid = new Boolean[gridSize][gridSize];
		
		Rectangle boundingBox = getGestureBoundingBox(data);
		
		float xCell = (float)boundingBox.width / (float)gridSize;
		float yCell = (float)boundingBox.height / (float)gridSize;
		
		for (ScreenGestureData.Point point : data.Points)
		{
			boolean gridFound = false;
			
			for (int x = 0; x < gridSize; ++x)
			{
				for (int y = 0; y < gridSize; ++y)
				{
					float xStart = boundingBox.x + x * xCell;
					float xEnd = boundingBox.x + (x + 1) * xCell;
					float yStart = boundingBox.y + y * yCell;
					float yEnd = boundingBox.y + (y + 1) * yCell;
					
					if (point.X >= xStart && point.X < xEnd && 
						point.Y >= yStart && point.Y < yEnd)
					{
						grid[y][x] = true;
						
						gridFound = true;
						break;
					}
				}
				
				if (gridFound)
				{
					break;	
				}
			}
		}
		
		return grid;
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
