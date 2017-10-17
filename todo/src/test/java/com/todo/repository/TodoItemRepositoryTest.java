package com.todo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.todo.TodoApplication;
import com.todo.model.User;
import com.todo.todolist.TodoItem;



@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = TodoApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TodoItemRepositoryTest {

	@Autowired
	private TodoItemRepository todoItemRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void testSetUp() {
		
	}
	//@Test
	public void whenfindByOwner_thenReturnToDoList() {
		
		
		// given User
		User raushan = new User();
	    raushan.setEmail("raushan10dm087@gmail.com");
	    raushan.setName("Raushan");
	    raushan.setLastName("Kumar");
	    raushan.setPassword("1223456");
	    entityManager.persist(raushan); 
	    //Given Todo List
	    
	    List<TodoItem> todoList=new ArrayList<>();
	    TodoItem td1=new TodoItem();
	    td1.setName("Need to travel");
	    td1.setTitle("travelling to Gurgoan on 25th");
	    td1.setOwner(raushan);
	    
	    TodoItem td2=new TodoItem();
	    td2.setName("Need to Buy Tv");
	    td2.setTitle("Hoping to get best deals on ecommerce app");
	    td2.setOwner(raushan);
	    todoList.add(td1);
	    todoList.add(td2);
	    
		entityManager.persist(td1);
		entityManager.persist(td2);
		entityManager.flush();

		// when
		List<TodoItem> foundList = todoItemRepository.findByOwner(raushan);

		// then
		assertThat(foundList.size()).isEqualTo(todoList.size());
	}
	
	@Test
	public void whenfindTodoItemById_thenReturnToDoItem() {
		
		
		// given User
		User raushan = new User();
	    raushan.setEmail("raushan10dm087@gmail.com");
	    raushan.setName("Raushan");
	    raushan.setLastName("Kumar");
	    raushan.setPassword("1223456");
	    entityManager.persist(raushan); 
	    
	    //Given Todo Item
	    TodoItem td1=new TodoItem();
	    td1.setName("Need to travel");
	    td1.setTitle("travelling to Gurgoan on 25th");
	    td1.setOwner(raushan);
	    td1.setId(1);
	    
	    //entityManager.persist(td1);
	    entityManager.merge(td1);
	    entityManager.flush();
		

		// when
		 TodoItem found = todoItemRepository.findTodoItemById(1);

		// then
		assertThat(found.getId()).isEqualTo(td1.getId());
	}
}
