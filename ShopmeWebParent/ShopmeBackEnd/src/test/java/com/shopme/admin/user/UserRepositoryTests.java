package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	//Inserting the new User with only one role
	@Test
	public void TestCreateNewUserWithOneRole()
	{
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userNameHM = new User("name@codejava.net", "name23", "name", "nam e");
		userNameHM.addRole(roleAdmin);
		
		User savedUser = userRepo.save(userNameHM);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	//Inserting the single User With One role
	@Test
	public void TestCreateUserWithSingleRole()
	{
		Role roleAdmin = new Role(4);
		User userName = new User("rolex@gmail.com", "rolex", "rolexsharma", "sharma");
		userName.addRole(roleAdmin);
		
		User savedUser = userRepo.save(userName);
		assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	//Inserting the new User with two role
	@Test
	public void TestCreateNewUserWithTwoRole()
	{
		User userRavi = new User("ravi@gmail.com", "ravi","raviShashtri", "kumar" );
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		
		User savedUser = userRepo.save(userRavi);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	
	
	//Inserting the new User with three role
	@Test
	public void TestCreateNewuserwithThreeRole()

	{
		User userRahul = new User("Rahul@gmail.com", "Rahulkumar", "rahul", "kumar");
		Role roleAdmin = new Role(1);
		Role roleEditor = new Role(3);
		Role roleAssitant = new Role(5);
		
		userRahul.addRole(roleAdmin);
		userRahul.addRole(roleEditor);
		userRahul.addRole(roleAssitant);
		User savedUser = userRepo.save(userRahul);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	
	//Getting the user with its id
	@Test
	public void TestGetUserById()
	{
		User user = userRepo.findById(1).get();
		System.out.println(user);
		assertThat(user).isNotNull();
		
	}
	
	
	//Getting the all users which are present inside the database
	@Test
	public void TestListAllUsers()
	{
		Iterable<User> listUsers =  userRepo.findAll();
		listUsers.forEach(user -> System.out.println(user));
		
	}
	
	//Updating the method which is present inside the database
	@Test 
	public void TestUpdateListById()
	{
		User user = userRepo.findById(1).get();
		
		user.setEmail("jarvis@gmail.com");
		user.setPassword("tonystark");
		user.setFirstName("jarvis");
		user.setLastName("Jarvis Jarvis");
		user.setEnabled(true);
		System.out.println(user);
		User savedUser = userRepo.save(user);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	
	//Updating the roles of user using findbyid method
	@Test
	public void TestUpdateRoleByid()
	{
		User user = userRepo.findById(3).get();
		
		Role roleAdmin = new Role(1);
		Role roleSalesperson = new Role(5);
		
		user.getRoles().remove(roleSalesperson);
		
		user.addRole(roleAdmin);
		
		User savedUser = userRepo.save(user);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	
	//Testing the user deletion from the database
	@Test
	public void TestDeleteById()
	{
		Integer userId = 4;
		userRepo.deleteById(userId);
	}
}
