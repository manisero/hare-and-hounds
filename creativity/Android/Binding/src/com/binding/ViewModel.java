package com.binding;

import android.view.View;
import gueei.binding.Command;
import gueei.binding.observables.StringObservable;

public class ViewModel
{
	public class MessageCommand extends Command
	{
		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			message.set(message.get() + "_dupa");
		}
	}
	
	public StringObservable message = new StringObservable("dupa");
	public MessageCommand change = new MessageCommand();
}
