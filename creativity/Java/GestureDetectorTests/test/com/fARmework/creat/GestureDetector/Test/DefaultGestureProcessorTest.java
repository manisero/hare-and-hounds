package com.fARmework.creat.GestureDetector.Test;

import com.fARmework.creat.GestureDetector.*;
import com.fARmework.creat.GestureDetector.DefaultImpl.*;
import com.fARmework.modules.ScreenGestures.Data.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class DefaultGestureProcessorTest 
{
	@Test
	public void testGetGrid()
	{
		GestureData data = new GestureData();
		
		data.addPoint(10, 10);
		data.addPoint(20, 20);
		data.addPoint(10, 20);
		
		IGestureProcessor processor = new DefaultGestureProcessor();
		
		boolean[][] grid = processor.getGestureGrid(data, 2);
		
		assertEquals("First cell", true, grid[0][0]);
		assertEquals("Second cell", false, grid[1][0]);
		assertEquals("Third cell", true, grid[1][1]);
		assertEquals("Fourth cell", true, grid[0][1]);
		
		data = new GestureData();
		
		data.addPoint(20, 10);
		data.addPoint(30, 20);
		data.addPoint(10, 30);
		
		grid = processor.getGestureGrid(data, 3);
		
		assertEquals("First cell", false, grid[0][0]);
		assertEquals("Second cell", true, grid[1][0]);
		assertEquals("Third cell", false, grid[2][0]);
		assertEquals("Fourth cell", false, grid[0][1]);
		assertEquals("Fifth cell", false, grid[1][1]);
		assertEquals("Sixth cell", true, grid[2][1]);
		assertEquals("Seventh cell", true, grid[0][2]);
		assertEquals("Eighth cell", false, grid[1][2]);
		assertEquals("Ninth cell", false, grid[2][2]);
	}
	
	@Test
	public void testGetOrientedGrid() 
	{		
		GestureData data = new GestureData();
		
		data.addPoint(10, 10);
		data.addPoint(20, 10);
		data.addPoint(20, 20);
		data.addPoint(10, 20);
		
		IGestureProcessor processor = new DefaultGestureProcessor();
		
		int[][] grid = processor.getOrientedGrid(data, 2);
		
		assertEquals("First cell", 1, grid[0][0]);
		assertEquals("Second cell", 2, grid[1][0]);
		assertEquals("Third cell", 3, grid[1][1]);
		assertEquals("Fourth cell", 4, grid[0][1]);
		
		data = new GestureData();
		
		data.addPoint(10, 10);
		data.addPoint(30, 30);
		data.addPoint(30, 20);
		data.addPoint(10, 30);
		data.addPoint(20, 20);
		data.addPoint(20, 30);
		data.addPoint(20, 10);
		data.addPoint(10, 20);
		data.addPoint(30, 10);
		
		grid = processor.getOrientedGrid(data, 3);
		
		assertEquals("First cell", 1, grid[0][0]);
		assertEquals("Second cell", 7, grid[1][0]);
		assertEquals("Third cell", 9, grid[2][0]);
		assertEquals("Fourth cell", 8, grid[0][1]);
		assertEquals("Fifth cell", 5, grid[1][1]);
		assertEquals("Sixth cell", 3, grid[2][1]);
		assertEquals("Seventh cell", 4, grid[0][2]);
		assertEquals("Eighth cell", 6, grid[1][2]);
		assertEquals("Ninth cell", 2, grid[2][2]);
	}
}
