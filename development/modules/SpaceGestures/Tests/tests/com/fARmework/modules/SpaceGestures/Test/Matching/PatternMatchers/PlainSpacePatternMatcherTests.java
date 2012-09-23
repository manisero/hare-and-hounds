package com.fARmework.modules.SpaceGestures.Test.Matching.PatternMatchers;

import com.fARmework.modules.SpaceGestures.Data.SpaceGestureData.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{ 
			PlainSpacePatternMatcherTests.TheMatchMethod.class 
		})
public class PlainSpacePatternMatcherTests 
{	
	public static class TheMatchMethod
	{
		@Test
		public void MatchesExactSamePatterns()
		{
			PlainSpacePatternMatcher matcher = new PlainSpacePatternMatcher();
			
			Direction[] moves =
			{
					Direction.Left,
					Direction.Right,
					Direction.Right,
					Direction.Up,
					Direction.Backward
			};
			
			Direction[] pattern =
			{
					Direction.Left,
					Direction.Right,
					Direction.Right,
					Direction.Up,
					Direction.Backward
			};
								
			assertTrue(matcher.match(moves, pattern));			
		}
		
		@Test 
		public void DiffersDistinctPatterns()
		{
			PlainSpacePatternMatcher matcher = new PlainSpacePatternMatcher();
			
			Direction[] moves =
			{
				Direction.Left,
				Direction.Up,
				Direction.Right,
				Direction.Down
			};
			
			Direction[] pattern =
			{
				Direction.Up,
				Direction.Right,
				Direction.Down,
				Direction.Left
			};
			
			assertFalse(matcher.match(moves, pattern));
		}
		
		@Test
		public void DiffersSizeMismatchedPatterns()
		{
			PlainSpacePatternMatcher matcher = new PlainSpacePatternMatcher();
			
			Direction[] moves = 
			{
				Direction.Forward,
				Direction.Forward,
				Direction.Forward
			};
			
			Direction[] pattern =
			{
				Direction.Forward,
				Direction.Forward,
				Direction.Forward,
				Direction.Forward
			};
			
			assertFalse(matcher.match(moves, pattern));
		}		
	}
}
