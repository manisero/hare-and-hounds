package com.fARmework.modules.SpaceGestures.Test.Matching.PatternMatchers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fARmework.modules.SpaceGestures.Java.Direction;
import com.fARmework.modules.SpaceGestures.Java.Matching.ISpacePatternMatcher;
import com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers.SurfaceSpacePatternMatcher;

@RunWith(Suite.class)
@SuiteClasses(
		{ 
			SurfacePatternMatcherTests.TheMatchMethod.class 
		})
public class SurfacePatternMatcherTests
{
	public static class TheMatchMethod
	{
		@Test
		public void MatchesMoveWithZAxisNoise()
		{
			ISpacePatternMatcher matcher = new SurfaceSpacePatternMatcher();
			
			Direction[] pattern =
			{
				Direction.Left,
				Direction.Up,
				Direction.Right,
				Direction.Down
			};
			
			Direction[] moves =
			{
				Direction.Left,
				Direction.UpForward,
				Direction.Right,
				Direction.Down
			};
			
			assertEquals(1.0, matcher.match(moves, pattern), 0.0);
		}
	}
}
