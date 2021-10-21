package br.com.edubarbieri.whishlist.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserInput {
    private String name;
    private String email;

    public CreateUserInput(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
