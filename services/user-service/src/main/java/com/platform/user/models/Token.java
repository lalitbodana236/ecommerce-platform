package com.platform.user.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Token extends BaseModel {
    private String tokenValue;
    private Date expiryAt;
    @ManyToOne
    private User user;
}
