package com.techprimers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techprimers.model.User;

/*
 * We don't have to do any data source injection,
 * because Spring Boot detect that H2 is on the 
 * class path, and JdbcTemplate will use it.
 */
@Service
public class UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void insertUsers(List<User> users) {
		users.forEach(user -> {
			System.out.println("Inserting data for user: " + user.getName());
			jdbcTemplate.update("INSERT InTO user (name, salary) VALUES (?, ?)", ps -> {
				ps.setString(1, user.getName());
				ps.setLong(2, user.getSalary());
			});
		});
	}

	public List<User> getUsers() {
		System.out.println("Retrieving all users...");
		return jdbcTemplate.query("SELECT name,salary FROM user", (rs, rownum) -> {
			User user = new User(rs.getString(1), rs.getLong(2));
			return user;
		});
	}
}
