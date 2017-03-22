package com.aifeng.service;

import com.aifeng.dao.ProductRepository;
import com.aifeng.dao.ProductSlideRepository;
import com.aifeng.model.Product;
import com.aifeng.model.ProductSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;

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
    public void saveSlide(String[] imgPaths, Product product) {
        for (String imgPath : imgPaths) {
            ProductSlide productSlide = new ProductSlide();
            productSlide.setImg(imgPath);
            productSlide.setName(imgPath.substring(imgPath.lastIndexOf(File.separator) + 1));
            productSlide.setCreateTime(new Date());
            productSlide.setProduct(product);
            productSlideRepository.save(productSlide);
        }
    }

    @Transactional
    public List<ProductSlide> getByProduct(Product product) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return productSlideRepository.findAllByProduct(product, sort);
    }

    @Transactional
    public void delSlide(ProductSlide productSlide) {
        productSlideRepository.delete(productSlide);
    }

    @Transactional
    public void editSlide(String[] imgPaths, String[] imgIds, Product product) {
        int i = 0;
        for (String idStr : imgIds) {
            ProductSlide productSlide;
            if (idStr != null && !idStr.isEmpty()) {
                long id = Long.parseLong(idStr);
                productSlide = productSlideRepository.findOne(id);
                productSlide.setUpdateTime(new Date());
            } else {
                productSlide = new ProductSlide();
                productSlide.setCreateTime(new Date());
            }
            String imgPath = imgPaths[i];
            if (imgPath != null) {
                productSlide.setImg(imgPath);
                productSlide.setName(imgPath.substring(imgPath.lastIndexOf(File.separator) + 1));
            }
            productSlide.setProduct(product);
            productSlideRepository.save(productSlide);
            i++;
        }
    }
}
