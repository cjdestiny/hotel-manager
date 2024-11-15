package com.example.hotelmanager.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@TableName("ev_users")
public class User {
    @TableId
    private int id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String userPic;
    private int roleId;
}
