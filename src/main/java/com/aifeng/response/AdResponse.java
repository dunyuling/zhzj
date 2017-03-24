package com.aifeng.response;

import com.aifeng.model.Ad;
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
public class AdResponse extends BaseResponse<Ad> {

    private List<Ad> data;

}
