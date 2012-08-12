package com.fARmework.modules.ScreenGestures.Java.Processing.GestureProcessors;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.*;
import java.util.*;

public class DirectionalScreenGestureProcessor extends ScreenGestureProcessorBase<Integer> 
{
	@Override
	public Integer[][] getGestureGrid(ScreenGestureData data, int gridSize) 
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
		
		LinkedList<ScreenGestureData.Point> points = data.Points;
		
		int counter = 0;
		
		for(ScreenGestureData.Point point : points)
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
						
						if(grid[y][x] == 0 && (grid[y][x] != counter || counter == 0))
						{
							grid[y][x] = ++counter;
						}
					}
				}
			}
		}
		
		return grid;
	}			
}
