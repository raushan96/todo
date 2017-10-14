package com.todo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.todo.model.User;
import com.todo.service.TodoService;
import com.todo.service.UserService;
import com.todo.todolist.TodoItem;

@Controller
@RequestMapping("/admin/home")
public class TodoUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TodoService todoService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName",
				"Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		List<TodoItem> todoList = new ArrayList<>();
		todoList = todoService.findAll(user);
		modelAndView.addObject("adminMessage", "Current User TODO List:");
		modelAndView.addObject("items", todoList);
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void getList(@ModelAttribute TodoItem item,HttpServletResponse response) throws IOException {
		todoService.delete(item.getId());
		response.sendRedirect("/admin/home");
	}

	@RequestMapping(value = "/list/{id}", method = { RequestMethod.GET })
	public ModelAndView edit(@ModelAttribute TodoItem item) {
		ModelAndView modelAndView = new ModelAndView();
		item = todoService.getByID(item.getId());
		modelAndView.addObject("item", item);
		modelAndView.setViewName("admin/todo-edit-create");
		return modelAndView;
	}
	
	@RequestMapping(value = "/create", method = { RequestMethod.GET })
	public ModelAndView getTodoForm(@ModelAttribute TodoItem item) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", item);
		modelAndView.setViewName("admin/todo-edit-create");
		return modelAndView;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST )
	public void updateToDoItem(@ModelAttribute TodoItem item,HttpServletResponse response) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		item.setOwner(user);
		todoService.update(item);
		response.sendRedirect("/admin/home");
		
		
	}
}
