package com.todo.service;

import java.util.List;

import com.todo.model.User;
import com.todo.todolist.TodoItem;

public interface TodoService {

	public List<TodoItem> findAll(User owner);
	
	public  TodoItem getByID(int id);

	public void save(TodoItem todoItem);

	public void update(TodoItem todoItem);

	public void delete(int id);
}
