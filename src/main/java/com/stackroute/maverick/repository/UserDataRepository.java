package com.stackroute.maverick.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.maverick.domain.UserGameData;

@Repository
public interface UserDataRepository extends CrudRepository<UserGameData, Integer> {

}
