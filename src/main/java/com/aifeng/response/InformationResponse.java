package com.aifeng.response;

import com.aifeng.model.Ad;
import com.aifeng.model.Information;
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

    private List<Information> data;


}
