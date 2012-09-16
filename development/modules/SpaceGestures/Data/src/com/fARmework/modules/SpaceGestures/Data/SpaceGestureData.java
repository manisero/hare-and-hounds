package com.fARmework.modules.SpaceGestures.Data;

import java.util.LinkedList;

public class SpaceGestureData
{
	public enum Direction
	{
		Up, Down,
		Left, Right,
		Forward, Backward
	}
	
	public LinkedList<Direction> Directions;
	
	public SpaceGestureData()
	{
		Directions = new LinkedList<Direction>();
	}
	
	public SpaceGestureData(LinkedList<Direction> directions)
	{
		Directions = directions;
	}
	
	public void addDirection(Direction direction)
	{
		Directions.add(direction);
	}
}
