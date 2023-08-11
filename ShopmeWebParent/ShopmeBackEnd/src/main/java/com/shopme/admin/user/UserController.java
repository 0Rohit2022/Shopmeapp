package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;
	
	
	@GetMapping("/users")
	public String listAll(Model model)
	{
		List<User> listUser = userservice.listAll();
		model.addAttribute("listUser", listUser);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model)
	{
		List<Role> listRoles = userservice.listRoles();
		User user = new User();
		user.setEnabled(true);
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectattribute)
	{
		System.out.println(user);
		userservice.save(user); 
		    
		redirectattribute.addFlashAttribute("message", "The user has been saved successfuly");
		return "redirect:/users";
	}
	
}
