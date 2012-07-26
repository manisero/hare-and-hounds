package com.fARmework.RockPaperScissors.Server.Logic;

import com.fARmework.RockPaperScissors.Server.Logic.DataHandlers.IDataHandler;

public interface IConnectionHandler extends com.fARmework.core.server.Connection.IConnectionHandler
{
	<T> void registerHandler(Class<T> dataClass, IDataHandler<T> handler);
}
