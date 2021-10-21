package br.com.edubarbieri.whishlist.domain.entity;

import br.com.edubarbieri.whishlist.domain.exception.InvalidEmailException;

import java.util.regex.Pattern;

public class User {
    private static final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private String name;
    private String email;

    public User(String name, String email) {
        if(!this.validateEmail(email)){
            throw new InvalidEmailException();
        }
        this.name = name;
        this.email = email;
    }

    private boolean validateEmail(String email){
        var matcher = VALID_EMAIL_REGEX.matcher(email);
        return matcher.find();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
