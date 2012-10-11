package com.binding;

import android.os.Bundle;

public class BindingActivity extends gueei.binding.app.BindingActivity
{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setAndBindRootView(R.layout.main, new ViewModel());
    }
}