package br.com.edubarbieri.whishlist.domain.exception;

public class EmailAlreadyRegistered extends DomainException {
    public EmailAlreadyRegistered(String email) {
        super(String.format("The email address %s is already in use", email));
    }
}
