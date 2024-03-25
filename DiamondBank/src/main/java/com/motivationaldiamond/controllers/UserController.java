package com.motivationaldiamond.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motivationaldiamond.entities.User;
import com.motivationaldiamond.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users")
	public List<User> index() {
		return userService.index();
	}

	@GetMapping("users/{id}")
	public User findById(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
		User user = userService.findById(id);
		if (user == null) {
			response.setStatus(404);
		}
		return user;
	}

	@PostMapping("users")
	public User create(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		User newUser = userService.create(user);
		if (newUser == null) {
			response.setStatus(400);
			return null;
		} else {
			response.setStatus(201);
			response.setHeader("Location", request.getRequestURL().append("/").append(newUser.getId()).toString());
			return newUser;
		}
	}

	@PutMapping("users/{id}")
	public User update(@PathVariable("id") Integer id, @RequestBody User user, HttpServletResponse res) {
		user = userService.update(id, user);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}

	@DeleteMapping("users/{id}")
	public void delete(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (userService.delete(id)) {
				response.setStatus(204);
			} else {
				response.setStatus(404);
			}
		} catch (Exception e) {
			response.setStatus(400);
			e.printStackTrace();
		}
	}

	@PutMapping("users/{id}/disable")
	public ResponseEntity<?> disableUser(@PathVariable("id") int id) {
		if (userService.disableUser(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("users/{id}/enable")
	public ResponseEntity<?> enableUser(@PathVariable("id") int id) {
		if (userService.enableUser(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("users/{id}/resetpassword")
	public ResponseEntity<?> resetPassword(@PathVariable("id") int id, @RequestBody String newPassword) {
		User updatedUser = userService.resetPassword(id, newPassword);
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
