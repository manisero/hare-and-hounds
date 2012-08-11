package com.fARmework.creat.GestureDetector.Processors;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.*;
import java.util.*;

public class PlainGestureProcessor extends GestureProcessorBase<Boolean>
{
	@Override
	public Boolean[][] getGestureGrid(GestureData data, int gridSize) 
	{
		Boolean[][] grid = new Boolean[gridSize][gridSize];
		
		Rectangle boundingBox = getGestureBoundingBox(data);
		
		int width = boundingBox.width;
		int height = boundingBox.height;
		
		float xCell = (float) width / (float) gridSize;
		float yCell = (float) height / (float) gridSize;
		
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
}
