package com.stackroute.maverick.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.maverick.domain.MultiPlayerGameResponseData;

@Repository
public interface MultiPlayerGameResponseDataRepository extends CrudRepository<MultiPlayerGameResponseData, Integer>{

}
