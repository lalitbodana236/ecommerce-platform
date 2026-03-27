package com.platform.user.services.impl;

import com.platform.user.exceptions.PasswordMismatchException;
import com.platform.user.models.Token;
import com.platform.user.models.User;
import com.platform.user.repositories.TokenRepository;
import com.platform.user.repositories.UserRepository;
import com.platform.user.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
       // token.setTokenValue(RandomStringUtils.randomAlphabetic(128));
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        Date dateAfter30Days = calendar.getTime();
      //  token.setExpiryAt(dateAfter30Days);
        
        Map<String,Object> claims = new HashMap<>();
        claims.put("iss","scalar.com");
        claims.put("userid",user.getEmail());
        claims.put("exp",dateAfter30Days);
        claims.put("roles",user.getRoles());
        
        
        MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
        SecretKey secretKey = macAlgorithm.key().build();
        String tokenValue = Jwts.builder().claims(claims).signWith(secretKey).compact();
       // String payload="";
        //byte[] payLoadBytes=payload.getBytes(StandardCharsets.UTF_8);
     //   String tokenValue = Jwts.builder().content(payLoadBytes).compact();
        token.setTokenValue(tokenValue);
        token = tokenRepository.save(token);
        return token;
    }
    
    @Override
    public User validateToken(String tokenValue) {
        
        Optional<Token> optionalToken = tokenRepository.findByTokenValueAndExpiryAtGreaterThan(tokenValue, new Date());
        return optionalToken.get().getUser();
    }
}
