package demo.dd.learningofes.Enum;

import lombok.Getter;

/**
 * @author DiDi
 * @description 请求状态枚举
 * @date 2022/12/14 14:53
 */

@Getter
public enum StateEnum {

    /**
     * 成功
     */
    SUCCESS("20000", "success"),

    /**
     * 失败
     */
    FAILURE("50000", "failure");

    /**
     * 状态码
     */
    private String code;

    /**
     * 请求结果信息
     */
    private String message;

    StateEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
