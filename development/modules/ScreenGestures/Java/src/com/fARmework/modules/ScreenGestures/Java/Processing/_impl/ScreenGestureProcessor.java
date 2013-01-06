package com.fARmework.modules.ScreenGestures.Java.Processing._impl;

import java.awt.Rectangle;
import java.util.LinkedList;

import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData;
import com.fARmework.modules.ScreenGestures.Data.ScreenGestureData.Point;
import com.fARmework.modules.ScreenGestures.Java.Processing.IScreenGestureProcessor;

public class ScreenGestureProcessor implements IScreenGestureProcessor
{
	@Override
	public Boolean[][] getGestureGrid(ScreenGestureData data, int gridSize)
	{
		Boolean[][] grid = new Boolean[gridSize][gridSize];
		
		Rectangle boundingBox = getGestureBoundingBox(data);
		
		float xCell = (float)boundingBox.width / (float)gridSize;
		float yCell = (float)boundingBox.height / (float)gridSize;
		
		for (int x = 0; x < gridSize; ++x)
		{
			for (int y = 0; y < gridSize; ++y)
			{
				Point previousPoint = null;
				
				boolean hasPoint = false;
				
				for (LinkedList<Point> segment : data.Segments)
				{
					for (Point point : segment)
					{
						if (previousPoint != null)
						{
							float xStart = boundingBox.x + x * xCell;
							float yStart = boundingBox.y + y * yCell;
							
							Rectangle cell = new Rectangle((int) xStart, (int) yStart, (int) xCell, (int) yCell);
							
							if (cell.intersectsLine(previousPoint.X, previousPoint.Y, point.X, point.Y))
							{
								hasPoint = true;
								break;
							}
						}
					
						previousPoint = point;
					}
				}
				
				grid[y][x] = hasPoint;
			}
		}
		
		return grid;
	}
	
	private Rectangle getGestureBoundingBox(ScreenGestureData data) 
	{
		int xMin = 0;
		int xMax = 0;
		int yMin = 0;
		int yMax = 0;
		
		for (LinkedList<Point> segment : data.Segments)
		{
			for(ScreenGestureData.Point point : segment)
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
		}
		
		return new Rectangle(xMin, yMin, xMax - xMin + 1, yMax - yMin + 1);
	}
}
