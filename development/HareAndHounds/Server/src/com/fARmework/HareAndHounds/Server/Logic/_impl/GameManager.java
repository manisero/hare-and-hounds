package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

public class GameManager implements IGameManager
{
	private final IConnectionManager _connectionManager;
	private final ISettingsProvider _settingsProvider;
	private final IDirectionCalculator _directionCalculator;
	private final IDistanceCalculator _distanceCalculator;
	
	private int _hareID;
	private int _houndsID;
	private PositionDataList _harePositions;
	private boolean _houndsInCheckpoint;
	
	public GameManager(IConnectionManager connectionManager, ISettingsProvider settingsProvider, IDirectionCalculator directionCalculator,
					   IDistanceCalculator distanceCalculator, int hareID, int houndsID)
	{
		_connectionManager = connectionManager;
		_settingsProvider = settingsProvider;
		_directionCalculator = directionCalculator;
		_distanceCalculator = distanceCalculator;
		
		_hareID = hareID;
		_houndsID = houndsID;
		_harePositions = new PositionDataList(_distanceCalculator);
	}
	
	@Override
	public void startGame(final IGameEndHandler gameEndHandler)
	{
		_connectionManager.registerDataHandler(PositionData.class, _hareID, new IDataHandler<PositionData>()
		{
			@Override
			public void handle(int clientID, PositionData data)
			{
				_harePositions.add(data, _settingsProvider.getCheckpointRange());
				
				if (_harePositions.size() >= _settingsProvider.getVictoriousHarePositions()) // hare has ran away
				{
					// TODO: end game
					gameEndHandler.onGameEnd(_hareID, _houndsID);
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
					PositionData nextCheckpoint = _harePositions.getNextPosition(data, checkpointRange);
					
					if (nextCheckpoint != null)
					{
						if (!_houndsInCheckpoint) // hounds have reached a checkpoint
						{
							_houndsInCheckpoint = true;
							
							double direction = _directionCalculator.calculateDirection(data, nextCheckpoint);
							_connectionManager.send(new CheckpointEnteredInfo(direction), _houndsID);
						}
						else // hounds are still in checkpoint
						{
							double direction = _directionCalculator.calculateDirection(data, nextCheckpoint);
							double accuracy = (checkpointRange - _distanceCalculator.calculateDistance(data, _harePositions.getNearPosition(data, checkpointRange))) / checkpointRange;
							_connectionManager.send(new CheckpointUpdateInfo(direction, accuracy), _houndsID);
						}
					}
					else // hounds have caught up the hare
					{
						// TODO: end game
						gameEndHandler.onGameEnd(_hareID, _houndsID);
					}
				}
				else if (_houndsInCheckpoint) // hounds have left a checkpoint
				{
					_houndsInCheckpoint = false;
					_connectionManager.send(new CheckpointLeftInfo(), _houndsID);
				}
			}
		});
		
		_connectionManager.send(new GameStartInfo(_settingsProvider.getHareDemandedPositionUpdateInterval()), _hareID);
		_connectionManager.send(new GameStartInfo(_settingsProvider.getHoundsDemandedPositionUpdateInterval()), _houndsID);
	}
}
