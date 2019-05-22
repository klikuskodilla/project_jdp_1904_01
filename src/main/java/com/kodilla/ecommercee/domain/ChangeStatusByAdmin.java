package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChangeStatusByAdmin {
    private String adminLogin;
    private String adminPassword;
    private Long userId;
    private boolean newStatus;
}
