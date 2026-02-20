package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Task;

public class TaskService {

	
	private String path;
	
	
	public TaskService(String path) {
		
		this.path = path + "\\TaskManager.json";
	}
	
	


	public void addTask(List<Task> list) {

		

 
		
		List<String> lines = taskToJson(list);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path , fileExists(path)))) {

			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showTasks() {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}
	
	public List<String>  taskToJson(List<Task> list) {
		List<String> allLines = new ArrayList<>();
		for(Task x: list) {
			String[] lines = x.toJson().split("\n");
			Collections.addAll(allLines, lines);
		}
		if(list.isEmpty()) throw new RuntimeException("no tasks");
		
		return allLines;
 	}
	
	

	private boolean fileExists(String path) {
		File file = new File(path);
		return file.exists();
	}
}
