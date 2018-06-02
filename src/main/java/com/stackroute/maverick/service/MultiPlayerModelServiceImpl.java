/**
 * 
 */
package com.stackroute.maverick.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.MultiPlayerGame;
import com.stackroute.maverick.domain.MultiPlayerModel;
import com.stackroute.maverick.domain.MultipleQuestions;
import com.stackroute.maverick.repository.MultiPlayerModelRepository;

/**
 * @author ajay
 *
 */
@Service
public class MultiPlayerModelServiceImpl implements MultiPlayerModelService {

	@Autowired
	MultiPlayerModelRepository multiPlayerModelRepo;

	@Autowired
	MultiPlayerModel multiPlayerModel;

	@Autowired
	MultipleQuestions multipleQuestions;

	// @Override
	// public Iterable<MultiPlayerModel> getAllQuestions() {
	// // TODO Auto-generated method stub
	// return multiPlayerModelRepo.findAll();
	// }

	@Override
	public MultiPlayerModel storeQuestion(MultiPlayerModel multiPlayerModel) {
		// TODO Auto-generated method stub
		multiPlayerModelRepo.save(multiPlayerModel);
		return multiPlayerModel;
	}

	@Override
	public MultiPlayerModel findByGameId(int gameId) {
		// TODO Auto-generated method stub
		MultiPlayerModel multiPlayerModel = multiPlayerModelRepo.findByGameId(gameId);
		return multiPlayerModel;
	}

	@Override
	public MultiPlayerModel create(MultiPlayerGame multiPlayerGame) {
		// TODO Auto-generated method stub
		// return multiPlayerModelRepo.save(multiPlayerModel);

		List<MultipleQuestions> questions = new ArrayList<MultipleQuestions>();
		multiPlayerModel.setGameId(multiPlayerGame.getGameId());
		for (int i = 0; i < multiPlayerGame.getAutoquestions().size(); i++) {

			multipleQuestions.setCorrectAnswer(multiPlayerGame.getAutoquestions().get(i).getCorrectAnswer());
			multipleQuestions.setOp1(multiPlayerGame.getAutoquestions().get(i).getOption1());
			multipleQuestions.setOp2(multiPlayerGame.getAutoquestions().get(i).getOption2());
			multipleQuestions.setOp3(multiPlayerGame.getAutoquestions().get(i).getOption3());
			multipleQuestions.setOp4(multiPlayerGame.getAutoquestions().get(i).getOption4());
			multipleQuestions.setQuestionId(multiPlayerGame.getAutoquestions().get(i).getQuestionId());
			multipleQuestions.setQuestionStamp(multiPlayerGame.getAutoquestions().get(i).getQuestionStem());
			questions.add(multipleQuestions);
			multiPlayerModel.setQuestions(questions);

		}

		multiPlayerModel.setGameSessionId(multiPlayerModel.getGameSessionId());
		
		multiPlayerModelRepo.save(multiPlayerModel);
		
		return null;
	}

	@Override
	public Iterable<MultiPlayerModel> getAllQuestions() {
		// TODO Auto-generated method stub
		return multiPlayerModelRepo.findAll();
	}

	@Override
	public MultiPlayerModel update(MultiPlayerModel updateMultiPlayer) {
		// TODO Auto-generated method stub
		MultiPlayerModel updateModel = multiPlayerModelRepo.save(updateMultiPlayer);

		return updateModel;
	}

}
