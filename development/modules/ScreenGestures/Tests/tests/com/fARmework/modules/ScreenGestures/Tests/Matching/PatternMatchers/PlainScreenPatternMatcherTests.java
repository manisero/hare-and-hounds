package com.fARmework.modules.ScreenGestures.Tests.Matching.PatternMatchers;

import com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			PlainScreenPatternMatcherTests.TheMatchPatternMethod.class
		})
public class PlainScreenPatternMatcherTests 
{
	public static class TheMatchPatternMethod
	{
		@Test
		public void MatchesExactSamePatterns() 
		{
			PlainScreenPatternMatcher matcher = new PlainScreenPatternMatcher();
			
			Boolean[][] grid = 
			{
					{	true, false, true	},
					{	true, false, true	},
					{	true, false, true	}
			};
			
			Boolean[][] pattern =
			{
					{	true, false, true	},
					{	true, false, true	},
					{	true, false, true	}
			};
			
			assertTrue(matcher.match(grid, pattern));
		}
		
		@Test
		public void DiffersDistinctPatterns()
		{
			PlainScreenPatternMatcher matcher = new PlainScreenPatternMatcher();
			
			Boolean[][] grid =
			{
					{	false,	true,	true	},
					{	true,	false,	false	},
					{	false,	true,	false	}
			};
			
			Boolean[][] pattern =
			{
					{	false,	true,	false	},
					{	true,	true,	true	},
					{	false,	false,	false	}
			};
			
			assertFalse(matcher.match(grid, pattern));
		}
		
		@Test
		public void DiffersSizeMismatchedPatterns()
		{
			PlainScreenPatternMatcher matcher = new PlainScreenPatternMatcher();
			
			Boolean[][] grid =
			{
					{	true,	true,	true,	true	},
					{	true,	true,	true,	true	},
					{	true,	true,	true,	true	},
					{	true,	true,	true, 	true	}
			};
			
			Boolean[][] pattern =
			{
					{	true,	true,	true	},
					{	true,	true,	true	},
					{	true,	true,	true	}
			};
			
			assertFalse(matcher.match(grid, pattern));
		}
	}
}
