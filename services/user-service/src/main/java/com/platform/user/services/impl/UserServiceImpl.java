package com.platform.user.services.impl;

import com.platform.user.exceptions.PasswordMismatchException;
import com.platform.user.models.Token;
import com.platform.user.models.User;
import com.platform.user.repositories.TokenRepository;
import com.platform.user.repositories.UserRepository;
import com.platform.user.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private final TokenRepository tokenRepository;
    
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }
    
    @Override
    public User signup(String name, String email, String password) {
        //check if there's already a user with this email or not;
        
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            //redirect to login page
            return optionalUser.get();
        }
        
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setName(name);
        return userRepository.save(user);
    }
    
    @Override
    public Token login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            //redirect to login page
            throw new UsernameNotFoundException("email not found");
        }
        User user = optionalUser.get();
        boolean isValid = bCryptPasswordEncoder.matches(password, user.getPassword());
        if (!isValid)
            throw new PasswordMismatchException("invalid password");
        
        Token token = new Token();
        token.setUser(user);
        token.setTokenValue(RandomStringUtils.randomAlphabetic(128));
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        Date dateAfter30Days = calendar.getTime();
        token.setExpiryAt(dateAfter30Days);
        token = tokenRepository.save(token);
        return token;
    }
    
    @Override
    public User validateToken(String tokenValue) {
        
        Optional<Token> optionalToken = tokenRepository.findByTokenValueAndExpiryAtGreaterThan(tokenValue, new Date());
        return optionalToken.get().getUser();
    }
}
