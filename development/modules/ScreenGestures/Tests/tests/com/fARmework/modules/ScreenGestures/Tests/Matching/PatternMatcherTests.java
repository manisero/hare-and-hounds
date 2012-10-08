package com.fARmework.modules.ScreenGestures.Tests.Matching;

import com.fARmework.modules.ScreenGestures.Tests.Matching.PatternMatchers.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{
			DiffusedScreenPatternMatcherTests.class,
			DirectionalScreenPatternMatcherTests.class,
			GroupedScreenPatternMatcherTests.class,
			PlainScreenPatternMatcherTests.class
		})
public class PatternMatcherTests 
{ }
