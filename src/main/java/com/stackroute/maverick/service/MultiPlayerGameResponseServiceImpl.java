/**
 * 
 */
package com.stackroute.maverick.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.stackroute.maverick.domain.MultiPlayerGameResponseData;
import com.stackroute.maverick.repository.MultiPlayerGameResponseDataRepository;

/**
 * @author ajay
 *
 */
public class MultiPlayerGameResponseServiceImpl implements MultiPlayerGameResponseService{

	@Autowired
	MultiPlayerGameResponseDataRepository multiPlayerResponseDataRepo;
	
	@Override
	public Iterable<MultiPlayerGameResponseData> getAllQuestions() {
		// TODO Auto-generated method stub
		return multiPlayerResponseDataRepo.findAll();
	}

	@Override
	public MultiPlayerGameResponseData store(MultiPlayerGameResponseData responseData) {
		// TODO Auto-generated method stub
		multiPlayerResponseDataRepo.save(responseData);
		return responseData;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		multiPlayerResponseDataRepo.deleteAll();
	}

	@Override
	public Optional<MultiPlayerGameResponseData> findById(int questionId) {
		// TODO Auto-generated method stub
		Optional<MultiPlayerGameResponseData> responseDataById = multiPlayerResponseDataRepo.findById(questionId);
		
		return responseDataById;
	}

}
