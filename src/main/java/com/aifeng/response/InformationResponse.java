package com.aifeng.response;

import com.aifeng.model.Ad;
import com.aifeng.model.Information;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Data
@NoArgsConstructor
public class InformationResponse {

    private String msg;

    private int code;

    private List<Information> data;

    public void config(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
