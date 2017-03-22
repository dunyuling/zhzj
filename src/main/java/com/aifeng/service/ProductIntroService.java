package com.aifeng.service;

import com.aifeng.dao.ProductIntroRepository;
import com.aifeng.dao.ProductSlideRepository;
import com.aifeng.model.Product;
import com.aifeng.model.ProductIntro;
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
public class ProductIntroService {

    private final ProductIntroRepository productIntroRepository;

    @Autowired
    public ProductIntroService(ProductIntroRepository productIntroRepository) {
        this.productIntroRepository = productIntroRepository;
    }

    @Transactional
    public void saveIntro(String[] imgPaths, Product product) {

    }

    @Transactional
    public void delIntro(ProductIntro productIntro) {
        productIntroRepository.delete(productIntro);
    }
}
