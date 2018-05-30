package com.stackroute.maverick.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.maverick.domain.MultiPlayerModel;

@Repository
public interface MultiPlayerModelRepository extends CrudRepository<MultiPlayerModel, Integer>{

	public MultiPlayerModel findByGameId(int gameId);
	
}
