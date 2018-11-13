package com.uestc.netsecurity.entry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("base_area_ip") //对应表名
public class AreaIp implements Serializable {

    //对应id，可不填
    @TableId(value = "id")
    private int id;

    //对应字段名，如果属性名和字段名一致，可不填
    //@TableField("srcPort")
    private Integer areaId;

    private String ip;

    private String startIp;

    private String endIp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private String createName;


}
