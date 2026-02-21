package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entities.Task;
import entities.task.enums.TaskState;

public class TaskService {

	private String path;

	public TaskService(String path) {

		this.path = path + "\\TaskManager.json";
	}

	public void add(Task task) {

		String[] lines = task.toJson().split("\n");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, fileExists(path)))) {

			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void remove(int id) {

		boolean found = false;
		List<String> lines = new ArrayList<>();
		List<String> filterLines = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		for (String line : lines) {
			if (returnId(line) == id) {
				found = true;
			} else {
				filterLines.add(line);
			}
		}

		if (!found) {
			throw new RuntimeException("erro: id não encontrado");
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (String line : filterLines) {
				bw.write(line);
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void update(int id, Task task) {
		boolean found = false;
		List<String> lines = new ArrayList<>();
		List<String> filterLines = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		for (String line : lines) {
			if (returnId(line) == id) {
				found = true;
				filterLines.add(task.toJson());
			} else {
				filterLines.add(line);
			}
		}

		if (!found) {
			throw new RuntimeException("erro: id não encontrado");
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (String line : filterLines) {
				bw.write(line);
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	public List<Task> jsonToTask() throws IOException {
						
	
		int id = 0;
		String description = null;
		TaskState status = null;
		LocalDateTime createdAt = null;
		LocalDateTime updatedAt = null;
		List<Task> load = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String json= br.readLine();
			while(json!= null) {
				json = json.substring(1, json.length() - 1);
				String[] campos = json.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				
				for(String line: campos) {
					
					String[] chaveValor = line.split(":", 2);
					
					String chave = chaveValor[0].replace("\"", "").trim();
			        String valor = chaveValor[1].replace("\"", "").trim();

			        switch (chave) {
			            case "id":
			                id = Integer.parseInt(valor);
			                break;

			            case "description":
			                description = valor;
			                break;

			            case "status":
			                status = TaskState.valueOf(valor);
			                break;

			            case "createdAt":
			                createdAt = LocalDateTime.parse(valor);
			                break;

			            case "updatedAt":
			                updatedAt = LocalDateTime.parse(valor);
			                break;
			        }
			 }
				
				load.add(new Task(id, description, status, createdAt, updatedAt));
				json= br.readLine();
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		return load;

	}

	private boolean fileExists(String path) {
		File file = new File(path);
		return file.exists();
	}

	private int returnId(String line) {
		int start = line.indexOf("\"id\"");
		int colon = line.indexOf(":", start);
		int comma = line.indexOf(",", colon);
		String idStr = line.substring(colon + 1, comma).trim();
		int id = Integer.parseInt(idStr);
		return id;

	}

}
