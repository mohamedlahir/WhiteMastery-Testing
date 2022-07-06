package com.whitemastery;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class DBAccess {

	
	MongoClient mongoClient = new MongoClient( "localhost" , 27017);

	public String db() {
	DB db = mongoClient.getDB("dine_pos");
	DBCollection dbCollection = db.getCollection("users");
//	DBCollection dbCollection_test = db.getCollection("activity_logs");
	
	BasicDBObject searchQuery = new BasicDBObject();
//	searchQuery.put("activity", "Cheesy Commando Fries");
	searchQuery.put("email", "ops@thepumphouse.in");
	DBCursor cursor = dbCollection.find(searchQuery);

	String response = "";
	 try {
	     while(cursor.hasNext()) {
	     response = response.concat(cursor.next().toString());
	     System.out.println(response);
	    }
	 } 
	 finally { 
	    cursor.close();
	 }
 return response;	
}
}