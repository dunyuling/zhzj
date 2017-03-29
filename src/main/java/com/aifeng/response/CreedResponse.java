package com.aifeng.response;

import com.aifeng.model.Ad;
import com.aifeng.model.Creed;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Data
@NoArgsConstructor
public class CreedResponse /* extends BaseResponse<Ad> */{

    public int code;
    public String msg;
    private List<Creed> data;

    public void config(int code, String msg, List<Creed> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
