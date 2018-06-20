package com.qainfotech.tap.training.snl.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONArray;



public class ManagePlayer {
	Board board;
	public ManagePlayer(Board board) {
		this.board = board;
	}
	
	public JSONArray addNewPlayerToBoard(String name) throws FileNotFoundException, UnsupportedEncodingException,
				PlayerExistsException, GameInProgressException, 
				MaxPlayersReachedExeption, IOException {
		return board.registerPlayer(name);
	}
	
	public void deletePlayer(UUID playerUuid) throws FileNotFoundException, UnsupportedEncodingException, 
				NoUserWithSuchUUIDException {
		board.deletePlayer(playerUuid);
	}
	
	public void rollTheDice(UUID playerUuid) throws FileNotFoundException, UnsupportedEncodingException,
				InvalidTurnException {
		board.rollDice(playerUuid);
	}
	
}
