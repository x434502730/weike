package com.weike.system.service.impl;

import com.weike.system.dao.WUserMapper;

import com.weike.system.entity.WUser;
import com.weike.system.entity.WUserExample;
import com.weike.system.entity.WUserExample.*;
import com.weike.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import util.Result;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static util.Result.create;

@Service("systemService")
public class SystemServiceImpl implements SystemService{

    @Autowired
    private WUserMapper wUserMapper;

    @Override
    public Result login(String userName, String password) {
        // 判断用户名和密码是否正确
        WUserExample example = new WUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(userName);
        List<WUser> list = wUserMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return new Result("400", "用户名或密码错误");
        }
        // 校验密码，密码要进行md5加密后再校验
        WUser user = list.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return new Result("400", "用户名或密码错误");
        }
        // 生成一个token
        String token = UUID.randomUUID().toString();
        // 把用户信息保存到Redis数据库里面去
        // key就是token，value就是用户对象转换成json
        user.setPassword(null); // 为了安全，就不要把密码保存到Redis数据库里面去，因为这样太危险了，因此我们先把密码置空
//        jedisClient.set(SESSION_PRE + ":" + token, JsonUtils.objectToJson(user));
        // 设置key的过期时间
//        jedisClient.expire(SESSION_PRE + ":" + token, SESSION_EXPIRE);
        // 返回结果
//        return new Result(user);
        return create(user,"200","登陆成功");
    }

    @Override
    public Result register(WUser wUser) {
        // 校验数据的合法性
        if (StringUtils.isEmpty(wUser.getLoginName())
                || StringUtils.isEmpty(wUser.getPassword())) {
            return new Result(400, "用户名和密码不能为空");
        }
        // 校验用户名是否重复
        Result result = checkUserInfo(wUser.getLoginName(), 1);
        boolean flag = (boolean) result.getData();
        if (!flag) {
            return new Result(400, "用户名重复");
        }
        // 校验手机号是否重复
        if (wUser.getMobile() != null) { // 注意：空串也算有值
            result = checkUserInfo(wUser.getMobile(), 2);
            if (!(boolean) result.getData()) {
                return  new Result(400, "手机号重复");
            }
        }
        // 校验邮箱是否重复
        if (wUser.getEmail() != null) { // 注意：空串也算有值
            result = checkUserInfo(wUser.getEmail(), 3);
            if (!(boolean) result.getData()) {
                return  new Result(400, "邮箱重复");
            }
        }
        // 校验用户名是否重复
        if (wUser.getVsername() != null) { // 注意：空串也算有值
            result = checkUserInfo(wUser.getVsername(), 4);
            if (!(boolean) result.getData()) {
                return  new Result(400, "用户名重复");
            }
        }
        // 补全TbUser对象的属性
        wUser.setGenTime(new Date());
        wUser.setLastLoginTime(new Date());
        wUser.setLoginTime(new Date());
        wUser.setCount(1L);
        // 把密码进行MD5加密
        String md5Pass = DigestUtils.md5DigestAsHex(wUser.getPassword().getBytes());
        wUser.setPassword(md5Pass);
        // 插入到数据库
        wUserMapper.insert(wUser);
        // 返回结果
        return new Result(true);
    }

    @Override
    public Result logout(WUser wUser) {
        return null;
    }

    @Override
    public Result checkUserInfo(String param, int type) {
        WUserExample example = new WUserExample();
        Criteria criteria = example.createCriteria();
        // 判断要校验的数据类型，来设置不同的查询条件
        // 1、2、3,4分别代表loginname、phone、email,vsername
        if (type == 1) {
            criteria.andLoginNameEqualTo(param);
        } else if (type == 2) {
            criteria.andMobileEqualTo(param);
        } else if (type == 3) {
            criteria.andEmailEqualTo(param);
        }else if (type == 4) {
            criteria.andVsernameEqualTo(param);
        }
        // 执行查询
        List<WUser> list = wUserMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return new Result(true);
        }
        return new Result(false);
    }
}
