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
			DiffusedScreenPatternMatcherTests.TheMatchPatternMethod.class
		})
public class DiffusedScreenPatternMatcherTests 
{
	public static class TheMatchPatternMethod
	{
		@Test
		public void MatchesSimplePatterns()
		{
			DiffusedScreenPatternMatcher matcher = new DiffusedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
					{	true,	false,	true	},
					{	false,	false,	true	},
					{	true,	false,	false	}
			};
			
			Double[][] pattern =
			{
					{	1.0,	0.0,	1.0	},
					{	0.0,	0.0,	1.0	},
					{	1.0,	0.0,	0.0	}
			};
			
			assertTrue(matcher.match(grid, pattern));
		}
		
		@Test
		public void MatchesPatternsWithLimitBelowMaximum()
		{
			DiffusedScreenPatternMatcher matcher = new DiffusedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
				{	true,	true,	false	},
				{	true,	false,	false	},
				{	false,	true,	true	}
			};
			
			Double[][] pattern =
			{
				{	0.2,	0.2,	0.2	},
				{	1.0,	0.2,	0.2	},
				{	0.2,	0.2,	0.2	}
			};
			
			assertTrue(matcher.match(grid, pattern));
		}
		
		@Test
		public void MatchesPatternsWithMaximumLimit() 
		{
			DiffusedScreenPatternMatcher matcher = new DiffusedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
				{	true,	true,	false,	true	},
				{	false,	false,	true,	false	},
				{	false,	true,	true,	false	},
				{	true,	true,	true,	false	}
			};
			
			Double[][] pattern =
			{
				{	0.2,	0.2,	0.0,	1.0	},
				{	0.0,	0.2,	1.0,	0.2	},
				{	0.2,	0.2,	0.2,	0.0	},
				{	1.0,	1.0,	0.2,	0.0	}
			};
			
			assertTrue(matcher.match(grid, pattern));
		}
		
		@Test
		public void DiffersSizeMismatchedPatterns()
		{
			DiffusedScreenPatternMatcher matcher = new DiffusedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
				{	true,	true,	false,	true	},
				{	false,	false,	true,	false	},
				{	false,	true,	true,	false	},
				{	true,	true,	true,	false	}
			};
			
			Double[][] pattern =
			{
				{	1.0,	1.0,	0.0	},
				{	0.0,	0.0,	1.0	},
				{	0.0,	0.3,	1.0	},
				{	1.0,	1.0,	0.3	}
			};
			
			assertFalse(matcher.match(grid, pattern));
		}
		
		@Test
		public void DiffersPatternsWithLimitOverMaximum()
		{
			DiffusedScreenPatternMatcher matcher = new DiffusedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
				{	true,	true,	false,	true	},
				{	false,	true,	true,	false	},
				{	false,	true,	true,	false	},
				{	true,	true,	true,	false	}
			};
			
			Double[][] pattern =
			{
				{	0.2,	0.2,	0.0,	1.0	},
				{	0.0,	0.2,	1.0,	0.2	},
				{	0.2,	0.2,	0.2,	0.0	},
				{	1.0,	1.0,	0.2,	0.0	}
			};
			
			assertFalse(matcher.match(grid, pattern));			
		}
		
		@Test
		public void DiffersDistinctPatterns()
		{
			DiffusedScreenPatternMatcher matcher = new DiffusedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
				{	true,	false,	false	},
				{	false,	false,	true	},
				{	true,	false,	true	}
			};
			
			Double[][] pattern =
			{
				{	0.2,	0.1,	1.0	},
				{	0.2,	0.3,	1.0	},
				{	0.0,	0.1,	1.0	}	
			};
			
			assertFalse(matcher.match(grid, pattern));			
		}
	}
}
