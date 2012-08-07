package com.fARmework.creat.GestureDetector;

import com.fARmework.modules.ScreenGestures.Data.*;

public interface IGestureProcessor 
{		
	boolean[][] getGestureGrid(GestureData data, int gridSize);
	
	int[][] getOrientedGrid(GestureData data, int gridSize);
	
	double getMatchPercentage(boolean[][] input, boolean[][] pattern);
	
	double getMatchPercentage(int[][] input, int[][] pattern);
}
