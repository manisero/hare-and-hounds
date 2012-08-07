package com.fARmework.creat.GestureDetector.Processors;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.*;
import java.util.*;

public class DirectionalGestureProcessor implements IGestureProcessor<Integer> 
{
	@Override
	public Integer[][] getGestureGrid(GestureData data, int gridSize) 
	{
		Integer[][] grid = new Integer[gridSize][gridSize];
		
		Rectangle boundingBox = getGestureBoundingBox(data);
		
		int width = boundingBox.width;
		int height = boundingBox.height;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				grid[x][y] = 0;
			}
		}
		
		float xCell = (float) width / (float) gridSize;
		float yCell = (float) height / (float) gridSize;
		
		LinkedList<GestureData.Point> points = data.Points;
		
		int counter = 0;
		
		for(GestureData.Point point : points)
		{
			boolean gridFound = false;
			
			for(int x = 0; x < gridSize; ++x)
			{
				if(gridFound)
				{
					break;	
				}
				
				for(int y = 0; y < gridSize; ++y)
				{
					if(gridFound)
					{
						break;
					}
					
					float xStart = boundingBox.x + x * xCell;
					float xEnd = boundingBox.x + (x + 1) * xCell;
					float yStart = boundingBox.y + y * yCell;
					float yEnd = boundingBox.y + (y + 1) * yCell;
					
					if(	point.X >= xStart && point.X < xEnd && 
						point.Y >= yStart && point.Y < yEnd)
					{
						gridFound = true;
						
						if(grid[x][y] == 0 && (grid[x][y] != counter || counter == 0))
						{
							grid[x][y] = ++counter;
						}
					}
				}
			}
		}
		
		return grid;
	}
	
	private Rectangle getGestureBoundingBox(GestureData data) 
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
