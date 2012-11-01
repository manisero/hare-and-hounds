package com.fARmework.Creativity.SpikeDetector;

import java.util.*;

public class Thresholder
{
	public static final float THRESHOLD = 0.5f;
	
	public List<SegmentRange> getFilteredSegments(List<SegmentRange> allSegments, float[] values)
	{
		List<SegmentRange> filteredSegments = new ArrayList<SegmentRange>();
		
		float globalMaximum = getMaximum(new SegmentRange(0, values.length - 1), values);
		
		for (SegmentRange range : allSegments)
		{
			float ratio = getMaximum(range, values) / globalMaximum;
			
			if (ratio > THRESHOLD)
			{
				filteredSegments.add(range);
			}
		}
		
		return filteredSegments;
	}
	
	private float getMaximum(SegmentRange range, float[] values)
	{
		float maximum = 0.0f;
		
		for (int i = range.OscillationBegin; i <= range.OscillationEnd; ++i)
		{
			if (values[i] > maximum)
			{
				maximum = values[i];
			}
		}
		
		return maximum;
	}
}
