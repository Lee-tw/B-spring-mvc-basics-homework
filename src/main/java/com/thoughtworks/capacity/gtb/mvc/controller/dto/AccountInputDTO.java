package com.thoughtworks.capacity.gtb.mvc.controller.dto;

import com.thoughtworks.capacity.gtb.mvc.model.Account;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
public class AccountInputDTO {
    @NotBlank
    @Email(message = "Email不合法")
    private final String email;

    @Length(min = 5, max = 12, message = "用户名不合法")
    private final String name;

    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)(?![0-9_]+$)(?![0-9a-zA-Z]+$)(?![a-zA-Z_]+$)[0-9A-Za-z_]{5,12}$", message = "密码不合法")
    private final String password;

    public Account toAccount() {
        return Account.builder().email(email).name(name).password(password).build();
    }
}
