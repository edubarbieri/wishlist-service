package br.com.edubarbieri.whishlist.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserInput {
    private String id;
    private String name;
    private String email;

    public UpdateUserInput(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
