package com.worksap.stm;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class createKeyspace {
	
	public static void main(String []args)
	{
//		indexTable ob = new indexTable();
//		
//		ob.connect("127.0.0.1", 9042);
//		
//		ob.createKeyspace("indexTable", "SimpleStrategy", 1);
//		
		
        
  //creating Cluster object
  Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
  //Creating Session object
  Session session = cluster.connect();           
  
  
  session.execute("Drop KEYSPACE if exists searchEngine");

  String query1 = "CREATE KEYSPACE if not exists searchEngine WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};";    
  session.execute(query1);     
  
  
  session.execute("use searchEngine;");
  
 
  String query = "CREATE TABLE if not exists searchHistory("
		    +"id int , " 
		  	+ "word text, "
	         + "time int, "
	       
	         + " primary key(id,time));";

  session.execute(query);
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  for (int i=0;i<11;i++)
  {

        				  String word= "demoWord"+ Integer.toString(i);
	  
	  long millis = System.currentTimeMillis() ;
	  int t= (int) millis;
	String query2 = "INSERT INTO searchHistory (id, word, time)"
			
	 				+ " VALUES(" + "1"  +",'" +   word+ "',"+ Integer.toString(t) +  ");" ;
	  
	  
	  
	  
	  session.execute(query2);
	  
  }
  
  
  
  
  
  
  
  
 
  
  
  
  
  session.close();
  cluster.close();
  
		
		
		
		
		
		
		
		
		
	}

}
