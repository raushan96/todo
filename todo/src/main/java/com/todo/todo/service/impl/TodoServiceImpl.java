package com.todo.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.todo.model.User;
import com.todo.todo.repository.TodoItemRepository;
import com.todo.todo.service.TodoService;
import com.todo.todo.todolist.TodoItem;

@Service("todoService")
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoItemRepository todoItemRepository;

	@Override
	public List<TodoItem> findAll(User owner) {
		// TODO Auto-generated method stub
		
		return todoItemRepository.findByOwner(owner);
	}

	@Override
	public void save(TodoItem todoItem) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return todoItemRepository.findTodoItemById(id);
	}

}