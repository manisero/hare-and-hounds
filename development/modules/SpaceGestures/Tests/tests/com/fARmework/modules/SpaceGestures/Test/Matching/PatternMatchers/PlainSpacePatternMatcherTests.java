package com.fARmework.modules.SpaceGestures.Test.Matching.PatternMatchers;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.SuiteClasses;

import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers.*;

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
								
			assertEquals(1.0, matcher.match(moves, pattern), 0.0);			
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
			
			assertEquals(0.0, matcher.match(moves, pattern), 0.0);
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
			
			assertEquals(0.0, matcher.match(moves, pattern), 0.0);
		}		
	}
}
