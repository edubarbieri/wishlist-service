package br.com.edubarbieri.whishlist.infra.web;


import br.com.edubarbieri.whishlist.application.dto.CreateUserInput;
import br.com.edubarbieri.whishlist.application.dto.UpdateUserInput;
import br.com.edubarbieri.whishlist.application.usecase.CreateUser;
import br.com.edubarbieri.whishlist.application.usecase.DeleteUser;
import br.com.edubarbieri.whishlist.application.usecase.UpdateUser;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user")
public class UserCommandController {

    private AbstractRepositoryFactory repositoryFactory;

    public UserCommandController(AbstractRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserInput request) {
        try {
            new CreateUser(repositoryFactory).execute(request);
            return ResponseEntity.ok().build();
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UpdateUserInput request) {
        try {
            new UpdateUser(repositoryFactory).execute(request);
            return ResponseEntity.ok().build();
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable String userId) {
        try {
            new DeleteUser(repositoryFactory).execute(userId);
            return ResponseEntity.ok().build();
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }
    }
}
