package com.fARmework.Creativity.CustomAttributes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CustomAttributesActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_attributes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_custom_attributes, menu);
        return true;
    }
}
