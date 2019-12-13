package com.example.demo.validate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yao
 * @date 2019/12/13
 */

@ToString
public class ResultVO<T> {

    @Getter
    @Setter
    private Integer code;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private T data;

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultVO success(String message) {
        return new ResultVO(ResultConst.RESULT_SUCCESS_CODE, message);
    }

    public static <T> ResultVO error(String message) {
        return new ResultVO(ResultConst.RESULT_FAIL_CODE, message);
    }

    public static <T> ResultVO<T> error(String message, T data) {
        return new ResultVO<>(ResultConst.RESULT_FAIL_CODE, message, data);
    }


}
