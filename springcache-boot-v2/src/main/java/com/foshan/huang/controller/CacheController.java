package com.foshan.huang.controller;


import com.foshan.huang.bo.UserBO;
import com.foshan.huang.convert.UserConvert;
import com.foshan.huang.service.CacheServiceV2;
import com.foshan.huang.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cache")
@Slf4j
public class CacheController {

    private final CacheServiceV2 cacheServiceV2;

    @GetMapping(value = "/queryUserInfo")
    public UserVO queryUserInfo(@RequestParam("name") String name) {
        log.info("queryUserInfo reqParams:{}", name);
        UserBO userBo = cacheServiceV2.getUser(name);
        return UserConvert.instance.convert2VO(userBo);
    }

    @PutMapping(value = "/updateUser")
    public UserVO update(@RequestParam("name") String name) {
        UserBO userBo = cacheServiceV2.updateUser(name);
        return UserConvert.instance.convert2VO(userBo);
    }

}
