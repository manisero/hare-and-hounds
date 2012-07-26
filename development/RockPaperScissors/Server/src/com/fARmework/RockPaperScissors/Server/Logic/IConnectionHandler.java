package com.fARmework.RockPaperScissors.Server.Logic;

import com.fARmework.RockPaperScissors.Data.IDataHandler;

public interface IConnectionHandler extends com.fARmework.core.server.Connection.IConnectionHandler
{
	<T> void registerHandler(Class<T> dataClass, IDataHandler<T> handler);
}
