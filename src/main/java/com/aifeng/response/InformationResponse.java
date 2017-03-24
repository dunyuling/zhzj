package com.aifeng.response;

import com.aifeng.model.Ad;
import com.aifeng.model.Information;
import com.aifeng.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class InformationResponse extends BaseResponse<Information> {

    public int code;
    public String msg;
    private List<Information> data;

    public void config(int code, String msg, List<Information> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
