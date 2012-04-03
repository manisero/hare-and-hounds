package com.fARmework.client.Logic;

import gueei.binding.observables.StringObservable;

import java.net.Socket;

public interface IReadTask
{
	public void execute(Socket socket, StringObservable output);
}
