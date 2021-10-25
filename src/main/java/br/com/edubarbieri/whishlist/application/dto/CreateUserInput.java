package br.com.edubarbieri.whishlist.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserInput {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    public CreateUserInput(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
