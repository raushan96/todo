package com.todo.repository;





import static org.assertj.core.api.Assertions.assertThat;

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

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = TodoApplication.class)
@TestPropertySource(
		  locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;
    
    
    
	@Test
	public void whenFindByEmail_thenReturnUser() {
	    // given
	    
	    User raushan = new User();
	    raushan.setEmail("raushan10dm087@gmail.com");
	    raushan.setName("Raushan");
	    raushan.setLastName("Kumar");
	    raushan.setPassword("1223456");
	    entityManager.persist(raushan);
	    entityManager.flush();
	    
	    // when
	    User found = userRepository.findByEmail(raushan.getEmail());
	 
	    // then
	   assertThat(found.getName()).isEqualTo(raushan.getName());
	}
}
