package com.fARmework.creat.GestureDetector.Matching.PatternMatchers;

public class DiffusedPatternMatcher extends PatternMatcherBase<Double> 
{
	@Override
	public boolean match(Double[][] input, Double[][] pattern) 
	{
		if(!sizeCheck(input, pattern))
		{
			return false;
		}
		
		int gridSize = input.length;
		
		double diffusedSum = 0.0;
		
		for(int x = 0; x < gridSize; ++x)
		{
			for(int y = 0; y < gridSize; ++y)
			{
				if(!input[x][y].equals(pattern[x][y]))
				{
					if(input[x][y].equals(1.0) && pattern[x][y] > 0.0 && pattern[x][y] < 1.0)
					{
						diffusedSum += pattern[x][y];
						
						if(diffusedSum > 1.0)
						{							
							return false;
						}
					}
					else if(!input[x][y].equals(0.0))
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
