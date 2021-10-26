package br.com.edubarbieri.whishlist.domain.exception;

public class PasswordNotMatch extends DomainException {
    public PasswordNotMatch() {
        super("Password and confirm password don't match");
    }
}
