package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import entities.Task;



public class TaskService {

	public void addTask (Task task, String path) {
		 
		
		 String[] lines= task.toJson().split("\n");
		 
		  boolean fileExists = fileExists(path);
		  
		 try(BufferedWriter bw =  new BufferedWriter(new FileWriter(path + "\\TaskManager.json",fileExists))){
			 
			 for(String line: lines) {
				 bw.write(line);
				 bw.newLine();
			 }
			  	
		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
		 
		 
	 }
	
	
	private boolean fileExists(String path) {
		File file = new File(path);
		return file.exists();
	}
}
