package com.aifeng.response;

import com.aifeng.model.Ad;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Data
@NoArgsConstructor
public class AdResponse /* extends BaseResponse<Ad> */{

    public int code;
    public String msg;
    private List<Ad> data;

    public void config(int code, String msg, List<Ad> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
