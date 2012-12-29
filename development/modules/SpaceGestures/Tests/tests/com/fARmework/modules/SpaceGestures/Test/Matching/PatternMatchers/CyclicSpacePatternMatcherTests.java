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
			CyclicSpacePatternMatcherTests.TheMatchMethod.class 
		})
public class CyclicSpacePatternMatcherTests 
{
	public static class TheMatchMethod
	{
		@Test
		public void MatchesExactSamePatterns()
		{
			CyclicSpacePatternMatcher matcher = new CyclicSpacePatternMatcher();
			
			Direction[] moves = 
			{
				Direction.Left,
				Direction.Up,
				Direction.Right,
				Direction.Down
			};
			
			Direction[] pattern = 
			{
				Direction.Left,
				Direction.Up,
				Direction.Right,
				Direction.Down
			};			
			
			assertEquals(1.0, matcher.match(moves, pattern), 0.0);
		}
		
		@Test
		public void MatchesPatternsWithOffset()
		{
			CyclicSpacePatternMatcher matcher = new CyclicSpacePatternMatcher();
			
			Direction[] moves = 
			{
				Direction.Left,
				Direction.Up,
				Direction.Right,
				Direction.Down,
				Direction.Forward,
				Direction.Backward
			};
			
			Direction[] pattern = 
			{
				Direction.Down,
				Direction.Forward,
				Direction.Backward,
				Direction.Left,
				Direction.Up,
				Direction.Right
			};			
			
			assertEquals(1.0, matcher.match(moves, pattern), 0.0);			
		}
		
		@Test
		public void DiffersSizeMismatchedPatterns()
		{
			CyclicSpacePatternMatcher matcher = new CyclicSpacePatternMatcher();
			
			Direction[] moves =
			{
				Direction.Left,
				Direction.Backward,
				Direction.Right,
				Direction.Down
			};
			
			Direction[] pattern =
			{
				Direction.Left,
				Direction.Backward,
				Direction.Right
			};
			
			assertEquals(0.0, matcher.match(moves, pattern), 0.0);
		}
		
		@Test
		public void DiffersMirroredPatterns()
		{
			CyclicSpacePatternMatcher matcher = new CyclicSpacePatternMatcher();
			
			Direction[] moves =
			{
				Direction.Left,
				Direction.Backward,
				Direction.Right,
				Direction.Down
			};
			
			Direction[] pattern =
			{
				Direction.Left,
				Direction.Down,
				Direction.Right,
				Direction.Backward
			};
			
			assertEquals(0.0, matcher.match(moves, pattern), 0.0);
		}
		
		@Test
		public void DiffersDistinctPatterns()
		{
			CyclicSpacePatternMatcher matcher = new CyclicSpacePatternMatcher();
			
			Direction[] moves =
			{
				Direction.Right,
				Direction.Right,
				Direction.Right,
				Direction.Right,
				Direction.Right
			};
			
			Direction[] pattern =
			{
				Direction.Right,
				Direction.Right,
				Direction.Left,
				Direction.Right,
				Direction.Right
			};
			
			assertEquals(0.0, matcher.match(moves, pattern), 0.0);
		}
	}
}
