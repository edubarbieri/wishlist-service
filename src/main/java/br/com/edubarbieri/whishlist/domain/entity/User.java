package br.com.edubarbieri.whishlist.domain.entity;

import br.com.edubarbieri.whishlist.domain.exception.InvalidEmailException;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class User {
    private static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private String id;
    private String name;
    private String email;
    private String password;

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        if(!this.validateEmail(email)){
            throw new InvalidEmailException();
        }
        this.name = name;
        this.email = email;
        this.password = password;
    }

    private boolean validateEmail(String email){
        var matcher = VALID_EMAIL_REGEX.matcher(email);
        return matcher.find();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        if(!this.validateEmail(email)){
            throw new InvalidEmailException();
        }
        this.email = email;
    }
}
