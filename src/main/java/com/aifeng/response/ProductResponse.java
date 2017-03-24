package com.aifeng.response;

import com.aifeng.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pro on 17-3-24.
 */
//@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProductResponse /*extends BaseResponse<Product>*/ {

    public int code;
    public String msg;
    public List<Product> data;

    public void config(int code, String msg, List<Product> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}
