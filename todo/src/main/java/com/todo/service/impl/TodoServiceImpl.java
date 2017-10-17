package com.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.model.User;
import com.todo.repository.TodoItemRepository;
import com.todo.service.TodoService;
import com.todo.todolist.TodoItem;

@Service("todoService")
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoItemRepository todoItemRepository;

	@Override
	public List<TodoItem> findAll(User owner) {
		
		
		return todoItemRepository.findByOwner(owner);
	}

	

	@Override
	public void update(TodoItem todoItem) {
		todoItemRepository.save(todoItem);

	}

	@Override
	public void delete(int id) {
		
		TodoItem todoItem=todoItemRepository.findTodoItemById(id);
		todoItemRepository.delete(todoItem);
	}

	@Override
	public TodoItem getByID(int id) {
		
		return todoItemRepository.findTodoItemById(id);
	}

}
