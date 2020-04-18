package com.chika.server.services.product;

import com.chika.server.models.product.Product;
import com.chika.server.models.product.ProductResponse;
import com.chika.server.models.product.RfProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProductService {

    List<ProductResponse> getAllByUserId(Long userId);

    Map<String, Long> getAllNumberOfProduct();

    Map<String, Long> getAllNumberOfProductByUserId(Long userId);

    List<RfProduct> getAllRfProductByUserId(Long userId);

    String checkListProduct(List<Product> products);

    void updateProductWithUserId(Long userId, List<Product> products);
}
