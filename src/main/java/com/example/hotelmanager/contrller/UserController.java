package com.example.hotelmanager.contrller;

import com.example.hotelmanager.pojo.Result;
import com.example.hotelmanager.pojo.User;
import com.example.hotelmanager.service.UserService;
import com.example.hotelmanager.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    //注册功能
    @PostMapping("/reguser")
    public Result<String> register(@RequestParam String username,@RequestParam String password){
        log.info("正在注册验证...",username);
        //根据username查询用户
        User user = userService.findByUsername(username);
        //判断用户是否已存在
        if (user!=null) {
            return Result.error("用户已存在");
        }
//        mp帮我插入数据库
        User newUser = new User().setUsername(username).setPassword(password);
        userService.save(newUser);
        System.out.println(username + "注册成功");
        return Result.success("注册成功！");
    }
    //登录功能
    @PostMapping("/login")
    public Result<String> login(@RequestParam String username,@RequestParam String password){
        log.info("验证登录{}",username);
        //判断用户是否存在
        User userLogin = userService.findByUsername(username);

        if( userLogin == null){
           return Result.error("用户名有误");
        }
        //存在，验证密码是否正确
        if (!userLogin.getPassword().equals(password)) {
           return  Result.error("密码错误");
        }
        //密码正确，生成token
        Map<String,Object> claims = new HashMap();
        claims.put("id",userLogin.getId());
        claims.put("username",userLogin.getUsername());
        //实现token里包含id，username信息
        String token = JwtUtil.genToken(claims);

        //token储存在redis中
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        //token的值为key储存到redis中
        operations.set(token,token,12, TimeUnit.HOURS);

        return Result.success(token);
    }
}
