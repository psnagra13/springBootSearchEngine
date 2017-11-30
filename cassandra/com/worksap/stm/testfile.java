package com.worksap.stm;

import java.io.IOException;
import java.nio.file.*;


public class testfile {

	
	
	public static void main(String []args) throws IOException
	{
		byte data[] = {1, 2, 3, 4, 5};
		
		
		Path file = Paths.get("abc");
		Files.write(file, data);
		
	}
}
