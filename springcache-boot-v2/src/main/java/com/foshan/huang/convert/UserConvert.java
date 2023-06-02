package com.foshan.huang.convert;

import com.foshan.huang.bo.UserBO;
import com.foshan.huang.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserConvert {

    UserConvert instance = Mappers.getMapper(UserConvert.class);

    UserVO convert2VO(UserBO userBO);

}
