package com.mail.service;

import com.mail.domain.User;

public interface UserEmailService {
	public User saveUser(User user);
	Boolean veryfyToken(String token);

}
