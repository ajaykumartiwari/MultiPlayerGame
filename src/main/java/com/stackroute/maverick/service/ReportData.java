package com.stackroute.maverick.service;

import com.stackroute.maverick.domain.MultiPlayerGameResponseData;
import com.stackroute.maverick.domain.ReportingData;
import com.stackroute.maverick.domain.Users;

public interface ReportData {

	public ReportingData setQuestionData(MultiPlayerGameResponseData responseData);

	public void saveReportingData(ReportingData reportingData);
	
 public void deleteData();
	
	
}
