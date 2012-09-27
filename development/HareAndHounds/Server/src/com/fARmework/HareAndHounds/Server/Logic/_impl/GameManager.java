package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

public class GameManager implements IGameManager
{
	private IConnectionManager _connectionManager;
	private ISettingsProvider _settingsProvider;
	private IDirectionCalculator _directionCalculator;
	
	private int _hareID;
	private int _houndsID;
	private PositionDataList _harePositions;
	private boolean _houndsInCheckPoint;
	
	public GameManager(IConnectionManager connectionManager, ISettingsProvider settingsProvider, IDirectionCalculator directionCalculator,
					   IDistanceCalculator distanceCalculator, int hareID, int houndsID)
	{
		_connectionManager = connectionManager;
		_settingsProvider = settingsProvider;
		_directionCalculator = directionCalculator;
		
		_hareID = hareID;
		_houndsID = houndsID;
		_harePositions = new PositionDataList(distanceCalculator);
	}
	
	@Override
	public void startGame(IGameEndHandler gameEndHandler)
	{
		_connectionManager.registerDataHandler(PositionData.class, _hareID, new IDataHandler<PositionData>()
		{
			@Override
			public void handle(int clientID, PositionData data)
			{
				_harePositions.add(data);
				
				if (_harePositions.size() >= _settingsProvider.getVictoriousHarePositions()) // hare has ran away
				{
					// TODO: end game
				}
			}
		});
		
		_connectionManager.registerDataHandler(PositionData.class, _houndsID, new IDataHandler<PositionData>()
		{
			@Override
			public void handle(int clientID, PositionData data)
			{
				if (_harePositions.size() < _settingsProvider.getRequiredInitialHarePositions())
				{
					return;
				}
				
				double checkpointRange = _settingsProvider.getCheckpointRange();
				
				if (_harePositions.isNearAnyPosition(data, checkpointRange))
				{
					PositionData nextPosition = _harePositions.getNextPosition(data, checkpointRange);
					
					if (nextPosition != null) // hounds have reached a checkpoint
					{
						_houndsInCheckPoint = true;
						_connectionManager.send(new CheckpointEnteredInfo(_directionCalculator.calculateDirection(data, nextPosition)), _houndsID);
					}
					else // hounds have caught up the hare
					{
						// TODO: end game
					}
				}
				else if (_houndsInCheckPoint) // hounds have left a checkpoint
				{
					_connectionManager.send(new CheckpointLeftInfo(), _houndsID);
				}
			}
		});
		
		_connectionManager.send(new GameStartInfo(_settingsProvider.getHareDemandedPositionUpdateInterval()), _hareID);
		_connectionManager.send(new GameStartInfo(_settingsProvider.getHoundsDemandedPositionUpdateInterval()), _houndsID);
	}
}
