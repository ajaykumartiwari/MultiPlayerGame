package com.stackroute.maverick.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.maverick.domain.ReportingData;

@Repository
public interface ReportDataRepository extends CrudRepository<ReportingData, Integer>{

}
