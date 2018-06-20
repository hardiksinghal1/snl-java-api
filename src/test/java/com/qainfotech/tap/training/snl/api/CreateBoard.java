package com.qainfotech.tap.training.snl.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class CreateBoard {
	Board board;
	
	public Board createNewBoard() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		return new Board();
	}
	
	public Board openExistingBoard(UUID uuid) throws IOException {
		return new Board(uuid);
	}
	
}
