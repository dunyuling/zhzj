package com.aifeng.response;

import com.aifeng.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pro on 17-3-24.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProductDetailResponse extends BaseResponse<Product> {

}
