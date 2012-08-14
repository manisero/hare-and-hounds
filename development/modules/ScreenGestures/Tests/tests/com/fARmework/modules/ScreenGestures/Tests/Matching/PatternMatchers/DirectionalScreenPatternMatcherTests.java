package com.fARmework.modules.ScreenGestures.Tests.Matching.PatternMatchers;

import com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;

public class DirectionalScreenPatternMatcherTests 
{
	public static class TheMatchPatternMethod
	{
		@Test
		public void MatchesExactSamePatterns() 
		{
			DirectionalScreenPatternMatcher matcher = new DirectionalScreenPatternMatcher();
			
			Integer[][] grid =
			{
				{	4,	5,	6	},
				{	3,	0,	7	},
				{	2,	1,	8	}
			};
			
			Integer[][] pattern =
			{
				{	4,	5,	6	},
				{	3,	0,	7	},
				{	2,	1,	8	}
			};
			
			assertTrue(matcher.match(grid, pattern));
		}
		
		@Test
		public void MatchesPatternsWithOffset()
		{
			DirectionalScreenPatternMatcher matcher = new DirectionalScreenPatternMatcher();
			
			Integer[][] grid =
			{
				{	4,	5,	6,	7	},
				{	3,	2,	9,	8	},
				{	14,	1,	10,	0	},
				{	13,	12,	11,	0	}
			};
			
			Integer[][] pattern =
			{
				{	12,	13,	14,	1	},
				{	11,	10,	3,	2	},
				{	8,	9,	4,	0	},
				{	7,	6,	5,	0	}
			};
			
			assertTrue(matcher.match(grid, pattern));
		}
		
		@Test
		public void DiffersMirroredPatterns()
		{
			DirectionalScreenPatternMatcher matcher = new DirectionalScreenPatternMatcher();
			
			Integer[][] grid =
			{
				{	0,	0,	0,	5,	6	},
				{	0,	0,	4,	0,	7	},
				{	0,	3,	0,	0,	8	},
				{	0,	2,	0,	0,	9	},
				{	1,	0,	11,	10,	0	}
			};
			
			Integer[][] pattern =
			{
				{	0,	0,	0,	1,	11	},
				{	0,	0,	2,	0,	10	},
				{	0,	3,	0,	0,	9	},
				{	0,	4,	0,	0,	8	},
				{	5,	0,	6,	7,	0	}
			};
			
			assertFalse(matcher.match(grid, pattern));
		}
		
		@Test
		public void DiffersSizeMismatchedPatterns()
		{
			DirectionalScreenPatternMatcher matcher = new DirectionalScreenPatternMatcher();
			
			Integer[][] grid =
			{
				{	0,	0,	3,	0,	0	},
				{	0,	2,	4,	0,	0	},
				{	1,	0,	5,	0,	0	},
				{	7,	6,	0,	0,	0	},
				{	0,	0,	0,	0,	0	}
			};
			
			Integer[][] pattern =
			{
				{	0,	0,	7,	0	},
				{	0,	6,	1,	0	},
				{	5,	0,	2,	0	},
				{	4,	3,	0,	0	}
			};
				
			assertFalse(matcher.match(grid, pattern));
		}
	}
}
