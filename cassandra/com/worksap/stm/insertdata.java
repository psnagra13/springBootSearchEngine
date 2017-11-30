package com.worksap.stm;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.worksap.stm.createtable.node;

public class insertdata {
	
	
	
	

	



	   public static void main(String args[]){  
		   
	                      
	      //creating Cluster object
	      Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();    
	      //Creating Session object
	      Session session = cluster.connect();  
	      
	      
	
	      String query1 = "CREATE KEYSPACE if not exists studentsdata WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};";    
	      session.execute(query1);  	      
	      session.execute("use studentsdata");   
	      
	      session.execute("DROP TABLE if exists studentsdata;");
	      
	      
	      String query = "CREATE TABLE if not exists studentbyid("
	    		    +"id int , " 
	    		  	+ "name text, "
	    	         + "email text, "
	    	         + "result text ,"
	    	         + " primary key(id));";
	      session.execute(query);
	      
	      node data[]=getData();   // System.out.println(data[0].id);
	      

	      
	      for (int i=0;i<data.length;i++)
	      {
	    	  
	    	  String query2 = "INSERT INTO studentbyid (id, name, email, result)"
	    				
         				+ " VALUES(" + Integer.toString(data[i].id)  +",'" +   data[i].name  +"','"+ data[i].email  + "','"+ data[i].result   + "');" ;
	    	  
	    	  System.out.println(query2);
	    	  
	    	  session.execute(query2);
	      }
	      
	      

	      
	      
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
			String s = "1 pardeep pard@gmail.com pass\n" + 
					"2 sukhwinder sukh@gmail.com fail\n" + 
					"3 goutam gout@gmail.com fail\n" + 
					"4 ojasvi ojas@gmail.com pass\n" + 
					"5 sandeep sand@gmail.com fail\n" + 
					"6 shravan shra@gmail.com fail\n" + 
					"7 abhinav abhi@gmail.com pass\n" + 
					"8 ravi ravi@gmail.com fail\n" + 
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
