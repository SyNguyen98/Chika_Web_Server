package com.chika.server.services.product;

import com.chika.server.models.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProductService {

    List<Product> getAllByUserId(Long userId);

    Map<String, Long> getAllNumberOfProduct();

    Map<String, Long> getAllNumberOfProductByUserId(Long userId);

    String checkListProduct(List<Product> products);

    void updateProductWithUserId(Long userId, List<Product> products);
}
