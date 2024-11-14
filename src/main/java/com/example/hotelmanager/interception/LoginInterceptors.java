package com.example.hotelmanager.interception;



import com.example.hotelmanager.utils.JwtUtil;
import com.example.hotelmanager.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptors implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("Authorization");
        //验证toke
        try {
            //从redis中获取token对比
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken=operations.get(token);
            if(redisToken==null){
                //token失效
                throw new RuntimeException();
            }

            Map<String,Object> claims= JwtUtil.parseToken(token);
            //把业务数据存放在ThreadLocal中
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            //响应状态码
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThrendLocal中的数据
        ThreadLocalUtil.remove();
    }
}
