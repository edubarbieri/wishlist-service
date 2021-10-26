package br.com.edubarbieri.whishlist.infra.web;


import br.com.edubarbieri.whishlist.application.user.CreateUserInput;
import br.com.edubarbieri.whishlist.application.user.UpdateUserInput;
import br.com.edubarbieri.whishlist.application.user.CreateUser;
import br.com.edubarbieri.whishlist.application.user.DeleteUser;
import br.com.edubarbieri.whishlist.application.user.UpdateUser;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private AbstractRepositoryFactory repositoryFactory;

    public UserController(AbstractRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserInput request) {
        try {
            new CreateUser(repositoryFactory).execute(request);
            return ResponseEntity.ok().build();
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestBody @Valid UpdateUserInput request) {
        try {
            new UpdateUser(repositoryFactory).execute(request);
            return ResponseEntity.ok().build();
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable String userId) {
        try {
            new DeleteUser(repositoryFactory).execute(userId);
            return ResponseEntity.ok().build();
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }
    }
}
