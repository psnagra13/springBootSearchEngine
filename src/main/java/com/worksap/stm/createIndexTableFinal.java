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
import com.datastax.driver.core.Session;

// to update inverted index into database from dictionary stored in file
// and to update table whith sno to contents




public class createIndexTableFinal {
	
	
	
	public static void main(String []args) throws ClassNotFoundException, IOException
	{
		Map<String, List<  List<String>            >> index = readFile();

		Map<String,  List<String>     > contents= new HashMap();
		
		
		
		
		 for (String key : index.keySet()) {
			 
			 
			 List l= index.get(key);
			 
			 	
			 
			 for (int i=0;i<l.size();i++)
			 {
				 List temp= (List) l.get(i);
				 
				 List temp2 = new ArrayList();
				 
				 String sno= (String) temp.get(0);
				 String title= (String) temp.get(1);
				 String date= (String) temp.get(2);
				 String snippet= (String) temp.get(3);
				 String data= (String) temp.get(4);
				 
				 
				 temp2.add(title);
				 temp2.add(date);
				 temp2.add(snippet);
				 temp2.add(data);

				 contents.put(sno, temp2);

	
				
			 }
					 
		 }
		
		 
//		insert(index);
		 insertDocuments(contents);

		
		
	}
	
	
	
	
	
	
	

	public static void insertDocuments(Map<String,  List<String>     > index2)
	{
		
		 //creating Cluster object
		  Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
		  //Creating Session object
		  Session session = cluster.connect();           
		  
		  session.execute("use mysearchengine;");
		  
		  session.execute("Drop TABLE if exists indexContents");
		  
		 
		  String query = "CREATE TABLE if not exists indexContents("
				    
				  	+ "sno text, "
			         + "title text,   date text,  contents text ,"
			       
			         + " primary key(sno)) ;";

		  session.execute(query);
		  
 
		  for (String key : index2.keySet()) {
			  if(key.replace("'","").length()==0) continue;
			  List ll= index2.get(key);
			  
			  String title= (String) ll.get(0);			  
			  String date= (String) ll.get(1);
			  String data= (String) ll.get(3);

			  System.out.println( key);
			  
				
				String query2 = "INSERT INTO indexContents (sno,title,date,contents)"
						
		 				+ " VALUES(" + "'" +   key.replace("'", "")+ "','"+ title.replace("'", "") +     "','"+ date + "','"+   data.replace("'", "") +      "');" ;
			  
			  
				 try {

					  session.execute(query2);
					} catch (Exception e){

						System.out.println( query2 + "                 " + e);

					}

			 
			    
			}
		
		  session.close();
		  cluster.close();
		  
	}
		
	
	
	
	
	public static void insert(Map<String, List<  List<String>            >> index2)
	{
		
		 //creating Cluster object
		  Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
		  //Creating Session object
		  Session session = cluster.connect();           
		  
		  
		  session.execute("Drop KEYSPACE if exists mysearchengine");

		  String query1 = "CREATE KEYSPACE if not exists mysearchengine WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};";    
		  session.execute(query1);     
		  
		  
		  session.execute("use mysearchengine;");
		  
		  session.execute("Drop TABLE if exists indexTable");
		  
		 
		  String query = "CREATE TABLE if not exists indexTable("
				    
				  	+ "word text, "
			         + "title text,   sno text, score int , date text, snippet text , "
			       
			         + " primary key(word , score,sno , title))  WITH CLUSTERING ORDER BY (score DESC);";

		  session.execute(query);
		  
 
		  for (String key : index2.keySet()) {
			  System.out.println( key);
			  List ll = index2.get(key);
			  if(key.replace("'","").length()==0) continue; 
			  
			  if(key.replace("'","")=="ride") continue; 
			  
			  
			  for (int i=0;i<ll.size();i++)
			  {
				  
				  List temp= (List) ll.get(i);
				  
				  String sno= (String) temp.get(0);
				  String title= (String) temp.get(1);
					 String date= (String) temp.get(2);
					 String snippet= (String) temp.get(3);
					 String data= (String) temp.get(4);
				  
				  
				  String query2 = "INSERT INTO indexTable (word,title,sno,score,date,snippet)"
							
		 				+ " VALUES(" + "'" +   key.replace("'", "")+ "','"+ title.replace("'", "") +   "','" + sno +   "'," + "1"   +   ",'" + date +     "','" + snippet.replace("'", "")      + "');" ;
			  
				  try {

					  session.execute(query2);
					} catch (Exception e){

						System.out.println( query2 + "                 " + e);

					}
			 
				  
				  
				  
				  
				 
				  
				  
				  
				  
			  }
			  
			  
			  

			 
			  
			  
				
			    
			}
		
		  
		  
		  
		
		  
		  
		  
		  
		  
		 
		  
		  
		  
		  
		  session.close();
		  cluster.close();
		  
				
				
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static Map<String, List<  List<String>      >>readFile()
            throws ClassNotFoundException, IOException
    {
		String PATH = "invertedIndexFinal.txt";
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(PATH))) {
            return (Map<String, List<  List<String>            >>) is.readObject();
        }
    }

}
