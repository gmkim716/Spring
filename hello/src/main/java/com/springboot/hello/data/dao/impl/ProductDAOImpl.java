package com.springboot.hello.data.dao.impl;

import com.springboot.hello.data.dao.ProductDAO;
import com.springboot.hello.data.entity.Product;
import com.springboot.hello.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    // orElse: Optional 객체가 비어있을 때 반환할 값을 지정한다
    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.findById(number).orElse(null);
        return selectedProduct;
    }

    // 주의, JPA에서는 값을 갱신할 때 update를 사용하지 않고, 영속성 컨텍스트를 활용해 값을 갱신한다
    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);  // Q. Optional: 무엇인가요?

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            product.setName(name);
            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    // 삭제 또한 영속성 컨텍스트에 가져와서 삭제 요청을 전달한다
    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            productRepository.deleteById(number);
        } else {
            throw new Exception();
        }
    }
}
