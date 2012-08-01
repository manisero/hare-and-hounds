package com.fARmework.RockPaperScissors.Server.Logic.DataHandlers;

import com.fARmework.core.server.Connection.IDataHandler;
import com.fARmework.modules.ScreenGestures.Data.GestureData;
import com.fARmework.modules.ScreenGestures.Data.GestureData.Point;
import java.awt.*;
import java.util.*;

public class GestureProcessor implements IDataHandler<GestureData>
{
	@Override
	public void handle(int clientID, GestureData data)
	{
		System.out.println("Processing gesture data...");
		
		for (Point point : data.Points)
		{
			System.out.println(point.X + ", " + point.Y);
		}
		
		printInvariants(data.Points);
		
		System.out.println("Finished processing gesture data");
	}
	
	public void printInvariants(LinkedList<Point> points)
	{
		if(points.isEmpty())
		{
			return;
		}
		
		Rectangle boundingBox = getBoundingBox(points);
		
		boolean[][] area = getAreaAsArray(points, boundingBox);
		
		double[] invariants = calculateInvariants(area);
		
		System.out.println(" ");
		System.out.println("Invariants: ");
		
		for(int i = 1; i <= 10; ++i)
		{
			System.out.println("M" + i + ": " + invariants[i - 1]);
		}
		
		System.out.println(" ");
	}
	
	public Rectangle getBoundingBox(LinkedList<Point> points)
	{
		int x = (int) points.getFirst().X;
		int y = (int) points.getFirst().Y;
		
		Rectangle boundingBox = new Rectangle(x, y, 0, 0);
		
		for(Point point : points)
		{
			boundingBox.add(new java.awt.Point((int) point.X, (int) point.Y));
		}
		
		return boundingBox;
	}
	
	public boolean[][] getAreaAsArray(LinkedList<Point> points, Rectangle boundingBox)
	{
		int height = boundingBox.height;
		int width = boundingBox.width;
		
		boolean[][] area = new boolean[width + 1][height + 1];
		
		for(int x = 0; x < width; ++x)
		{
			for(int y = 0; y < height; ++y)
			{
				area[x][y] = false;
			}
		}
		
		int left = boundingBox.x;
		int top = boundingBox.y;
		
		for(Point point : points)
		{
			int x = (int) point.X - left;
			int y = (int) point.Y - top;
			
			System.out.println("Added new point - x: " + x + " y: " + y);
			area[x][y] = true;
		}
		
		return area;
	}
	
	public double[] calculateInvariants(boolean[][] area)
	{
		int width = area.length;
		int height = area[0].length;
		
		double 	m00 = 0.0, m01 = 0.0, m10 = 0.0, m11 = 0.0, m20 = 0.0, m02 = 0.0,
				m30 = 0.0, m03 = 0.0, m21 = 0.0, m12 = 0.0;
		
		for(int x = 0; x < width; ++x)
		{
			for(int y = 0; y < height; ++y)
			{
				if(area[x][y])
				{
					int i = x + 1;
					int j = y + 1;
					
					m00 += 1;
					m10 += i;
					m01 += j;
					m11 += i * j;
					m20 += i * i;
					m02 += j * j;
					m30 += i * i * i;
					m03 += j * j * j;
					m21 += i * i * j;
					m12 += i * j * j;
				}
			}
		}
		
		double iPrime = m10 / m00;
		double jPrime = m01 / m00;
		
		double M20 = m20 - (m10 * m10) / m00;
		double M02 = m02 - (m01 * m01) / m00;
		double M11 = m11 - (m10 * m01) / m00;
		double M21 = m21 - 2 * m11 * iPrime - m20 * jPrime + 2 * m01 * iPrime * iPrime;
		double M12 = m12 - 2 * m11 * jPrime - m02 * iPrime + 2 * m10 * jPrime * jPrime;
		double M30 = m30 - 3 * m20 * iPrime + 2 * m10 * iPrime * iPrime;
		double M03 = m03 - 3 * m02 * jPrime + 2 * m01 * jPrime * jPrime;
		
		double M1 = (M20 + M02) / (m00 * m00);
		
		double M2 = ((M20 + M02) * (M20 + M02) + 4 * M11 * M11);
		M2 /= (m00 * m00 * m00 * m00);
		
		double M3 = (M30 + 3 * M12) * (M30 + 3 * M12) + (3 * M21 - M03) * (3 * M21 - M03);
		M3 /= (m00 * m00 * m00 * m00 * m00);
		
		double M4 = (M30 + M12) * (M30 + M12) + (M21 - M03) * (M21 - M03);
		M4 /= (m00 * m00 * m00 * m00 * m00);
		
		double M5 = (M30 - 3 * M12) * (M30 + M12) * ((M30 + M12) * (M30 + M12) - 3 * (M21 + M03) * (M21 + M03));
		M5 += (3 * M21 - M03) * (M21 + M03) * (3 * (M30 + M12) * (M30 + M12) - (M21 + M03) * (M21 + M03));
		M5 /= (m00 * m00 * m00 * m00 * m00 * m00 * m00 * m00 * m00 * m00);
		
		double M6 = (M20 - M02) * ((M30 + M12) * (M30 + M12) - (M21 + M03) * (M21 + M03));
		M6 += 4 * M11 * (M30 + M12) * (M21 + M03);
		M6 /= (m00 * m00 * m00 * m00 * m00 * m00 * m00);
		
		double M7 = (M20 * M02 - M11 * M11) / (m00 * m00 * m00 * m00);
		
		double M8 = (M30 * M12 + M21 * M03 - M12 * M12 - M21 * M21) / (m00 * m00 * m00 * m00 * m00);
		
		double M9 = M20 * (M21 * M03 - M12 * M12) + M02 * (M03 * M12 - M21 * M21);
		M9 -= M11 * (M30 * M03 - M21 * M12);
		M9 /= (m00 * m00 * m00 * m00 * m00 * m00 * m00);
		
		double M10 = (M30 * M03 - M12 * M21) * (M30 * M03 - M12 * M21);
		M10 -= 4 * (M30 * M12 - M21 * M21) * (M03 * M21 - M12);
		M10 /= (m00 * m00 * m00 * m00 * m00 * m00 * m00 * m00 * m00 * m00);
		
		double[] invariants = { M1, M2, M3, M4, M5, M6, M7, M8, M9, M10 };
		
		return invariants;
	}
}
