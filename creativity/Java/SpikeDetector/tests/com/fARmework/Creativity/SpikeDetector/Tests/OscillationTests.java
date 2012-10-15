package com.fARmework.Creativity.SpikeDetector.Tests;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.fARmework.Creativity.SpikeDetector.Oscillation;
import com.fARmework.Creativity.SpikeDetector.OscillationRange;

@RunWith(Suite.class)
@SuiteClasses(
		{
			OscillationTests.TheGetOscillationsMethod.class
		})
public class OscillationTests 
{
	public static class TheGetOscillationsMethod
	{
		@Test
		public void DetectsTwoOscillationRanges()
		{
			Oscillation oscillation = new Oscillation();
			
			float[] accelerometerData =
			{
					0.221576172f,
					0f,
					0.191426226f,
					0.110566722f,
					2.515710834f,
					0.581763698f,
					7.399461805f,
					4.6707949f,
					1.335606229f,
					5.683671349f,
					14.96321125f,
					9.839758381f,
					10.64073057f,
					0.759469552f,
					4.407719705f,
					6.721301511f,
					4.205159212f,
					6.604420489f,
					7.509171592f,
					12.10627845f,
					7.044948119f,
					4.620848191f,
					9.427347718f,
					5.086149428f,
					2.915860422f,
					8.250008848f,
					7.711782868f,
					3.260518517f,
					12.82918057f,
					9.50602409f,
					5.982854336f,
					11.42211999f,
					2.574208422f,
					9.047403274f,
					6.804679346f,
					8.134927289f,
					6.194087665f,
					9.950559934f,
					12.84725033f,
					1.482815228f,
					5.027825176f,
					2.223003824f,
					3.150402197f,
					3.289721721f,
					1.767110919f,
					2.594244784f
			};
			
			List<OscillationRange> ranges = oscillation.getOscillations(accelerometerData);
			
			for (OscillationRange range : ranges)
			{
				System.out.println("Init: " + (range.OscillationBegin + 1) + " End: " + (range.OscillationEnd + 1));
			}
		}
	}
}
