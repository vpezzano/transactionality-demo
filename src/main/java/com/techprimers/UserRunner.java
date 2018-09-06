package com.techprimers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.techprimers.model.User;
import com.techprimers.service.UserService;

@Component
public class UserRunner implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("Peter", 12000L);
		User user2 = new User("Sam", 22000L);
		
		try {
			userService.insertUsers(Arrays.asList(user1, user2));
		} catch (RuntimeException e) {
            System.out.println("A problem occurred while executing batch 1: " + e.getMessage());
		}

		User user3 = new User("Alexander", 32000L);
		User user4 = new User("Nick", 18000L);

		try {
			userService.insertUsers(Arrays.asList(user3, user4));
		} catch (RuntimeException e) {
            System.out.println("A problem occurred while executing batch 2: " + e.getMessage());
		}
		
		System.out.println("Current users: " + userService.getUsers());
	}
}
