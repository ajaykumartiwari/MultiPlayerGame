package com.stackroute.maverick.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.maverick.domain.ReportingData;

@Repository
public interface ReportDataRepository extends CrudRepository<ReportingData, Integer>{

	public Optional<ReportingData> findByUserId(int userId);

	

}
