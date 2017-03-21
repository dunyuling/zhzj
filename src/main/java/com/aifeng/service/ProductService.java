package com.aifeng.service;

import com.aifeng.constant.ImgPath;
import com.aifeng.dao.ProductIntroRepository;
import com.aifeng.dao.ProductRepository;
import com.aifeng.dao.ProductSlideRepository;
import com.aifeng.model.Ad;
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
import java.util.Set;

import static com.aifeng.constant.ImgPath.productPath;

/**
 * Created by pro on 17-3-14.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductSlideRepository productSlideRepository, ProductIntroRepository productIntroRepository, ProductSlideService productSlideService) {
        this.productRepository = productRepository;
        this.productSlideRepository = productSlideRepository;
        this.productIntroRepository = productIntroRepository;
        this.productSlideService = productSlideService;
    }

    private final ProductSlideRepository productSlideRepository;

    private final
    ProductIntroRepository productIntroRepository;

    private final
    ProductSlideService productSlideService;


    @Transactional
    public void saveProduct(String name, String imgPath, float price, String seller, String telephone,
                            String[] imgSlidePaths) {
        Product product = new Product();
        product.setName(name);
        product.setImg(imgPath);
        product.setPrice(price);
        product.setSeller(seller);
        product.setTelephone(telephone);
        product.setCreateTime(new Date());
        product = productRepository.save(product);

        productSlideService.saveSlide(imgSlidePaths, product);
    }

    @Transactional
    public List<Product> findAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return productRepository.findAll(sort);
    }

    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editProduct() {

    }

    @Transactional
    public void delProduct(String imgRealPathDir, long id) {
        Product product = productRepository.findOne(id);
        String subPath = productPath.substring(0, imgRealPathDir.indexOf(ImgPath.adPath));
        new File(subPath + product.getImg()).delete();

        Set<ProductSlide> productSlides = product.getProductSlideSet();
        for (ProductSlide productSlide : productSlides) {
            new File(subPath + productSlide.getImg()).delete();
            productSlideRepository.delete(productSlide);

        }

        Set<ProductIntro> productIntros = product.getProductIntroSet();
        for (ProductIntro productIntro : productIntros) {
            productIntroRepository.delete(productIntro);
        }
    }
}