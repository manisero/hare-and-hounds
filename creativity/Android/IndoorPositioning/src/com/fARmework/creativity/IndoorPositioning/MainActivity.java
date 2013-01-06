package com.fARmework.creativity.IndoorPositioning;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	private Button _storeButton;
	private Button _checkButton;
	
	private PositionRepository _repository;
	private PositionFetcher _fetcher;
	private PositionChecker _checker;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        _storeButton = (Button) findViewById(R.id.storePosition);
        _checkButton = (Button) findViewById(R.id.checkPosition);
        
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
        _repository = new PositionRepository();
        _fetcher = new PositionFetcher(wifiManager);
        _checker = new PositionChecker();
        
        _storeButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				IndoorPositionData positionData = _fetcher.getCurrentPosition();
				
				_repository.addPosition(positionData);
				
				StringBuilder stringBuilder = new StringBuilder("Storing current position with networks: \n");
				
				for (Map.Entry<String, Integer> wifiData : positionData.WifiSignalData.entrySet())
				{
					stringBuilder.append(wifiData.getKey() + " - " + wifiData.getValue() + "%\n");
				}
				
				Toast.makeText(getApplicationContext(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
			}
		});
        
        _checkButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				boolean isVisited = _checker.isPositionVisited(_fetcher.getCurrentPosition(), _repository.getPositions());
				
				Toast toast = Toast.makeText(getApplicationContext(), 
						isVisited ? "You have been there already!" : "You haven't been there yet!", 
						Toast.LENGTH_LONG);
				
				toast.show();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
