package com.fARmework.modules.ScreenGestures.Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fARmework.modules.ScreenGestures.Tests.Matching.PatternMatcherTests;
import com.fARmework.modules.ScreenGestures.Tests.Processing.GestureProcessorTests;

@RunWith(Suite.class)
@SuiteClasses(
		{
			PatternMatcherTests.class,
			GestureProcessorTests.class
		})
public class ScreenGesturesTests
{

}
