package com.worksap.stm;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import java.util.*;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class retreivedata {
	
	
	
	public static void main(String []args)
	{
		//creating Cluster object
	    Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
	    //Creating Session object
	    Session session = cluster.connect(); 
	    session.execute("use studentsdata"); 
	    
	    
	    String query = "Select * from studentbyid ;";//Getting the ResultSet
	    
	    ResultSet result = session.execute(query);
	      
//	    System.out.println(result.all());
	    
	    
	    List<Row> al= result.all();
	    
	    for(int i=0;i<al.size();i++)
	    {
	    	Row r= al.get(i);
	    	System.out.println(r);
	    	
	    	System.out.println(r.getInt("id"));
	    	
	    	
	    }
	    
	    
	    session.close();
	    
	   cluster.close();
	    
	    
	    
	    
	}
    
    

}
