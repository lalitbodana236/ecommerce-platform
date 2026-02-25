package com.platform.user.controllers;

import com.platform.user.dtos.LoginRequestDto;
import com.platform.user.dtos.SignupRequestDto;
import com.platform.user.dtos.TokenResponseDto;
import com.platform.user.dtos.UserDto;
import com.platform.user.models.Token;
import com.platform.user.models.User;
import com.platform.user.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequestDto requestDto) {
        User user = userService.signup(requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        
        return UserDto.fromUser(user);
    }
    
    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto requestDto) {
        Token token = userService.login(requestDto.getEmail(), requestDto.getPassword());
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setTokenValue(token.getTokenValue());
        tokenResponseDto.setEmail(token.getUser().getEmail());
        tokenResponseDto.setExpiryAt(token.getExpiryAt());
        return tokenResponseDto;
    }
    
    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") String token) {
        User user = userService.validateToken(token);
        return UserDto.fromUser(user);
    }

//    public logout(){
//
//    }
}
