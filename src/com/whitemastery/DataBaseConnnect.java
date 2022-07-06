package com.whitemastery;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class DataBaseConnnect {
	
	public void dBTest() {

	MongoClient mongoClient = new MongoClient( "localhost1" , 27017);
	DB db = mongoClient.getDB("dbName");
	DBCollection dbCollection = db.getCollection("collectionName");
	BasicDBObject searchQuery = new BasicDBObject();
	searchQuery.put("key", "value");
	DBCursor cursor = dbCollection.find(searchQuery);
	}
}
