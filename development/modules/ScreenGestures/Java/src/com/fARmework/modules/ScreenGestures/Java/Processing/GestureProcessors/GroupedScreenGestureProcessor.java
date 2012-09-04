package com.fARmework.modules.ScreenGestures.Java.Processing.GestureProcessors;

import java.awt.*;
import java.util.*;

import com.fARmework.modules.ScreenGestures.Data.*;

public class GroupedScreenGestureProcessor extends ScreenGestureProcessorBase<Character> 
{
	@Override
	public Character[][] getGestureGrid(ScreenGestureData data, int gridSize) 
	{
		Character[][] grid = new Character[gridSize][gridSize];
		
		Rectangle boundingBox = getGestureBoundingBox(data);
		
		int width = boundingBox.width;
		int height = boundingBox.height;
		
		float xCell = (float) width / (float) gridSize;
		float yCell = (float) height / (float) gridSize;
		
		LinkedList<ScreenGestureData.Point> points = data.Points;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				boolean hasPoint = false;
				
				for(ScreenGestureData.Point point : points)
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
					grid[y][x] = 't';
				}
				else
				{
					grid[y][x] = 'f';
				}
			}
		}
		
		return grid;
	}
}
