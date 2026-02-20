package entities;

import java.time.LocalDateTime;
import java.util.Objects;

import entities.task.enums.TaskState;

public class Task {
	private int id;
	private String description;
	private TaskState status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	

	public Task(int id, String description, TaskState status, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TaskState getStatus() {
		return status;
	}
	public void setStatus(TaskState status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return id == other.id;
	}
	
	
	public String toJson() {
	    StringBuilder sb = new StringBuilder();

	    sb.append("{");

	    sb.append("\"id\": ").append(id).append(",");
	    sb.append("\"description\": \"")
	      .append(description.replace("\"", "\\\""))
	      .append("\",");

	    sb.append("\"status\": \"")
	      .append(status.name())
	      .append("\",");

	    sb.append("\"createdAt\": \"")
	      .append(createdAt.toString())
	      .append("\",");

	    sb.append("\"updatedAt\": \"")
	      .append(updatedAt.toString())
	      .append("\"");

	    sb.append("}");

	    return sb.toString();
	}
	
}
