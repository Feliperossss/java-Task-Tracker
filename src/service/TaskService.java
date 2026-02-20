package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entities.Task;

public class TaskService {

	
	private String path;
	
	
	public TaskService(String path) {
		
		this.path = path + "\\TaskManager.json";
	}

	public void addTask(Task task) {

		String[] lines = task.toJson().split("\n");

		boolean fileExists = fileExists(path);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path , fileExists))) {

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

	private boolean fileExists(String path) {
		File file = new File(path);
		return file.exists();
	}
}
