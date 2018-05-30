package com.stackroute.maverick.service;

import java.util.Optional;

import com.stackroute.maverick.domain.MultiPlayerGameResponseData;

public interface MultiPlayerGameResponseService {

	public Iterable<MultiPlayerGameResponseData> getAllQuestions();

	public MultiPlayerGameResponseData store(MultiPlayerGameResponseData responseData);
	
	public void deleteAll();
	
	public Optional<MultiPlayerGameResponseData> findById(int questionId);
	
}
