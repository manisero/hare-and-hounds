package com.fARmework.Creativity.CustomAttributes;

import android.content.*;
import android.util.*;
import android.widget.*;

public class CustomButton extends Button
{	
	public CustomButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		String text = attrs.getAttributeValue("http://farmework.com/custombutton/schema", "text");
		
		initialize(text);
	}
	
	public CustomButton(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		
		String text = attrs.getAttributeValue("http://farmework.com/custombutton/schema", "text");
		
		initialize(text);
	}
	
	public void initialize(String text)
	{
		setText(text);
	}
}
