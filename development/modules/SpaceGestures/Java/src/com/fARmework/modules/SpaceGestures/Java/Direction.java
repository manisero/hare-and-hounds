package com.fARmework.modules.SpaceGestures.Java;

public enum Direction
{
	Unknown				(Integer.parseInt("000000", 2)),
	
	Left				(Integer.parseInt("000001", 2)),
	Right				(Integer.parseInt("000010", 2)),
	Up					(Integer.parseInt("000100", 2)),
	Down				(Integer.parseInt("001000", 2)),
	Forward				(Integer.parseInt("010000", 2)),
	Backward			(Integer.parseInt("100000", 2)),
	
	LeftUp				(Integer.parseInt("000101", 2)),
	LeftDown			(Integer.parseInt("001001", 2)),
	LeftForward			(Integer.parseInt("010001", 2)),
	LeftBackward		(Integer.parseInt("100001", 2)),
	RightUp				(Integer.parseInt("000110", 2)),
	RightDown			(Integer.parseInt("001010", 2)),
	RightForward		(Integer.parseInt("010010", 2)),
	RightBackward		(Integer.parseInt("100010", 2)),
	UpForward			(Integer.parseInt("010100", 2)),
	UpBackward			(Integer.parseInt("100100", 2)),
	DownForward			(Integer.parseInt("011000", 2)),
	DownBackward		(Integer.parseInt("101000", 2)),
	
	LeftUpForward		(Integer.parseInt("010101", 2)),
	LeftUpBackward		(Integer.parseInt("100101", 2)),
	LeftDownForward		(Integer.parseInt("011001", 2)),
	LeftDownBackward	(Integer.parseInt("101001", 2)),
	RightUpForward		(Integer.parseInt("010110", 2)),
	RightUpBackward		(Integer.parseInt("100110", 2)),
	RightDownForward	(Integer.parseInt("011010", 2)),
	RightDownBackward	(Integer.parseInt("101010", 2));
	
	private final int _id;
	
	Direction(int id)
	{
		_id = id;
	}
	
	public int getValue()
	{
		return _id;
	}
	
	public static Direction valueOf(Integer value)
	{
		for (Direction direction : Direction.values())
		{
			if (direction.getValue() == value)
			{
				return direction;
			}
		}
		
		return Direction.Unknown;
	}
	
	public boolean hasParts(Direction... parts)
	{
		for (Direction part : parts)
		{
			if (!hasPart(part))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean hasPart(Direction part)
	{
		return (getValue() & part.getValue()) != Direction.Unknown.getValue();
	}
	
	public static Direction[] trimParts(Direction[] directions, Direction... parts)
	{
		Direction[] result = new Direction[directions.length];
		
		for (int i = 0; i < directions.length; ++i)
		{
			result[i] = directions[i].trimParts(parts);
		}
		
		return result;
	}
	
	public Direction trimParts(Direction... parts)
	{
		Direction result = this;
		
		for (Direction part : parts)
		{
			result = result.trimPart(part);
		}
		
		return result;
	}
	
	public Direction trimPart(Direction part)
	{
		return Direction.valueOf(getValue() & ~part.getValue());
	}
}
