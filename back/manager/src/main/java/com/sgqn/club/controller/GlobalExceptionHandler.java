package com.sgqn.club.controller;

import com.sgqn.club.base.bean.ResultBean;
import com.sgqn.club.base.exception.ClubException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wspstart
 * @description 全局异常处理
 * @create 2023-06-14 20:48
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 拦截 Controller 方法参数校验异常（{@link org.springframework.web.bind.MethodArgumentNotValidException}
     *
     * @param e 方法参数校验异常
     * @return 返回异常响应
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultBean<?> validationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        int errorCount = bindingResult.getErrorCount();
        // 返回首个错误（当有多个错误时，第一个错误是无法预知的，或许和字段顺序有关）
        ObjectError objectError = bindingResult.getAllErrors()
                .get(0);
        if (log.isDebugEnabled()) {
            log.debug("参数校验失败。共 {} 个错误，返回的第 0 个错误为：\n {} ", errorCount, objectError.toString());
        }
        return ResultBean.error(objectError.getDefaultMessage());
    }

    /**
     * 拦截 Controller 方法请求参数校验异常 ({@link com.sgqn.club.base.exception.ClubException})
     *
     * @param clubException 项目中自定义的异常
     * @return 返回异常响应
     */
    @ExceptionHandler(value = ClubException.class)
    public ResultBean<?> clubExceptionHandler(ClubException clubException) {
        // 打印错误信息
        clubException.printStackTrace();
        return ResultBean.error(clubException.getMessage());
    }

    /**
     * 拦截 Controller 方法报错的其他异常信息 （{@link java.lang.Exception}）
     *
     * @param e 项目中的其他异常信息
     * @return 返回异常响应
     */
    @ExceptionHandler(value = Exception.class)
    public ResultBean<?> otherExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultBean.error("服务器异常,请联系管理员！");
    }
}
