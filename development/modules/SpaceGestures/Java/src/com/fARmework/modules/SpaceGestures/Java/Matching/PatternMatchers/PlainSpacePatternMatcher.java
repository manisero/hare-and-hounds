package com.fARmework.modules.SpaceGestures.Java.Matching.PatternMatchers;

import com.fARmework.modules.SpaceGestures.Java.*;
import com.fARmework.modules.SpaceGestures.Java.Matching.*;

public class PlainSpacePatternMatcher implements ISpacePatternMatcher
{
	@Override
	public double match(Direction[] input, Direction[] pattern)
	{
		double singleDirectionValue = ISpacePatternMatcher.MAX_MATCHING_RATIO / pattern.length;
		double result = ISpacePatternMatcher.MAX_MATCHING_RATIO;
		
		int currentInputIndex = 0;
		
		for (int i = 0; i < pattern.length; ++i)
		{
			if (input[currentInputIndex].equals(pattern[i])) // match
			{
				// the result stays intact
			}
			else if (input[currentInputIndex].equals(pattern[i + 1])) // missing direction (cost: sdv)
			{
				result -= singleDirectionValue;
			}
			else if (input[currentInputIndex].intersects(pattern[i])) // partial mismatch (cost: sdv / 2)
			{
				result -= singleDirectionValue / 2.0;
			}
			else if (input[currentInputIndex + 1].equals(pattern[i])) // extra direction
			{
				if (input[currentInputIndex].equals(pattern[i - 1])) // extra match (cost: sdv / 2)
				{
					result -= singleDirectionValue / 2.0;
				}
				else if (input[currentInputIndex].intersects(pattern[i - 1])) // extra partial mismatch (cost: sdv)
				{
					result -= singleDirectionValue;
				}
				else // extra complete mismatch (cost: sdv * 1.5)
				{
					result -= singleDirectionValue * 1.5;
				}
				
				currentInputIndex++; // next input direction has already been checked
			}
			else // complete mismatch (cost: sdv)
			{
				result -= singleDirectionValue;
			}
			
			currentInputIndex++;
		}
		
		return result;
	}
}
