package com.worksap.stm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;






public class insertToDatabase {
	
	
	
	public static void main(String []args) throws ClassNotFoundException, IOException
	{
		HashMap<String , List<String >> index = readFile();
		HashMap<String , String> index2= new HashMap();
		

		
	for (String key : index.keySet()) {
				
				List<String> al= index.get(key);
				Set<String> hs = new HashSet<String>();
				hs.addAll(al);
		     	al.clear();
			    al.addAll(hs);
			    String list="";
			    
			    for(int i=0;i<al.size();i++)
			    {
			    	list += al.get(i) + "-";
			    	
			    }
			    
			    key= key.replace("\n", "").replace(" ", "").replace("'", "");
			    if(key.length()==0)continue;
			    
			    index2.put(key, list);
			    
			}
		
		
		
	System.out.println(index2.get("terroriststoday's"));
		
		insert(index2);
		
		
	}
	
	public static void insert(HashMap<String , String> index2)
	{
		
		 //creating Cluster object
		  Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
		  //Creating Session object
		  Session session = cluster.connect();           
		  
		  
		  session.execute("Drop KEYSPACE if exists searchIndex");

		  String query1 = "CREATE KEYSPACE if not exists searchIndex WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};";    
		  session.execute(query1);     
		  
		  
		  session.execute("use searchIndex;");
		  
		  session.execute("Drop TABLE if exists indexTable");
		  
		 
		  String query = "CREATE TABLE if not exists indexTable("
				    
				  	+ "word text, "
			         + "keys text, "
			       
			         + " primary key(word));";

		  session.execute(query);
		  
 
		  for (String key : index2.keySet()) {
				
				String al= index2.get(key).replace("\n", "");
				
				String query2 = "INSERT INTO indexTable (word,keys)"
						
		 				+ " VALUES(" + "'" +   key+ "','"+ al.replace("'", "") +  "');" ;
			  
			  
			 
				  session.execute(query2);
				  System.out.println("inserting: " + query2);
			 
			  
			  
				
			    
			}
		
		  
		  
		  
		
		  
		  
		  
		  
		  
		 
		  
		  
		  
		  
		  session.close();
		  cluster.close();
		  
				
				
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static HashMap<String, List<String >> readFile()
            throws ClassNotFoundException, IOException
    {
		String PATH = "invertedIndex.txt";
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(PATH))) {
            return (HashMap<String, List<String >>) is.readObject();
        }
    }

}
