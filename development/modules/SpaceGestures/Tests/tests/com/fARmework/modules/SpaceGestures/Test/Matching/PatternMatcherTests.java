package com.fARmework.modules.SpaceGestures.Test.Matching;

import com.fARmework.modules.SpaceGestures.Test.Matching.PatternMatchers.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses(
		{ 
			CyclicSpacePatternMatcherTests.class,
			PlainSpacePatternMatcherTests.class 
		})
public class PatternMatcherTests 
{ }
