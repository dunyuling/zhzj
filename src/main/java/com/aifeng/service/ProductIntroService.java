package com.aifeng.service;

import com.aifeng.dao.ProductIntroRepository;
import com.aifeng.model.Product;
import com.aifeng.model.ProductIntro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by pro on 17-3-21.
 */
@Service
public class ProductIntroService {

    private final ProductIntroRepository productIntroRepository;

    @Autowired
    public ProductIntroService(ProductIntroRepository productIntroRepository) {
        this.productIntroRepository = productIntroRepository;
    }

    @Transactional
    public void saveIntro(String intro, Product product) {
        ProductIntro productIntro = new ProductIntro();
        productIntro.setHtmlFrag(intro);
        productIntro.setCreateTime(new Date());
        productIntro.setProduct(product);
        productIntroRepository.save(productIntro);
    }

    @Transactional
    public void editIntro(long id, String intro) {
        ProductIntro productIntro = productIntroRepository.findOne(id);
        productIntro.setHtmlFrag(intro);
        productIntro.setUpdateTime(new Date());
        productIntroRepository.save(productIntro);
    }

    //TODO how to delete image
    @Transactional
    public void delIntro(ProductIntro productIntro) {
        productIntroRepository.delete(productIntro);
    }
}
