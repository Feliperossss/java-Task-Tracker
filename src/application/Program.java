package application;

import java.io.IOException;
import java.time.LocalDateTime;

import entities.Task;
import entities.TaskManager;
import entities.task.enums.TaskState;
import service.TaskService;

public class Program {

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\proje\\OneDrive\\Desktop\\code\\java projects\\task manager";
		TaskService service = new TaskService(path);
		TaskManager  manager = new TaskManager(service);
		
		
        if (args.length == 0) {
            System.out.println("Nenhum comando informado.");
            return;
        }
        
        
        String idSearch;
        String command = args[0];

        switch (command) {

            case "add":{
            	 System.out.println("Adicionando tarefa...");
                 
                 int id = manager.generateId();
                 String taskTitle = args[1];
                 TaskState status = TaskState.TO_DO;
                 LocalDateTime creationTime = LocalDateTime.now();
                 LocalDateTime lastUpdate = LocalDateTime.now();
                 
                 manager.addTask(new Task(id,taskTitle,status,creationTime,lastUpdate));
                 break;
            }
                   
            case "delete":
                System.out.println("Removendo tarefa...");
                idSearch = args[1];
                manager.removeTask(Integer.parseInt(idSearch));
                break;
                
            case "update":{
            	
            	 System.out.println("Adicionando tarefa...");
                 
                 idSearch = args[1];
                 String taskTitle = args[2];
                 TaskState status = TaskState.TO_DO;
                 LocalDateTime creationTime = LocalDateTime.now();
                 LocalDateTime lastUpdate = LocalDateTime.now();
                 service.update(Integer.parseInt(idSearch), new Task(Integer.parseInt(idSearch),taskTitle,status,creationTime,lastUpdate));
                 break;  
            	
            }
            case "list":
                System.out.println("Listando tarefas...");
                manager.listTask();
                break;
                
            case "list-done":
                System.out.println("Listando tarefas...");
                manager.listDone();;
                break;
            case "list-to-do":
                System.out.println("Listando tarefas...");
                manager.listToDo();;;
                break;
                
            case "list-in-progress":
                System.out.println("Listando tarefas...");
                manager.listInProgress();
                break;
                
            case "mark-in-progress":
                System.out.println("Listando tarefas...");
                idSearch = args[1];
                manager.markInProgress(Integer.parseInt(idSearch));
                break;
                
            case "mark-done":
                System.out.println("Listando tarefas...");
                idSearch = args[1];
                manager.markDone(Integer.parseInt(idSearch));
                break;


            default:
                System.out.println("Comando inválido.");
        }
    }
		
		
	


}
