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
@TableName("alarm_base_data") //对应表名
public class Alarm implements Serializable {

    //对应id，可不填
    @TableId(value = "id")
    private int id;

    //对应字段名，如果属性名和字段名一致，可不填
    //@TableField("srcPort")
    private Integer srcPort;

    private Integer deviceId;

    private String deviceName;

    private String uuid;

    private String responseCode;

    private String toAlarm;

    private String destAddress;

    private Long occurrenceTime;

    private String srcGeoLongitude;

    private String srcGeoLatitude;

    private String deviceCat;

    private String attackStrategy;

    private Integer eventId;

    private String destGeoLongitude;

    private String modelType;

    private String srcAddress;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime shacoTime;

    private String srcGeoCountry;

    private String alarmStatus;

    private String transProtocol;

    private String catObject;

    private String destGeoAddress;

    private String catTechnique;

    private String name;

    private String destGeoRegion;

    private String catOutcome;

    private String dataSource;

    private String killChain;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime collectorReceiptTime;

    private String appProtocol;

    private String destMacAddress;

    private String eventIds;

    private String attackIntent;

    private String deviceAddress;

    private Integer destPort;

    private String chineseModelName;

    private String destGeoCountry;

    private String attackMethod;

    private String deviceProductType;

    private Integer customerId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    private String srcGeoAddress;

    private String destGeoCity;

    private String engineInfo;

    private String severity;

    private Integer securityEyeLogType;

    private Integer responseTime;

    private String threatSeverity;

    private String protocolType;

    private String queryType;

    private String destGeoLatitude;

    private String modelName;

    private String catBehavior;

    private String catSignificance;

    private String textName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    private String srcMacAddress;

    private String srcGeoRegion;

    private String descript;

    private String suggest;


}
