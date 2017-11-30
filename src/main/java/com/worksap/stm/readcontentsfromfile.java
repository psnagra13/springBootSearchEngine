package com.worksap.stm;

import java.util.*;
import java.io.*;

public class readcontentsfromfile {
	
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
	
	
	public node[] getData()
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
		
		
		for(int i=0;i<data.length;i++)
			System.out.println(dataobject[i].name);
		
		return dataobject;
		
	}
	
	
	
	
	
	
//	public static void main(String []args)
//	{
//		String s = "1 pardeep pard@gmail.com pass\r\n" + 
//				"2 sukhwinder sukh@gmail.com fail\r\n" + 
//				"3 goutam gout@gmail.com fail\r\n" + 
//				"4 ojasvi ojas@gmail.com pass\r\n" + 
//				"5 sandeep sand@gmail.com fail\r\n" + 
//				"6 shravan shra@gmail.com fail\r\n" + 
//				"7 abhinav abhi@gmail.com pass\r\n" + 
//				"8 ravi ravi@gmail.com fail\r\n" + 
//								"9 shalin shal@gmail.com pass";
//		
//		
//		String data[]= s.split("\n");
//		
//		
//		node dataobject[]= new node[data.length];
//		
//		for(int i=0; i<data.length;i++)
//		{
//			String temp[]=data[i].split(" ");
//			dataobject[i]=new node(Integer.parseInt(temp[0]),temp[1],temp[2],temp[3]);
//			
//			
//		}
//		
//		
//		for(int i=0;i<data.length;i++)
//			System.out.println(dataobject[i].name);
//		
//		
//		
//	
//	
//	}
	
	
	
	

}
