package com.uestc.netsecurity.entry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("attack_test") //对应表名
public class Attack implements Serializable {

    //对应id，可不填
    @TableId(value = "id")
    private int id;

    private int districtId;

    //对应字段名，如果属性名和字段名一致，可不填
    //@TableField("srcPort")
    private int type;

    private String status;

    private String description;

}
