package com.uestc.netsecurity.entry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user") //对应表名
public class User {

    //对应id，可不填
    @TableId(value = "id")
    private Integer id;

    private Integer age;

    private String username;

    private String password;


}
