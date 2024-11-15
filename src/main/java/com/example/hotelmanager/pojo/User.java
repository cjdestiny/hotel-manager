package com.example.hotelmanager.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.w3c.dom.Text;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@TableName("ev_users")
public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private Text userPic;
    private int roleId;
}