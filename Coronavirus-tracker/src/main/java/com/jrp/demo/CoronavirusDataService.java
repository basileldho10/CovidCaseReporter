package com.jrp.demo;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import models.LocationStats;

@Service
public class CoronavirusDataService {

	private static String data_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	
	private List<LocationStats> newCases = new ArrayList<>();
	
	
	
	public List<LocationStats> getNewCases() {
		return newCases;
	}
	
	
	public int totalCases(){
		int totalCases = 0;
		
		for (LocationStats cases: newCases) {
		      totalCases = totalCases + cases.getReportedCases();
		}
		return totalCases;
	}

	

	@PostConstruct
	@Scheduled(cron = "0 * * * * *") 
	public void fetchVirusData() throws IOException, InterruptedException {
		
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(data_URL))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());	
		StringReader csvReader = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		
		for (CSVRecord record : records) {
		LocationStats stat = new LocationStats();
		stat.setState(record.get("Province/State"));
	    stat.setCountry(record.get("Country/Region"));
	    stat.setReportedCases(Integer.parseInt(record.get(record.size()-1))); 
	    
	    System.out.println(stat);
	    newCases.add(stat);
	    

		}
		

	}
	
}
