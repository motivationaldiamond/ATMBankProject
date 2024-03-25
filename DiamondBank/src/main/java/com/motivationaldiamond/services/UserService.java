package com.motivationaldiamond.services;

import java.util.List;

import com.motivationaldiamond.entities.User;

public interface UserService {

	List<User> index();

	User findById(int id);

	User create(User newUser);

	User update(int id, User user);

	boolean delete(int id);

	boolean disableUser(int userId);

	boolean enableUser(int userId);

	User resetPassword(int userId, String newPassword);
}
