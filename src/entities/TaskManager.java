package entities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import entities.task.enums.TaskState;
import service.TaskService;

public class TaskManager {

	private TaskService service;

	public TaskManager(TaskService service) throws IOException {
		this.service = service;
		load();
	}

	List<Task> list = new LinkedList<>();

	public void addTask(Task task) {

		list.add(task);
		service.add(task);
	}

	public void listTask() {
		if (list.isEmpty()) {
			System.out.println("lista vazia");
		}

		for (Task x : list) {
			System.out.println(x.toJson());
			System.out.println(" ");
		}
	}

	public void removeTask(int id) {
		list.removeIf(x -> x.getId() == id);
		service.remove(id);
	}

	public void updateTask(int id, Task task) {
		service.update(id, task);
		Optional<Task> obj = list.stream().filter(x -> x.getId()== id).findFirst();
		if(!obj.isPresent()) {
			throw new RuntimeException("id não encontrado");
		}
		list.add(list.indexOf(obj.get()), task);
		list.remove(obj.get());
	}

	public void markInProgress(int id) {
		Optional<Task> obj = list.stream().filter(x -> x.getId()== id).findFirst();
		if(!obj.isPresent()) {
			throw new RuntimeException("id não encontrado");
		}
		obj.get().setStatus(TaskState.IN_PROGRESS);
		updateTask(id, obj.get());
	}
	public void markDone(int id) {
		Optional<Task> obj = list.stream().filter(x -> x.getId()== id).findFirst();
		if(!obj.isPresent()) {
			throw new RuntimeException("id não encontrado");
		}
		obj.get().setStatus(TaskState.DONE);
		updateTask(id, obj.get());
	}
	
	public void listDone() {
		for(Task x: list) {
			if(x.getStatus() == TaskState.DONE) {
				System.out.println(x.toJson());
			}
		}
	}
	
	public void listToDo() {
		for(Task x: list) {
			if(x.getStatus() == TaskState.TO_DO) {
				System.out.println(x.toJson());
			}
		}
	}
	
	public void listInProgress() {
		for(Task x: list) {
			if(x.getStatus() == TaskState.IN_PROGRESS) {
				System.out.println(x.toJson());
			}
		}
	}
	
	
	private void setList(List<Task> list) {
		this.list = list;
	}

	private void load() throws IOException {
		setList( service.jsonToTask());
	}
	
	
	public int generateId() {
		return list.size() +1;
	}
}
