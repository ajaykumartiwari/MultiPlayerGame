/**
 * 
 */
package com.stackroute.maverick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.maverick.domain.MultiPlayerModel;
import com.stackroute.maverick.repository.MultiPlayerModelRepository;

/**
 * @author ajay
 *
 */
@Service
public class MultiPlayerModelServiceImpl implements MultiPlayerModelService{

	@Autowired
	MultiPlayerModelRepository multiPlayerModelRepo;


	//	@Override
	//	public Iterable<MultiPlayerModel> getAllQuestions() {
	//		// TODO Auto-generated method stub
	//		return multiPlayerModelRepo.findAll();
	//	}

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
	public MultiPlayerModel create(MultiPlayerModel multiPlayerModel) {
		// TODO Auto-generated method stub
		return multiPlayerModelRepo.save(multiPlayerModel);
	}


	@Override
	public Iterable<MultiPlayerModel> getAllQuestions() {
		// TODO Auto-generated method stub
		return multiPlayerModelRepo.findAll();
	}


	@Override
	public MultiPlayerModel update(MultiPlayerModel updateMultiPlayer) {
		// TODO Auto-generated method stub
		MultiPlayerModel updateModel =  multiPlayerModelRepo.save(updateMultiPlayer);
		
		return updateModel;
	}


}
