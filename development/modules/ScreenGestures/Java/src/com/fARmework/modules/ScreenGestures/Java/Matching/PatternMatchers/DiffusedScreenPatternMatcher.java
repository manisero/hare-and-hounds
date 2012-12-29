package com.fARmework.modules.ScreenGestures.Java.Matching.PatternMatchers;

public class DiffusedScreenPatternMatcher extends ScreenPatternMatcherBase<Double> 
{
	@Override
	public boolean matchPattern(Boolean[][] input, Double[][] pattern) 
	{
		double diffusedSum = 0.0;
		
		for (int x = 0; x < pattern.length; ++x)
		{
			for (int y = 0; y < pattern[x].length; ++y)
			{
				Double fieldValue = getFieldValue(input[x][y]); 
				
				if (!fieldValue.equals(pattern[x][y]))
				{
					if (pattern[x][y].equals(0.0) || pattern[x][y].equals(1.0))
					{
						return false;
					}
					
					if (!fieldValue.equals(0.0))
					{
						diffusedSum += pattern[x][y];
					}
						
					if (diffusedSum > 1.0)
					{							
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private double getFieldValue(Boolean field)
	{
		return field ? 1.0 : 0.0;
	}
}
