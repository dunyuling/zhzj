package com.aifeng.response;

import com.aifeng.model.Ad;
import com.aifeng.model.ConferenceHall;
import com.aifeng.model.Information;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Data
@NoArgsConstructor
public class ConferenceHallResponse/* extends BaseResponse<ConferenceHall>*/ {

    public int code;
    public String msg;
    private List<ConferenceHall> data;

    public void config(int code, String msg, List<ConferenceHall> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
