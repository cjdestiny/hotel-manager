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
    int id;
    String username;
    String password;
    String name;
    String phone;
    Text userPic;
    int roleId;
}
