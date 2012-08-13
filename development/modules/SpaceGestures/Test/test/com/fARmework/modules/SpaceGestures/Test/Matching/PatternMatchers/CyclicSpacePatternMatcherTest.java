package com.fARmework.modules.SpaceGestures.Test.Matching.PatternMatchers;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class CyclicSpacePatternMatcherTest 
{
	@Test
	public void positiveMatchTest() 
	{
		CyclicSpacePatternMatcher matcher = new CyclicSpacePatternMatcher();
		
		Direction[] firstPattern = new Direction[4];
		Direction[] secondPattern = new Direction[4];
		
		firstPattern[0] = Direction.Left;
		firstPattern[1] = Direction.Up;
		firstPattern[2] = Direction.Right;
		firstPattern[3] = Direction.Down;
		
		secondPattern[0] = Direction.Left;
		secondPattern[1] = Direction.Up;
		secondPattern[2] = Direction.Right;
		secondPattern[3] = Direction.Down;
		
		assertTrue(matcher.match(firstPattern, secondPattern));
		
		secondPattern[0] = Direction.Right;
		secondPattern[1] = Direction.Down;
		secondPattern[2] = Direction.Left;
		secondPattern[3] = Direction.Up;
		
		assertTrue(matcher.match(firstPattern, secondPattern));
		
		firstPattern = new Direction[6];
		secondPattern = new Direction[6];
		
		firstPattern[0] = Direction.Left;
		firstPattern[1] = Direction.Up;
		firstPattern[2] = Direction.Right;
		firstPattern[3] = Direction.Down;
		firstPattern[4] = Direction.Forward;
		firstPattern[5] = Direction.Backward;
		
		secondPattern[0] = Direction.Down;
		secondPattern[1] = Direction.Forward;
		secondPattern[2] = Direction.Backward;
		secondPattern[3] = Direction.Left;
		secondPattern[4] = Direction.Up;
		secondPattern[5] = Direction.Right;
		
		assertTrue(matcher.match(firstPattern, secondPattern));
	}
	
	@Test
	public void negativeMatchTest()
	{
		CyclicSpacePatternMatcher matcher = new CyclicSpacePatternMatcher();
		
		Direction[] firstPattern = new Direction[4];
		Direction[] secondPattern = new Direction[3];
		
		firstPattern[0] = Direction.Left;
		firstPattern[1] = Direction.Backward;
		firstPattern[2] = Direction.Right;
		firstPattern[3] = Direction.Down;
		
		secondPattern[0] = Direction.Left;
		secondPattern[1] = Direction.Backward;
		secondPattern[2] = Direction.Right;
		
		assertFalse(matcher.match(firstPattern, secondPattern));
		
		secondPattern = new Direction[4];
		
		secondPattern[0] = Direction.Left;
		secondPattern[1] = Direction.Down;
		secondPattern[2] = Direction.Right;
		secondPattern[3] = Direction.Backward;
		
		assertFalse(matcher.match(firstPattern, secondPattern));
		
		firstPattern = new Direction[5];
		secondPattern = new Direction[5];
		
		firstPattern[0] = Direction.Right;
		firstPattern[1] = Direction.Right;
		firstPattern[2] = Direction.Right;
		firstPattern[3] = Direction.Right;
		firstPattern[4] = Direction.Right;
		
		secondPattern[0] = Direction.Right;
		secondPattern[1] = Direction.Right;
		secondPattern[2] = Direction.Left;
		secondPattern[3] = Direction.Right;
		secondPattern[4] = Direction.Right;
		
		assertFalse(matcher.match(firstPattern, secondPattern));
	}
}
