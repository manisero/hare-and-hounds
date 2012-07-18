package com.fARmework.RockPaperScissors.Server.Logic.DataHandlers;

import com.fARmework.RockPaperScissors.Server.Logic.Impl.MessageProcessor.IDataHandler;
import com.fARmework.modules.ScreenGestures.Data.GestureData;
import com.fARmework.modules.ScreenGestures.Data.GestureData.Point;

public class GestureProcessor implements IDataHandler<GestureData>
{
	@Override
	public void handle(GestureData data)
	{
		System.out.println("Processing gesture data...");
		
		for (Point point : data.getPoints())
		{
			System.out.println(point.X + ", " + point.Y);
		}
		
		System.out.println("Finished processing gesture data");
	}
}
