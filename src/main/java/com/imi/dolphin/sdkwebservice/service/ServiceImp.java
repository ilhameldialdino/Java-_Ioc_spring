package com.imi.dolphin.sdkwebservice.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imi.dolphin.sdkwebservice.util.OkHttpUtil;

import okhttp3.Request;
import okhttp3.Response;

@Service
public class ServiceImp implements IService {
	private static final Logger log = LogManager.getLogger(ServiceImp.class);

	@Autowired
	OkHttpUtil okHttpUtil;
	
	
	@Override
	public String getData(String country){
		
		int deaths 			= 0;
		int confirmed 		= 0;
		String lastReported = "";
		String lastChecked 	= "";
		String location 	= "";
		
		try {
			okHttpUtil.init(true);
			Request request = new Request.Builder()
								.url("https://covid-19-coronavirus-statistic.p.rapidapi.com/v1/total?country="+country)
								.get()
								.addHeader("X-RapidAPI-Key","4305915b43msh9bd7a7d4839e182p14433ejsnbeab4ac658f1")
								.addHeader("X-RapidAPI-Host", "covid-19-coronavirus-statistics.p.rapidapi.com")
								.build();
			
			Response response = okHttpUtil.getClient().newCall(request).execute();
			String responseJSON = response.body().string();
			log.debug("doSchedule() responseJSON: {}", responseJSON);
			JSONObject object 	= new JSONObject(responseJSON);
			JSONObject data 	= object.getJSONObject("data");
			deaths				= data.getInt("deaths");
			confirmed			= data.getInt("confirmed");
			lastReported		= data.getString("lastReported");
			lastChecked			= data.getString("lastChecked");
			location 			= data.getString("location");
						
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				
				connection 			= DriverManager.getConnection("jdbc:mysql://localhost:8081","root","");
				String sqlQuery 	= "insert into country_data (deaths, confirmed, lastChecked, lastReported, location)" + "values (?,?,?,?,?)";
				statement 			= connection.prepareStatement(sqlQuery);  
				statement.setInt(1, deaths);
				statement.setInt(2, confirmed);
				statement.setString(3, lastChecked);
				statement.setString(4, lastReported);
				statement.setString(5, location);
				statement.execute();
				connection.close();

			}catch(Exception e){
				log.debug("getMessageBody() {}", e.getMessage());
			}
			
		}catch(Exception e){
			log.debug("getMessageBody() {}", e.getMessage());
		}
		
		System.out.println(location);
		return location;
		
	}

}
