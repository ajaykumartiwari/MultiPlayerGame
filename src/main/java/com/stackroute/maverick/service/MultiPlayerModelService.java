package com.stackroute.maverick.service;

import com.stackroute.maverick.domain.MultiPlayerGame;
import com.stackroute.maverick.domain.MultiPlayerModel;

public interface MultiPlayerModelService {
//
//	public Iterable<MultiPlayerModel> getAllQuestions();

	public MultiPlayerModel storeQuestion(MultiPlayerModel multiPlayerModel);
	
	public  MultiPlayerModel findByGameId(int gameId);
	
	public MultiPlayerModel create(MultiPlayerGame  multiPlayerGame);
	public Iterable<MultiPlayerModel> getAllQuestions();
	MultiPlayerModel update(MultiPlayerModel updateMultiPlayer);
}
