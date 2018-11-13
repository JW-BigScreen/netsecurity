package com.uestc.netsecurity.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultObject<T> implements Serializable {



    private String msg ;

    private Integer code;

    private Boolean success;

    private T data;

    public ResultObject(String msg,Integer code){
        this.msg = msg;
        this.code = code;
    }

    public ResultObject(String msg,Integer code,Boolean success){
        this.msg = msg;
        this.code = code;
        this.success = success;
    }

}
