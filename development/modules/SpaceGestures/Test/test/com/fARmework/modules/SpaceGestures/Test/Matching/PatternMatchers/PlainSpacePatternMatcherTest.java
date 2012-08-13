package com.fARmework.modules.SpaceGestures.Test.Matching.PatternMatchers;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;

public class PlainSpacePatternMatcherTest 
{	
	@Test
	public void positiveMatchTest() 
	{
		PlainSpacePatternMatcher matcher = new PlainSpacePatternMatcher();
		
		Direction[] firstPattern = new Direction[5];
		Direction[] secondPattern = new Direction[5];
		
		firstPattern[0] = Direction.Left;
		firstPattern[1] = Direction.Right;
		firstPattern[2] = Direction.Right;
		firstPattern[3] = Direction.Up;
		firstPattern[4] = Direction.Backward;
		
		secondPattern[0] = Direction.Left;
		secondPattern[1] = Direction.Right;
		secondPattern[2] = Direction.Right;
		secondPattern[3] = Direction.Up;
		secondPattern[4] = Direction.Backward;
		
		assertEquals(true, matcher.match(firstPattern, secondPattern));
		
		firstPattern = new Direction[10];
		secondPattern = new Direction[10];
		
		firstPattern[0] = Direction.Forward;
		firstPattern[1] = Direction.Backward;
		firstPattern[2] = Direction.Down;
		firstPattern[3] = Direction.Up;
		firstPattern[4] = Direction.Left;
		firstPattern[5] = Direction.Right;
		firstPattern[6] = Direction.Left;
		firstPattern[7] = Direction.Left;
		firstPattern[8] = Direction.Down;
		firstPattern[9] = Direction.Forward;
		
		secondPattern[0] = Direction.Forward;
		secondPattern[1] = Direction.Backward;
		secondPattern[2] = Direction.Down;
		secondPattern[3] = Direction.Up;
		secondPattern[4] = Direction.Left;
		secondPattern[5] = Direction.Right;
		secondPattern[6] = Direction.Left;
		secondPattern[7] = Direction.Left;
		secondPattern[8] = Direction.Down;
		secondPattern[9] = Direction.Forward;		
		
		assertEquals(true, matcher.match(firstPattern, secondPattern));
	}
	
	@Test
	public void negativeMatchTest()
	{
		PlainSpacePatternMatcher matcher = new PlainSpacePatternMatcher();
		
		Direction[] firstPattern = new Direction[4];
		Direction[] secondPattern = new Direction[4];		
		
		firstPattern[0] = Direction.Left;
		firstPattern[1] = Direction.Up;
		firstPattern[2] = Direction.Right;
		firstPattern[3] = Direction.Down;
		
		secondPattern[0] = Direction.Up;
		secondPattern[1] = Direction.Right;
		secondPattern[2] = Direction.Down;
		secondPattern[3] = Direction.Left;
		
		assertEquals(false, matcher.match(firstPattern, secondPattern));
		
		firstPattern = new Direction[3];
		secondPattern = new Direction[4];
		
		firstPattern[0] = Direction.Forward;
		firstPattern[1] = Direction.Forward;
		firstPattern[2] = Direction.Forward;
		
		secondPattern[0] = Direction.Forward;
		secondPattern[1] = Direction.Forward;
		secondPattern[2] = Direction.Forward;
		secondPattern[3] = Direction.Forward;
		
		assertEquals(false, matcher.match(firstPattern, secondPattern));
		
		firstPattern = new Direction[7];
		secondPattern = new Direction[7];
		
		firstPattern[0] = Direction.Left;
		firstPattern[1] = Direction.Backward;
		firstPattern[2] = Direction.Up;
		firstPattern[3] = Direction.Forward;
		firstPattern[4] = Direction.Left;
		firstPattern[5] = Direction.Right;
		firstPattern[6] = Direction.Left;
		
		secondPattern[0] = Direction.Up;
		secondPattern[1] = Direction.Forward;
		secondPattern[2] = Direction.Up;
		secondPattern[3] = Direction.Left;
		secondPattern[4] = Direction.Backward;
		secondPattern[5] = Direction.Down;
		secondPattern[6] = Direction.Right;
		
		assertEquals(false, matcher.match(firstPattern, secondPattern));
	}
}
