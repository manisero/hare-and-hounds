package com.fARmework.client.Logic;

import gueei.binding.observables.StringObservable;

public interface IConnectionManager
{
	void connect(StringObservable output);
	void disconnect();
}
