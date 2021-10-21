package br.com.edubarbieri.whishlist.domain.exception;

public class InvalidEmailException extends DomainException {
    public InvalidEmailException() {
        super("Invalid email address");
    }
}
