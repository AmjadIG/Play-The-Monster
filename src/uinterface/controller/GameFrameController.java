package uinterface.controller;

import java.awt.EventQueue;

import businesslogic.client.FacadeClient;
import businesslogic.client.StateGame;
import businesslogic.domain.unit.Monster;
import uinterface.views.GameFrame;
import uinterface.views.GameInterface;

public class GameFrameController {
	private FacadeClient facade;
	private GameFrame gameFrame;
	public GameFrameController(FacadeClient facade) {
		this.facade = facade;
	}
    public void launchGame(Monster m, StateGame s) {
    	GameInterface gi = GameInterface.createBoard();
    	//Map map = new Map();
    	m.setFacade(facade);
    	StateGame stateGame = s;
    	
    	gi.setGameState(stateGame);
    	stateGame.addMonster(m);
    	//stateGame.setMap(map);
    	gi.addKeyListener(m);
    	facade.setStateGame(stateGame);
  
    	this.gameFrame = new GameFrame(gi);
    	EventQueue.invokeLater(() ->{
			gameFrame.setVisible(true);
		});
    }
}
