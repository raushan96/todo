package com.todo.todo.service;

import com.todo.todo.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);	
}
