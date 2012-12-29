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
			GroupedScreenPatternMatcherTests.TheMatchPatternMethod.class
		})
public class GroupedScreenPatternMatcherTests 
{
	public static class TheMatchPatternMethod
	{
		@Test
		public void MatchesSimplePatterns()
		{
			GroupedScreenPatternMatcher matcher = new GroupedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
				{	true,	false,	false,	true	},
				{	false,	true,	false,	true	},
				{	true,	true,	true,	false	},
				{	false,	false,	true,	true	}
			};
			
			Character[][] pattern =
			{
				{	'+',	'-',	'-',	'+'	},
				{	'-',	'+',	'-',	'+'	},
				{	'+',	'+',	'+',	'-'	},
				{	'-',	'-',	'+',	'+'	}
			};			
			
			assertTrue(matcher.match(grid, pattern));
		}
		
		@Test
		public void MatchesPatternsWithOptionalValues() 
		{
			GroupedScreenPatternMatcher matcher = new GroupedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
				{	true,	false,	false	},
				{	false,	true,	true	},
				{	true,	false,	true	}
			};
			
			Character[][] pattern =
			{
				{	'+',	'?',	'-'	},
				{	'-',	'?',	'?'	},
				{	'+',	'?',	'+'	}
			};
			
			assertTrue(matcher.match(grid, pattern));
		}
		
		@Test
		public void MatchesPatternsWithGroups()
		{
			GroupedScreenPatternMatcher matcher = new GroupedScreenPatternMatcher();
			
			Boolean[][] grid = 
			{
				{	true,	true,	true,	false,	true	},
				{	false,	false,	false,	true,	true	},
				{	true,	true,	true,	false,	true	},
				{	true,	true,	true,	true,	false	},
				{	false,	true,	false,	true,	false	}
			};
			
			Character[][] pattern = 
			{
				{	'a',	'+',	'+',	'e',	'd'	},
				{	'b',	'?',	'a',	'e',	'd'	},
				{	'c',	'a',	'f',	'-',	'd'	},
				{	'+',	'-',	'b',	'?',	'-'	},
				{	'?',	'b',	'd',	'c',	'f'	}
			};		
			
			assertTrue(matcher.match(grid, pattern));
		}
		
		@Test
		public void RequiresEachGroupMember()
		{
			GroupedScreenPatternMatcher matcher = new GroupedScreenPatternMatcher();
			
			Boolean[][] grid = 
			{
				{	false,	true,	false,	false	},
				{	true,	false,	false,	true	},
				{	false,	true,	true,	false	},
				{	false,	false,	false,	true	}
			};
			
			Character[][] pattern =
			{
				{	'c',	'?',	'b',	'b'	},
				{	'a',	'a',	'?',	'?'	},
				{	'?',	'a',	'c',	'c'	},
				{	'a',	'?',	'a',	'?'	}
			};
			
			assertFalse(matcher.match(grid, pattern));
		}
		
		@Test
		public void DiffersSizeMismatchedPatterns()
		{
			GroupedScreenPatternMatcher matcher = new GroupedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
				{	true,	false,	false	},
				{	false,	true,	true	},
				{	true,	false,	true	}
			};
			
			Character[][] pattern =
			{
				{	'?',	'?',	'?',	'?'	},
				{	'?',	'?',	'?',	'?'	},
				{	'?',	'?',	'?',	'?'	},
				{	'?',	'?',	'?',	'?'	}
			};

			assertFalse(matcher.match(grid, pattern));
		}
		
		@Test
		public void DiffersDistinctPatterns()
		{
			GroupedScreenPatternMatcher matcher = new GroupedScreenPatternMatcher();
			
			Boolean[][] grid =
			{
				{	true,	false,	true	},
				{	false,	false,	false	},
				{	true,	false,	false	}
			};
			
			Character[][] pattern =
			{
				{	'+',	'a',	'a'	},
				{	'?',	'?',	'-'	},
				{	'+',	'+',	'?'	}
			};			
			
			assertFalse(matcher.match(grid, pattern));
		}
	}
}
