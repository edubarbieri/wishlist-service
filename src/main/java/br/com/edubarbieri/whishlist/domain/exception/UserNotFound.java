package br.com.edubarbieri.whishlist.domain.exception;

public class UserNotFound extends DomainException {
    public UserNotFound(String id) {
        super(String.format("Could not find user with id %s", id));
    }
}
