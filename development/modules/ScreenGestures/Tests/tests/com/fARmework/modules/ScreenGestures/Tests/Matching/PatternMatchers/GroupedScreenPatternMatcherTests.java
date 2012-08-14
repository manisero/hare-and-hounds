package com.fARmework.modules.ScreenGestures.Tests.Matching.PatternMatchers;

import com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers.*;
import static org.junit.Assert.*;
import org.junit.*;

public class GroupedScreenPatternMatcherTests 
{
	public static class TheMatchPatternMethod
	{
		@Test
		public void MatchesSimplePatterns()
		{
			GroupedScreenPatternMatcher matcher = new GroupedScreenPatternMatcher();
			
			Character[][] grid =
			{
				{	't',	'f',	'f',	't'	},
				{	'f',	't',	'f',	't'	},
				{	't',	't',	't',	'f'	},
				{	'f',	'f',	't',	't'	}
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
			
			Character[][] grid =
			{
				{	't',	'f',	'f'	},
				{	'f',	't',	't'	},
				{	't',	'f',	't'	}
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
			
			Character[][] grid = 
			{
				{	't',	't',	't',	'f',	't'	},
				{	'f',	'f',	'f',	't',	't'	},
				{	't',	't',	't',	'f',	't'	},
				{	't',	'f',	't',	't',	'f'	},
				{	'f',	't',	'f',	't',	'f'	}
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
			
			Character[][] grid = 
			{
				{	'f',	't',	'f',	'f'	},
				{	't',	'f',	'f',	't'	},
				{	'f',	't',	't',	'f'	},
				{	'f',	'f',	'f',	't'	}
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
			
			Character[][] grid =
			{
				{	't',	'f',	'f'	},
				{	'f',	't',	't'	},
				{	't',	'f',	't'	}
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
			
			Character[][] grid =
			{
				{	't',	'f',	't'	},
				{	'f',	'f',	'f'	},
				{	't',	'f',	'f'	}
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
