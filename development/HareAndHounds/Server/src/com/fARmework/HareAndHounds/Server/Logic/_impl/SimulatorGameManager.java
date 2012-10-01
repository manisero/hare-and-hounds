package com.fARmework.HareAndHounds.Server.Logic._impl;

import com.fARmework.HareAndHounds.Data.*;
import com.fARmework.HareAndHounds.Server.Infrastructure.*;
import com.fARmework.HareAndHounds.Server.Logic.*;
import com.fARmework.core.server.Connection.*;
import com.fARmework.modules.PositionTracking.Data.*;
import com.fARmework.modules.PositionTracking.Java.*;

public class SimulatorGameManager implements IGameManager
{
	private final IConnectionManager _connectionManager;
	private final ISettingsProvider _settingsProvider;
	private final IDirectionCalculator _directionCalculator;
	private final IDistanceCalculator _distanceCalculator;
	
	private int _houndsID;
	private PositionDataList _harePositions;
	private boolean _houndsInCheckpoint;
	
	public SimulatorGameManager(IConnectionManager connectionManager, ISettingsProvider settingsProvider, IDirectionCalculator directionCalculator,
					   IDistanceCalculator distanceCalculator, int houndsID)
	{
		_connectionManager = connectionManager;
		_settingsProvider = settingsProvider;
		_directionCalculator = directionCalculator;
		_distanceCalculator = distanceCalculator;
		
		_houndsID = houndsID;
		_harePositions = new PositionDataList(_distanceCalculator);
		
		addPositionsToList();
	}
	
	@Override
	public void startGame(IGameEndHandler gameEndHandler)
	{	
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
					}
				}
				else if (_houndsInCheckpoint) // hounds have left a checkpoint
				{
					_houndsInCheckpoint = false;
					_connectionManager.send(new CheckpointLeftInfo(), _houndsID);
				}
			}
		});
		
		_connectionManager.send(new GameStartInfo(_settingsProvider.getHoundsDemandedPositionUpdateInterval()), _houndsID);
	}
	
	private void addPositionsToList()
	{
		_harePositions.add(new PositionData(52.13102,	21.070337));
		_harePositions.add(new PositionData(52.130837,	21.070452));
		_harePositions.add(new PositionData(52.130701,	21.070554));
		_harePositions.add(new PositionData(52.130539,	21.070675));
		_harePositions.add(new PositionData(52.130427,	21.070742));
		_harePositions.add(new PositionData(52.130315,	21.070635));
		_harePositions.add(new PositionData(52.130246,	21.070391));
		_harePositions.add(new PositionData(52.130393,	21.070214));
		_harePositions.add(new PositionData(52.130552,	21.070007));
		_harePositions.add(new PositionData(52.130732,	21.069843));
		_harePositions.add(new PositionData(52.130862,	21.06975));
		_harePositions.add(new PositionData(52.130995,	21.069637));
		_harePositions.add(new PositionData(52.131142,	21.069527));
		_harePositions.add(new PositionData(52.131163,	21.069302));
		_harePositions.add(new PositionData(52.131119,	21.06883));
		_harePositions.add(new PositionData(52.131078,	21.0684));
		_harePositions.add(new PositionData(52.131041,	21.06795));
		_harePositions.add(new PositionData(52.131018,	21.067684));
		_harePositions.add(new PositionData(52.13099,	21.067379));
		_harePositions.add(new PositionData(52.130967,	21.067108));
		_harePositions.add(new PositionData(52.130925,	21.066713));
		_harePositions.add(new PositionData(52.130768,	21.06678));
		_harePositions.add(new PositionData(52.130661,	21.066874));
		_harePositions.add(new PositionData(52.130536,	21.067006));
		_harePositions.add(new PositionData(52.130434,	21.0671));
		_harePositions.add(new PositionData(52.130337,	21.067193));
		_harePositions.add(new PositionData(52.130207,	21.06732));
		_harePositions.add(new PositionData(52.130083,	21.067427));
		_harePositions.add(new PositionData(52.129963,	21.067523));
		_harePositions.add(new PositionData(52.129894,	21.067365));
		_harePositions.add(new PositionData(52.129734,	21.06751));
		_harePositions.add(new PositionData(52.129579,	21.067531));
		_harePositions.add(new PositionData(52.129411,	21.067695));
		_harePositions.add(new PositionData(52.129276,	21.067826));
		_harePositions.add(new PositionData(52.129117,	21.067966));
		_harePositions.add(new PositionData(52.128955,	21.068116));
		_harePositions.add(new PositionData(52.128858,	21.068215));
		_harePositions.add(new PositionData(52.128753,	21.068328));
		_harePositions.add(new PositionData(52.128558,	21.068505));
		_harePositions.add(new PositionData(52.128435,	21.068658));
		_harePositions.add(new PositionData(52.128549,	21.068918));
		_harePositions.add(new PositionData(52.128502,	21.069264));
		_harePositions.add(new PositionData(52.128415,	21.069444));
		_harePositions.add(new PositionData(52.128297,	21.069742));
		_harePositions.add(new PositionData(52.128232,	21.069945));
		_harePositions.add(new PositionData(52.128167,	21.070232));
		_harePositions.add(new PositionData(52.128287,	21.070535));
		_harePositions.add(new PositionData(52.128376,	21.070857));
		_harePositions.add(new PositionData(52.128456,	21.07141));
		_harePositions.add(new PositionData(52.128502,	21.072029));
		_harePositions.add(new PositionData(52.128575,	21.072526));
		_harePositions.add(new PositionData(52.128677,	21.072933));
		_harePositions.add(new PositionData(52.128786,	21.073261));
		_harePositions.add(new PositionData(52.128945,	21.073636));
		_harePositions.add(new PositionData(52.129227,	21.073462));
		_harePositions.add(new PositionData(52.129378,	21.073344));
		_harePositions.add(new PositionData(52.12951,	21.073239));
		_harePositions.add(new PositionData(52.129642,	21.073129));
		_harePositions.add(new PositionData(52.129858,	21.072936));
		_harePositions.add(new PositionData(52.130009,	21.072821));
		_harePositions.add(new PositionData(52.130195,	21.072687));
		_harePositions.add(new PositionData(52.130399,	21.072528));
		_harePositions.add(new PositionData(52.130645,	21.072346));
		_harePositions.add(new PositionData(52.130798,	21.072206));
		_harePositions.add(new PositionData(52.131018,	21.072043));
		_harePositions.add(new PositionData(52.13119,	21.071909));
		_harePositions.add(new PositionData(52.131405,	21.071759));
		_harePositions.add(new PositionData(52.13139,	21.071488));
	}	
}

