package com.fARmework.create.GestureDetector.DefaultImpl;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.modules.ScreenGestures.Data.*;

import java.awt.*;
import java.util.*;

public class DefaultGestureProcessor implements IGestureProcessor 
{	
	@Override
	public boolean[][] getGestureGrid(GestureData data, int gridSize) 
	{
		boolean[][] grid = new boolean[gridSize][gridSize];
		
		Rectangle boundingBox = getGestureBoundingBox(data);
		
		int width = boundingBox.width;
		int height = boundingBox.height;
		
		float xCell = width / (float) gridSize;
		float yCell = height / (float) gridSize;
		
		LinkedList<GestureData.Point> points = data.Points;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				boolean hasPoint = false;
				
				for(GestureData.Point point : points)
				{
					float xStart = boundingBox.x + x * xCell;
					float xEnd = boundingBox.x + (x + 1) * xCell;
					float yStart = boundingBox.y + y * yCell;
					float yEnd = boundingBox.y + (y + 1) * yCell;
					
					if(	point.X >= xStart && point.X < xEnd && 
						point.Y >= yStart && point.Y < yEnd)
					{
						hasPoint = true;
						break;
					}
				}
				
				if(hasPoint)
				{
					grid[x][y] = true;
				}
				else
				{
					grid[x][y] = false;
				}
			}
		}
		
		return grid;
	}

	@Override
	public double getMatchPercentage(boolean[][] input, boolean[][] pattern) 
	{
		if(!sizeCheck(input, pattern))
		{
			return 0.0;
		}
		
		int gridSize = input.length;
		
		int total = 0;
		int matches = 0;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				++total;
				
				if(input[x][y] == pattern[x][y])
				{
					++matches;
				}
			}
		}
		
		return (double) matches / (double) total;
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
	
	private boolean sizeCheck(boolean[][] input, boolean[][] pattern)
	{
		int inputGridSize = input.length;
		int patternGridSize = pattern.length;
		
		if(inputGridSize != patternGridSize)
		{
			return false;
		}
		
		for(int i = 0; i < inputGridSize; ++i)
		{
			if(input[i].length != inputGridSize || pattern[i].length != inputGridSize)
			{
				return false;
			}
		}		
		
		return true;
	}
}
