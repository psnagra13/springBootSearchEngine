package com.worksap.stm;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.worksap.stm.keyspacecreate.node;

public class createtable {
	
	



	   public static void main(String args[]){  
		   
	                      
	      //creating Cluster object
	      Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
	      //Creating Session object
	      Session session = cluster.connect();           
	
	      String query1 = "CREATE KEYSPACE if not exists studentsdata WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};";    
	      session.execute(query1);   
	      
	      session.execute("use studentsdata");
      



//	      node data[]=getData();   // System.out.println(data[0].id); 
	      
	      String query = "CREATE TABLE if not exists studentbyid("
	    		    +"id int , " 
	    		  	+ "name text, "
	    	         + "email text, "
	    	         + "result text ,"
	    	         + " primary key(id));";

	      session.execute(query);
	      
	      
//	      String query = "ALTER TABLE studentsdata ADD emp_email text";
//	      String query = "ALTER TABLE studentsdata DROP emp_email;";
	      
//	      String query = "DROP TABLE studentsdata;";
	      
	      
//	      String query = "Truncate studentsdata;";
	      
	      
	      session.close();
	      cluster.close();
	      
	      
	   }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   public static class node{
			
			int id;
			String name;
			String email;
			String result;
			
			node(int i, String n, String e, String r)
			{
				id=i;name=n;email=e;result=r;
			}
		}
		
		
		public static node[] getData()
		{
			String s = "1 pardeep pard@gmail.com pass\r\n" + 
					"2 sukhwinder sukh@gmail.com fail\r\n" + 
					"3 goutam gout@gmail.com fail\r\n" + 
					"4 ojasvi ojas@gmail.com pass\r\n" + 
					"5 sandeep sand@gmail.com fail\r\n" + 
					"6 shravan shra@gmail.com fail\r\n" + 
					"7 abhinav abhi@gmail.com pass\r\n" + 
					"8 ravi ravi@gmail.com fail\r\n" + 
									"9 shalin shal@gmail.com pass";
			
			
			String data[]= s.split("\n");
			
			
			node dataobject[]= new node[data.length];
			
			for(int i=0; i<data.length;i++)
			{
				String temp[]=data[i].split(" ");
				dataobject[i]=new node(Integer.parseInt(temp[0]),temp[1],temp[2],temp[3]);
				
				
			}
			
			
//			for(int i=0;i<data.length;i++)
//				System.out.println(dataobject[i].name);
			
			return dataobject;
			
		}
	   
	   
		
		
		
		

}
