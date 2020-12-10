package com.factory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author LiuMingYang
 * @since 2020/12/10 16:20
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResultApi {
    private Integer code;
    private String message;
    private Object data;

    public static ResultApi ok(){
        return new ResultApi(200,"",null);
    }
    public static ResultApi error(){
        return new ResultApi(500,"服务器异常，请联系管理员",null);
    }
}
