package com.aifeng.service;

import com.aifeng.constant.ImgPath;
import com.aifeng.dao.ProductIntroRepository;
import com.aifeng.dao.ProductRepository;
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

import static com.aifeng.constant.ImgPath.productPath;

/**
 * Created by pro on 17-3-14.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductSlideRepository productSlideRepository, ProductIntroRepository productIntroRepository, ProductSlideService productSlideService, ProductIntroService productIntroService) {
        this.productRepository = productRepository;
        this.productSlideService = productSlideService;
        this.productIntroService = productIntroService;
    }

    private final
    ProductSlideService productSlideService;
    private final ProductIntroService productIntroService;

    @Transactional
    public void saveProduct(String name, String imgPath, float price, String seller, String telephone,
                            String[] imgSlidePaths, String intro) {
        Product product = new Product();
        product.setName(name);
        product.setImg(imgPath);
        product.setPrice(price);
        product.setSeller(seller);
        product.setTelephone(telephone);
        product.setCreateTime(new Date());
        product = productRepository.save(product);

        productSlideService.saveSlide(imgSlidePaths, product);
        productIntroService.saveIntro(intro, product);
    }

    @Transactional
    public List<Product> findAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return productRepository.findAll(sort);
    }

    @Transactional
    public Product findProduct(long id) {
        Product product = productRepository.findOne(id);
//        Set<ProductSlide> slideSet = product.getProductSlideSet();
//        Hibernate.initialize();
        List<ProductSlide> productSlides = productSlideService.getByProduct(product);
        product.setProductSlideList(productSlides);

        return product;
    }

    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editProduct(long id, String name, String imgPath, float price, String seller, String telephone,
                            String[] imgSlidePaths, String[] productSlideIds, long introId, String intro) {
        Product product = productRepository.findOne(id);
        product.setId(id);
        product.setName(name);
        if (imgPath != null) {
            product.setImg(imgPath);
        }
        product.setPrice(price);
        product.setSeller(seller);
        product.setTelephone(telephone);
        product.setUpdateTime(new Date());
        product = productRepository.save(product);

        productSlideService.editSlide(imgSlidePaths, productSlideIds, product);
        productIntroService.editIntro(introId, intro);
    }

    @Transactional
    public void delProduct(String imgRealPathDir, long id) {
        Product product = productRepository.findOne(id);
        String subPath = productPath.substring(0, imgRealPathDir.indexOf(ImgPath.adPath));
        new File(subPath + product.getImg()).delete();

        List<ProductSlide> productSlides = product.getProductSlideList();
        for (ProductSlide productSlide : productSlides) {
            new File(subPath + productSlide.getImg()).delete();
            productSlideService.delSlide(productSlide);

        }

        ProductIntro productIntro = product.getProductIntro();
        productIntroService.delIntro(productIntro);
    }
}