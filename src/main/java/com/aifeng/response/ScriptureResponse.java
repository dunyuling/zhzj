package com.aifeng.response;

import com.aifeng.model.Creed;
import com.aifeng.model.Scripture;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Data
@NoArgsConstructor
public class ScriptureResponse /* extends BaseResponse<Ad> */{

    public int code;
    public String msg;
    private List<Scripture> data;

    public void config(int code, String msg, List<Scripture> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
