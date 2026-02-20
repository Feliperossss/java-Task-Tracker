package application;

import java.time.LocalDateTime;

import entities.Task;
import entities.task.enums.TaskState;
import service.TaskService;

public class Program {

	public static void main(String[] args) {
		String path = "C:\\Users\\proje\\OneDrive\\Desktop\\code\\java projects\\task manager";
		TaskService tasks = new TaskService(path);
		
		Task task = new Task(
				1, 
				"Estudar Java",TaskState.valueOf("TO_DO"), LocalDateTime.parse("2026-02-20T15:30:00"), LocalDateTime.parse( "2026-02-20T16:00:00"));
		

		tasks.addTask(task);
		tasks.showTasks();
	}

}
