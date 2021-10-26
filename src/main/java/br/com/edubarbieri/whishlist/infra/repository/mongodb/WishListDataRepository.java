package br.com.edubarbieri.whishlist.infra.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishListDataRepository extends MongoRepository<WishListDoc, String> {
}
