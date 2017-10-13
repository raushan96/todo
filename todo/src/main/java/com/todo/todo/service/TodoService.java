package com.todo.todo.service;

import java.util.List;

import com.todo.todo.model.User;
import com.todo.todo.todolist.TodoItem;

public interface TodoService {

	public List<TodoItem> findAll(User owner);
	
	public  TodoItem getByID(int id);

	public void save(TodoItem todoItem);

	public void update(TodoItem todoItem);

	public void delete(int id);
}
