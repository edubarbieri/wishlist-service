package br.com.edubarbieri.whishlist.infra.web;

import br.com.edubarbieri.whishlist.application.dto.ItemWishListRequest;
import br.com.edubarbieri.whishlist.application.dto.QueryWishListResultItem;
import br.com.edubarbieri.whishlist.application.wishlist.*;
import br.com.edubarbieri.whishlist.domain.exception.DomainException;
import br.com.edubarbieri.whishlist.domain.factory.AbstractRepositoryFactory;
import br.com.edubarbieri.whishlist.infra.security.AuthUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WishListController {

    private final AbstractRepositoryFactory repositoryFactory;

    public WishListController(AbstractRepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @GetMapping("/api/user/wishlist")
    public ResponseEntity<List<QueryWishListResultItem>> getWishList(Authentication user) {
        try {
            var userId = getUser(user);
            List<QueryWishListResultItem> result = new GetUserWishList(repositoryFactory).execute(userId);
            return ResponseEntity.ok(result);
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }
    }

    @PostMapping("/api/user/wishlist")
    public ResponseEntity<Void> addItemInWishList(@RequestBody @Valid ItemWishListRequest request, Authentication user) {
        try {
            var userId = getUser(user);
            AddItemToWishListInput input = new AddItemToWishListInput(userId, request.getProductId());
            new AddItemToWishList(repositoryFactory).execute(input);
            return ResponseEntity.ok().build();
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }
    }

    @DeleteMapping("/api/user/wishlist")
    public ResponseEntity<Void> removeItemFromWishList(@RequestBody @Valid ItemWishListRequest request, Authentication user) {
        try {
            var userId = getUser(user);
            RemoveItemFromWishListInput input = new RemoveItemFromWishListInput(userId, request.getProductId());
            new RemoveItemFromWishList(repositoryFactory).execute(input);
            return ResponseEntity.ok().build();
        } catch (DomainException d) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, d.getMessage(), d);
        }
    }

    private String getUser(Authentication user) {
        if (!(user.getPrincipal() instanceof AuthUserDetails)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not get current user");
        }
        return ((AuthUserDetails) user.getPrincipal()).getUserId();
    }
}
