package com.imooc.domain;

import lombok.Data;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 1:48
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;

    private T data;
}
