package com.platform.user.services;

import com.platform.user.models.Token;
import com.platform.user.models.User;

public interface UserService {
    
    User signup(String name, String email, String password);
    
    Token login(String email, String password);
    
    User validateToken(String tokenValue);
    
}
