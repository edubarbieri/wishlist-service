package br.com.edubarbieri.whishlist.infra.web;

import br.com.edubarbieri.whishlist.application.dto.AddItemToWishListInput;
import br.com.edubarbieri.whishlist.application.dto.AddItemToWishListRequest;
import br.com.edubarbieri.whishlist.application.usecase.AddItemToWishList;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class WishListCommandController {

    private AbstractRepositoryFactory repositoryFactory;

    public WishListCommandController(AbstractRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @PostMapping("/api/user/{userId}/wishlist")
    public ResponseEntity<?> addItemInWishList(@PathVariable String userId, @RequestBody AddItemToWishListRequest request) {
        try {
            AddItemToWishListInput input = new AddItemToWishListInput(userId, request.getProductId());
            new AddItemToWishList(repositoryFactory).execute(input);
            return ResponseEntity.ok().build();
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }

    }
}
