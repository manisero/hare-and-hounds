package com.JSON;

import java.io.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.json.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class JSONActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                
        String twitterFeed = readTwitterFeed();
        
        try
		{
        	JSONArray jsonArray = new JSONArray(twitterFeed);
            
            Log.i(JSONActivity.class.toString(), "Number of entries: " + jsonArray.length());
            
            for(int i = 0; i < jsonArray.length(); ++i)
            {
            	JSONObject jsonObject = jsonArray.getJSONObject(i);
            	
            	Log.i(JSONActivity.class.toString(), jsonObject.getString("text"));
            }	
		} 
        catch(JSONException e)
		{
        	Log.e(JSONActivity.class.toString(), e.getMessage());
			e.printStackTrace();
		}
    }
    
    public String readTwitterFeed()
    {
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	HttpClient httpClient = new DefaultHttpClient();
    	
    	HttpGet httpGet = new HttpGet("http://twitter.com/statuses/user_timeline/kubaturek.json");
    	
    	try
		{
			HttpResponse httpResponse = httpClient.execute(httpGet);
		
			StatusLine statusLine = httpResponse.getStatusLine();
			
			Integer statusCode = statusLine.getStatusCode();
			
			((TextView)findViewById(R.id.status)).setText("Status code: " + statusCode);
			Log.i(JSONActivity.class.toString(), "Status code: " + statusCode);
			
			if(statusCode == 200)
			{
				HttpEntity entity = httpResponse.getEntity();
				
				InputStream content = entity.getContent();
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
				
				String streamLine = null;
				
				while((streamLine = bufferedReader.readLine()) != null)
				{	
					stringBuilder.append(streamLine);
				} 
			}
			else 
			{
				Log.e(JSONActivity.class.toString(), "Failed to fetch the feed");
			}
		} 
    	catch (ClientProtocolException e)
		{
    		e.printStackTrace();
		}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	
    	return stringBuilder.toString();
    }
}