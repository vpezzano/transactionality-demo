package com.techprimers.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techprimers.model.User;

/*
 * We don't have to do any data source injection, because Spring Boot detects that H2 is on the
 * class path, and JdbcTemplate will use it. On startup, Spring Boot will determine the schema
 * according to the file schema.sql located in the folder resources.
 * 
 */
@SuppressWarnings("unused")
@Service
public class UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void insertUsers(List<User> users) {
//		users.forEach(user -> {
//			System.out.println("Inserting data for user: " + user.getName());
//		
//			jdbcTemplate.update("INSERT INTO user (name, salary) VALUES (?, ?)", new PreparedStatementSetter() {
//				@Override
//				public void setValues(PreparedStatement ps) throws SQLException {
//					ps.setString(1, user.getName());
//					ps.setLong(2, user.getSalary());
//
//				}
//			});
//		});

		// The following block of code is leveraging lambdas and it is used in place of
		// the previous one:
		users.forEach(user -> {
			System.out.println("Inserting data for user: " + user.getName());
			
			jdbcTemplate.update("INSERT INTO user (name, salary) VALUES (?, ?)", ps -> {
				ps.setString(1, user.getName());
				ps.setLong(2, user.getSalary());
			});
		});
	}

	public List<User> getUsers() {
		System.out.println("Retrieving all users...");

//		return jdbcTemplate.query("SELECT name,salary FROM user", new RowMapper<User>() {
//			@Override
//			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//				User user = new User(rs.getString(1), rs.getLong(2));
//				return user;
//			}
//		});

		// The following block of code is leveraging lambdas and it is used in place of
		// the previous one:
		return jdbcTemplate.query("SELECT name,salary FROM user", (rs, rownum) -> {
			User user = new User(rs.getString(1), rs.getLong(2));
			return user;
		});
	}
}
