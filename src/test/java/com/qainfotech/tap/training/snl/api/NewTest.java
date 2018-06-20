package com.qainfotech.tap.training.snl.api;

import java.util.UUID;
import java.util.Random;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.JSONArray;
import org.json.JSONException;

public class NewTest {

	CreateBoard createboard;
	ManagePlayer manageplayer;
	Board board;

	@Test(expectedExceptions = { MaxPlayersReachedExeption.class })
	public void checkMaxPlayerLimit() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException,
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
	public void playNonExistingPlayer() throws FileNotFoundException, UnsupportedEncodingException, IOException,
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

	//@Test
	public void registerDuringGameInProgress() throws FileNotFoundException, UnsupportedEncodingException, IOException,
			JSONException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		board = createboard.createNewBoard();
		manageplayer = new ManagePlayer(board);
		//UUID uuid = (UUID) manageplayer.addNewPlayerToBoard("NewPlayer").getJSONObject(0).get("uuid");

		// UUID uid = (UUID)
		// manageplayer.addNewPlayerToBoard("NewPlayer2").getJSONObject(0).get("uuid");
		// System.out.print(uid);
	}

	// @Test
	public void nonExistingPlayerShouldNotRollDice() {
		
	}

	@Test(expectedExceptions = { NoUserWithSuchUUIDException.class })
	public void deleteNotExistingUser() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, NoUserWithSuchUUIDException {
		board = createboard.createNewBoard();
		manageplayer = new ManagePlayer(board);
		manageplayer.addNewPlayerToBoard("NewPlayer");
		UUID uid = UUID.randomUUID();
		manageplayer.deletePlayer(uid);
	}

	// @Test
	public void wrongTurnPlayerRollingDice() {
		
	}

	@BeforeClass
	public void beforeClass() {
		createboard = new CreateBoard();

	}

	@AfterClass
	public void afterClass() {

	}

}
