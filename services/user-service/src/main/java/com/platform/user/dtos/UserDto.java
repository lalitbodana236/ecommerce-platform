package com.platform.user.dtos;

import com.platform.user.models.Role;
import com.platform.user.models.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long userId;
    private String email;
    private List<Role> roles;
    
    public static UserDto fromUser(User user) {
        if (user == null) return null;
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setEmail(user.getName());
        return userDto;
    }
}
