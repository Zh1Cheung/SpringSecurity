package com.imooc.utils;

import com.imooc.domain.Result;

/**
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/15 1:31
 */
public class ResultUtil {
    public static Result success(Object object) {
        //返回成功，传入返回体具体出參
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }


    //提供给部分不需要出參的接口
    public static Result success() {
        return success(null);
    }

    //自定义错误信息
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
