package com.aifeng.service;

import com.aifeng.dao.ProductRepository;
import com.aifeng.dao.ProductSlideRepository;
import com.aifeng.model.Product;
import com.aifeng.model.ProductSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

/**
 * Created by pro on 17-3-21.
 */
@Service
public class ProductSlideService {

    private final ProductSlideRepository productSlideRepository;
    @Autowired
    public ProductSlideService(ProductSlideRepository productRepository) {
        this.productSlideRepository = productRepository;
    }

    @Transactional
    public void saveSlide(String[] imgPaths,Product product) {
        for (String imgPath : imgPaths) {
            ProductSlide productSlide = new ProductSlide();
            productSlide.setImg(imgPath);
            productSlide.setName(imgPath.substring(imgPath.lastIndexOf(File.separator)) + 1);
            productSlide.setCreateTime(new Date());
            productSlide.setProduct(product);
            productSlideRepository.save(productSlide);
        }
    }

    @Transactional
    public void delSlide() {

    }
}
