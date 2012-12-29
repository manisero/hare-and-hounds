package com.fARmework.modules.ScreenGestures.Java.Processing._impl;

import java.awt.*;
import java.lang.reflect.*;
import java.util.*;

import com.fARmework.modules.ScreenGestures.Data.*;
import com.fARmework.modules.ScreenGestures.Java.Processing.*;

public class ScreenGestureProcessor implements IScreenGestureProcessor<java.util.List<Integer>>
{
	@Override
	public java.util.List<Integer>[][] getGestureGrid(ScreenGestureData data, int gridSize)
	{
		java.util.List<Integer>[][] grid = createGrid(gridSize);
		
		Rectangle boundingBox = getGestureBoundingBox(data);
		
		float xCell = (float)boundingBox.width / (float)gridSize;
		float yCell = (float)boundingBox.height / (float)gridSize;
		
		int counter = 0;
		
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
						if (!grid[y][x].contains(counter))
						{
							counter++;
							grid[y][x].add(counter);
						}
						
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
	
	@SuppressWarnings("unchecked")
	private java.util.List<Integer>[][] createGrid(int size)
	{
		java.util.List<Integer>[][] grid = (java.util.List<Integer>[][])Array.newInstance(java.util.List.class, size, size);
		
		for (int i = 0; i != size; i++)
		{
			for (int j = 0; j != size; j++)
			{
				grid[i][j] = new ArrayList<Integer>();
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
