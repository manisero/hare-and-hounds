package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class GestureDrawer 
{
	private static final int RECTANGLES = 8;
	
	public BufferedImage drawGesture(GestureData data)
	{
		Rectangle boundingBox = getBoundingBox(data);
		
		BufferedImage image = new BufferedImage(boundingBox.width,
												boundingBox.height,
												BufferedImage.TYPE_INT_RGB);
		
		for(int x = 0; x < boundingBox.width; ++x)
		{
			for(int y = 0; y < boundingBox.height; ++y)
			{
				image.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
		
		LinkedList<GestureData.Point> points = data.Points;
		
		for(GestureData.Point point : points)
		{	
			int x = (int) point.X - boundingBox.x;
			int y = (int) point.Y - boundingBox.y;
			
			image.setRGB(x, y, Color.black.getRGB());
		}
		
		boolean[][] grid = getGrid(data, boundingBox, RECTANGLES);
		
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		
		for(int x = 0; x < RECTANGLES; ++x)
		{
			for(int y = 0; y < RECTANGLES; ++y)
			{
				if(grid[x][y])
				{
					graphics.setColor(Color.RED);
				
					int rectangleWidth = (int) boundingBox.width / RECTANGLES;
					int rectangleHeight = (int) boundingBox.height / RECTANGLES;
					int xRectangle = x * rectangleWidth;
					int yRectangle = y * rectangleHeight;
				
					graphics.drawRect(xRectangle, yRectangle, rectangleWidth, rectangleHeight);
				}
			}
		}
		
		return image;
	}
	
	public String getTitle(GestureData data)
	{
		String title = "Match percentage: ";
		
		boolean[][] pattern = {	{ true, true, true, true, true, true, true, true },
								{ true, false, false, false, false, false, false, true },
								{ true, false, false, false, false, false, false, true },
								{ true, false, false, false, false, false, false, true },
								{ true, false, false, false, false, false, false, true },
								{ true, false, false, false, false, false, false, true },
								{ true, false, false, false, false, false, false, true },
								{ true, true, true, true, true, true, true, true }	};
		
		Rectangle boundingBox = getBoundingBox(data);
		
		boolean[][] grid = getGrid(data, boundingBox, RECTANGLES);
		
		double match = getPatternMatchPercentage(RECTANGLES, grid, pattern);
		
		return title + match * 100 + "%";
	}
	
	public Rectangle getBoundingBox(GestureData data)
	{
		LinkedList<GestureData.Point> points = data.Points;
		
		if(points.size() == 0)
		{
			return null;
		}
		
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
	
	/**
	 * Returns grid 
	 * 
	 * @param gridSize
	 * @return
	 */
	public boolean[][] getGrid(GestureData data, Rectangle boundingBox, int gridSize)
	{
		boolean[][] grid = new boolean[gridSize][gridSize];
		
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
	
	public double getPatternMatchPercentage(int size, boolean[][] input, boolean [][] pattern)
	{
		double total = 0.0;
		double matches = 0.0;
		
		for(int x = 0; x < size; ++x)
		{
			for(int y = 0; y < size; ++y)
			{
				++total;
				
				if(input[x][y] == pattern[x][y])
				{
					++matches;
				}
			}
		}
		
		return matches / total;
	}
}
