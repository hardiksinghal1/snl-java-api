package com.qainfotech.tap.training.snl.api;

import java.util.UUID;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.JSONException;

public class NewTest {

	CreateBoard createboard;
	ManagePlayer manageplayer;
	Board board;

	@Test(expectedExceptions = { MaxPlayersReachedExeption.class })
	public void checkMaxPlayerLimit() 
			throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption, IOException {
		board = createboard.createNewBoard();
		manageplayer = new ManagePlayer(board);
		manageplayer.addNewPlayerToBoard("First Player");
		manageplayer.addNewPlayerToBoard("Second Player");
		manageplayer.addNewPlayerToBoard("Third Player");
		manageplayer.addNewPlayerToBoard("Fourth Player");

		manageplayer.addNewPlayerToBoard("Extra Invalid Player");
	}

	@Test(expectedExceptions = { InvalidTurnException.class })
	public void nonExistingPlayerShouldNotRollDice() 
			throws FileNotFoundException, UnsupportedEncodingException, IOException,
			PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
		board = createboard.createNewBoard();
		manageplayer = new ManagePlayer(board);
		UUID uid = UUID.randomUUID();
		manageplayer.addNewPlayerToBoard("NewPlayer");
		manageplayer.rollTheDice(uid);
	}

	@Test(expectedExceptions = { PlayerExistsException.class })
	public void registeringPlayerWithExistingNameShouldNotWork()
			throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException,
			GameInProgressException, MaxPlayersReachedExeption {
		board = createboard.createNewBoard();
		manageplayer = new ManagePlayer(board);
		manageplayer.addNewPlayerToBoard("NewPlayer");
		manageplayer.addNewPlayerToBoard("NewPlayer");
	}

	@Test(expectedExceptions = {GameInProgressException.class})
	public void registerDuringGameInProgress() 
			throws FileNotFoundException, UnsupportedEncodingException, IOException,
			JSONException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
		board = createboard.createNewBoard();
		manageplayer = new ManagePlayer(board);
		UUID uuid = UUID.fromString((String)manageplayer.addNewPlayerToBoard("NewPlayer").getJSONObject(0).get("uuid"));
		manageplayer.rollTheDice(uuid);
		manageplayer.addNewPlayerToBoard("New Invalid Player");
	}

	@Test(expectedExceptions = { NoUserWithSuchUUIDException.class })
	public void deleteNotExistingUser() 
			throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, NoUserWithSuchUUIDException {
		board = createboard.createNewBoard();
		manageplayer = new ManagePlayer(board);
		manageplayer.addNewPlayerToBoard("NewPlayer");
		manageplayer.addNewPlayerToBoard("NewPlayer2");
		UUID uid = UUID.randomUUID();
		manageplayer.deletePlayer(uid);
	}

	@Test(expectedExceptions = { InvalidTurnException.class })
	public void wrongTurnPlayerRollingDice() 
			throws FileNotFoundException, UnsupportedEncodingException, IOException, JSONException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
		board = createboard.createNewBoard();
		manageplayer = new ManagePlayer(board);
		UUID uuid1 = UUID.fromString((String)manageplayer.addNewPlayerToBoard("NewPlayer").getJSONObject(0).get("uuid"));
		UUID uuid2 = UUID.fromString((String)manageplayer.addNewPlayerToBoard("NewPlayer2").getJSONObject(1).get("uuid"));
		
		if(board.getData().getInt("turn")==0) {
			manageplayer.rollTheDice(uuid2);
		}
		else {
			manageplayer.rollTheDice(uuid1);
		}
		
	}
	
	@Test()
	public void playingUserShouldBeAbleToQuitTheGame()
			throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, NoUserWithSuchUUIDException, InvalidTurnException {
		board = createboard.createNewBoard();
		manageplayer = new ManagePlayer(board);
		UUID uuid1 = UUID.fromString((String)manageplayer.addNewPlayerToBoard("NewPlayer").getJSONObject(0).get("uuid"));
		manageplayer.rollTheDice(uuid1);
		manageplayer.deletePlayer(uuid1);
	}
	
	

	@BeforeClass
	public void beforeClass() {
		createboard = new CreateBoard();

	}

	@AfterClass
	public void afterClass() {

	}

}
