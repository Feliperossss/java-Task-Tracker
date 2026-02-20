package application;

import java.time.LocalDateTime;
import java.util.Arrays;

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
		Task task1 = new Task(
		        2,
		        "Revisar POO",
		        TaskState.valueOf("IN_PROGRESS"),
		        LocalDateTime.parse("2026-02-21T09:00:00"),
		        LocalDateTime.parse("2026-02-21T11:00:00"));

		Task task2 = new Task(
		        3,
		        "Fazer exercícios de algoritmos",
		        TaskState.valueOf("TO_DO"),
		        LocalDateTime.parse("2026-02-22T14:00:00"),
		        LocalDateTime.parse("2026-02-22T16:30:00"));

		Task task3 = new Task(
		        4,
		        "Implementar CRUD do projeto",
		        TaskState.valueOf("IN_PROGRESS"),
		        LocalDateTime.parse("2026-02-23T10:00:00"),
		        LocalDateTime.parse("2026-02-23T13:00:00"));

		Task task4 = new Task(
		        5,
		        "Testar aplicação",
		        TaskState.valueOf("DONE"),
		        LocalDateTime.parse("2026-02-24T15:00:00"),
		        LocalDateTime.parse("2026-02-24T17:00:00"));

		Task task5 = new Task(
		        6,
		        "Refatorar código",
		        TaskState.valueOf("TO_DO"),
		        LocalDateTime.parse("2026-02-25T08:30:00"),
		        LocalDateTime.parse("2026-02-25T10:00:00"));
		
		tasks.addTask(Arrays.asList(task,task2,task3,task4,task5));
		tasks.showTasks();
		
		

        if (args.length == 0) {
            System.out.println("Nenhum comando informado.");
            return;
        }

        String command = args[0];

        switch (command) {

            case "add":
                System.out.println("Adicionando tarefa...");
                break;

            case "list":
                System.out.println("Listando tarefas...");
                break;

            case "delete":
                System.out.println("Removendo tarefa...");
                break;

            default:
                System.out.println("Comando inválido.");
        }
    }
		
		
	


}
