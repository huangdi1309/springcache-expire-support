package com.foshan.huang.convert;

import com.foshan.huang.bo.UserBO;
import com.foshan.huang.vo.UserVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-02T09:58:48+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public UserVO convert2VO(UserBO userBO) {
        if ( userBO == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setName( userBO.getName() );
        userVO.setSex( userBO.getSex() );
        userVO.setAvatar( userBO.getAvatar() );
        userVO.setKey( userBO.getKey() );

        return userVO;
    }
}
