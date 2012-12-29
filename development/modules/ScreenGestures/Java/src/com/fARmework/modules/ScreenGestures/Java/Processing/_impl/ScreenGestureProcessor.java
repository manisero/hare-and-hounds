package com.fARmework.modules.ScreenGestures.Java.Processing._impl;

import java.awt.*;
import java.util.*;

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
				
				for (Point point : data.Points)
				{
					if (previousPoint != null)
					{
						float xStart = boundingBox.x + x * xCell;
						float xEnd = boundingBox.x + (x + 1) * xCell;
						float yStart = boundingBox.y + y * yCell;
						float yEnd = boundingBox.y + (y + 1) * yCell;
						
						if (cellContainsPoint(xStart, yStart, xEnd, yEnd, previousPoint, point))
						{
							hasPoint = true;
							break;
						}
					}
				
					previousPoint = point;
				}
				
				grid[y][x] = hasPoint;
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
	
	private boolean cellContainsPoint(float xCellStart, float yCellStart, float xCellEnd, float yCellEnd, Point firstPoint, Point secondPoint)
	{
		if (firstPoint.X == secondPoint.X)
		{
			if (containsInterval(xCellStart, xCellEnd, firstPoint.X, firstPoint.X))
			{
				return true;
			}
		}
		else if (firstPoint.Y == secondPoint.Y)
		{
			if (containsInterval(yCellStart, yCellEnd, firstPoint.Y, firstPoint.Y))
			{
				return true;
			}
		}
		else
		{
			float a = (secondPoint.Y - firstPoint.Y) / (secondPoint.X - firstPoint.X);
			float b = firstPoint.Y - a * firstPoint.X;
			
			float y1 = a * xCellStart + b;
			float y2 = a * xCellEnd + b;
			
			float yMax = (y1 > y2) ? y1 : y2;
			float yMin = (y1 < y2) ? y1 : y2;
			
			if (containsInterval(yCellStart, yCellEnd, yMin, yMax))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean containsInterval(float begin, float end, float intervalBegin, float intervalEnd)
	{
		if ((intervalEnd >= begin && intervalEnd <= end) ||
			(intervalBegin >= begin && intervalBegin <= end) ||
			(intervalBegin < begin && intervalEnd > end))
		{
			return true;
		}
		
		return false;
	}
}
