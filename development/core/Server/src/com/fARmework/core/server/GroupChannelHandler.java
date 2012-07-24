package com.fARmework.core.server;

import org.jboss.netty.channel.*;

public abstract class GroupChannelHandler extends SimpleChannelUpstreamHandler
{
	public abstract void send(Object object);
}
