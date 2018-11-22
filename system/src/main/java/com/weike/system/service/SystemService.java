package com.weike.system.service;


import com.weike.system.entity.WUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.Result;

@RestController
@RequestMapping("/sys")
public interface SystemService {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Result login(String userName,String password);

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    Result register( WUser wUser);

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    Result logout( WUser wUser);

    @RequestMapping(value = "/checkUserInfo", method = RequestMethod.POST)
    Result checkUserInfo( String param, int type);
}
