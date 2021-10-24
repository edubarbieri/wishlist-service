package br.com.edubarbieri.whishlist.infra.clients;


import br.com.edubarbieri.whishlist.application.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product", url = "${rest-clients.product.url}")
public interface ProductServiceClient {

    @GetMapping("/api/product/{productId}/")
    ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") String productId);
}
