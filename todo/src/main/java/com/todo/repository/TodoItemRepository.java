package com.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.model.User;
import com.todo.todolist.TodoItem;

@Repository("todoItemRepository")
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

	public List<TodoItem> findByOwner(User owner);

	public TodoItem findTodoItemById(int id);
}
