package com.chika.server.services.product;

import com.chika.server.models.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAllByUserId(Long userId);

    String checkListProduct(List<Product> products);

    void updateProductWithUserId(Long userId, List<Product> products);
}
