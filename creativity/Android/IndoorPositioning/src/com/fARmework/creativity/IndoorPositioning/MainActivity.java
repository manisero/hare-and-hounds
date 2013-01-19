package com.fARmework.creativity.IndoorPositioning;

import java.util.*;

import android.app.*;
import android.content.*;
import android.net.wifi.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity 
{
	private PositionFetcher _fetcher;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
        _fetcher = new PositionFetcher();
        
        wifiManager.startScan();
        
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        
        registerReceiver(new BroadcastReceiver()
		{
			@Override
			public void onReceive(Context context, Intent intent)
			{
				List<ScanResult> scanResults = wifiManager.getScanResults();
				
				if (scanResults != null)
				{
					StringBuilder stringBuilder = new StringBuilder("Current position with networks: \n");
					
					for (ScanResult scanResult : scanResults)
					{
						int signalStrength = _fetcher.calculateSignalLevel(scanResult.level, 101);
						
						stringBuilder.append(scanResult.SSID + " - " + signalStrength + "%\n");
					}
					
					Toast.makeText(getApplicationContext(), stringBuilder.toString(), Toast.LENGTH_LONG).show();
				}
				
				new CountDownTimer(5000, 5000)
				{
					@Override
					public void onTick(long millisUntilFinished)
					{
					}
					
					@Override
					public void onFinish()
					{
						wifiManager.startScan();
					}
				}.start();
			}
		}, intentFilter); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
