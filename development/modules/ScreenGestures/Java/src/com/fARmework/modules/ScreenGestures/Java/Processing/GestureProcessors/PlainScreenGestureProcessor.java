package com.fARmework.modules.ScreenGestures.Java.Processing.GestureProcessors;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.*;
import java.util.*;

public class PlainScreenGestureProcessor extends ScreenGestureProcessorBase<Boolean>
{
	@Override
	public Boolean[][] getGestureGrid(ScreenGestureData data, int gridSize) 
	{
		Boolean[][] grid = new Boolean[gridSize][gridSize];
		
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
					grid[y][x] = true;
				}
				else
				{
					grid[y][x] = false;
				}
			}
		}
		
		return grid;
	}			
}
