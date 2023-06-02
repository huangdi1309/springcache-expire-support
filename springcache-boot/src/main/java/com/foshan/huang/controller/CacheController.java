package com.foshan.huang.controller;


import com.foshan.huang.bo.UserBO;
import com.foshan.huang.convert.UserConvert;
import com.foshan.huang.service.CacheService;
import com.foshan.huang.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cache")
@Slf4j
public class CacheController {

    private final CacheService cacheService;

    @GetMapping(value = "/queryUserInfo")
    public UserVO queryUserInfo(@RequestParam("name") String name) {
        log.info("queryUserInfo reqParams:{}", name);
        UserBO userBo = cacheService.getUser(name);
        return UserConvert.instance.convert2VO(userBo);
    }

    @GetMapping(value = "/queryUserInfo2")
    public UserVO queryUserInfo2(@RequestParam("name") String name) {
        UserBO userBo = cacheService.getUserWithoutExpire(name);
        return UserConvert.instance.convert2VO(userBo);
    }

}
