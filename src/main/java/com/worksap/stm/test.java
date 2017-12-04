package com.worksap.stm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

// to update inverted index into database from dictionary stored in file
// and to update table whith sno to contents




public class test {
	
	
	
	public static void main(String []args) throws ClassNotFoundException, IOException
	{
		String query = "sachin";
		String itemid ="8390.txt";
		String score = "1";
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
  	  Session session = cluster.connect();           
  	  
  	  session.execute("use  mysearchengine;"); 
  	  
  	String cass = "Select count(*) from indexTable;";  	  
    ResultSet result = session.execute(cass);	  
  	List<Row> al= result.all();  	  
    System.out.println(al);
  	  
  	  
    
  	  String cassquery = "Select * from indexTable where word = '" + query+"'  and score = "+ score+ " and sno= '" + itemid+ "';";//Getting the ResultSet
    
  	  ResultSet result2 = session.execute(cassquery);
  	  
  	  List<Row> al2= result2.all();
  	  System.out.println(al2);
      
  	List<String> temp = new ArrayList();
  	
  	   
  		temp.add(query);
  		temp.add( Integer.toString(Integer.parseInt(score) + 1));
  		temp.add(itemid);
  		
    
	    for(int i=0;i<al2.size();i++)
	    {
	    	Row r= al2.get(i);
	    	
	    	
	    	
	    	String title = r.getString("title");
	    	String snippet = r.getString("snippet");
	    	String date = r.getString("date");
	    	

	    	
	    	temp.add(title);
	    	temp.add(snippet);
	    	temp.add(date);

	    	
	    }
    
	    System.out.println(temp);
	    
	    
	    
	    
	    cassquery = "Delete  from indexTable where word = '" + query+"'  and score = "+ score+ " and sno= '" + itemid+ "';";//Getting the ResultSet	    
	  	session.execute(cassquery);
	  	
	  	
	  	
	  	cass = "Select count(*) from indexTable;";  	  
	    result = session.execute(cass);	  
	  	al= result.all();  	  
	    System.out.println(al);
	  	
	    
	    
	    
	    cassquery = "INSERT INTO indexTable (word,title,sno,score,date,snippet)"
				
		 				+ " VALUES(" + "'" +   temp.get(0).replace("'", "")+ "','"+ temp.get(3).replace("'", "") +   "','" + temp.get(2)+   "'," + temp.get(1)  +   ",'" + temp.get(5) +     "','" + temp.get(4).replace("'", "")      + "');" ;
	    session.execute(cassquery);
	    
	    
	    
		cass = "Select count(*) from indexTable;";  	  
	    result = session.execute(cass);	  
	  	al= result.all();  	  
	    System.out.println(al);
	    
	    
	    
	    
	    
	    
	    
	    
	    
    session.close();
    
   cluster.close();
  	  
  	  
  	  
  	  
  	  
  	  
  	  
  	  
  	  
  	  
  	  
  	  
  	  
		
		
	}
	
	
	
	
	
	
	

}
