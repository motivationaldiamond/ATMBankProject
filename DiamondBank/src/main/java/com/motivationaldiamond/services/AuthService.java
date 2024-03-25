package com.motivationaldiamond.services;

import com.motivationaldiamond.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);


}
