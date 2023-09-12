package com.atguigu.ssyx.common.exception;

import com.atguigu.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //AOP
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody //返回json数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null);
    }

    @ExceptionHandler(SsyxException.class)
    @ResponseBody //返回json数据
    public Result error(SsyxException e){
        e.printStackTrace();
        return Result.build(null,e.getCode(),e.getMessage());
    }
}