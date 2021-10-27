package br.com.edubarbieri.whishlist.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthenticationRequest {
    @Email
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
