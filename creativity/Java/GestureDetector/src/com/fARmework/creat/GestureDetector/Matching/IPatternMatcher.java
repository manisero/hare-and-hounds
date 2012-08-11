package com.fARmework.creat.GestureDetector.Matching;

public interface IPatternMatcher<T> 
{	
	boolean match(T[][] input, T[][] pattern);
}