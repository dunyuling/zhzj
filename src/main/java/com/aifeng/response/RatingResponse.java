package com.aifeng.response;

import com.aifeng.model.Ad;
import com.aifeng.model.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Data
@NoArgsConstructor
public class RatingResponse /* extends BaseResponse<Ad> */{

    public int code;
    public String msg;
    private List<Rating> data;

    public void config(int code, String msg, List<Rating> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
