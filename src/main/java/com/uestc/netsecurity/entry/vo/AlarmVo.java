package com.uestc.netsecurity.entry.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlarmVo implements Serializable {

    //对应id，可不填
    @TableId(value = "id")
    private int id;

    private String deviceName;

    private String srcAddress;

    private String destAddress;

    private String threatSeverity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime shacoTime;

    //所属部门
    private String destDepartment;

    //所属网络区域
    private String destZoom;

    //服务器
    private String serverName;

    //外网
    private String outGateWay;

    //相关负责人
    private String creator;


}
