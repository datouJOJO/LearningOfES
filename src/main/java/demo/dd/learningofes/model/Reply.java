package demo.dd.learningofes.model;

import demo.dd.learningofes.Enum.StateEnum;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author DiDi
 * @description 返回结果
 * @date 2022/12/14 14:56
 */

@Data
public class Reply {

    /**
     * 状态码
     */
    private String code;

    /**
     * 请求信息
     */
    private String message;

    /**
     * 请求结果
     */
    private Object data;

    public static Reply success(Object goodsList) {
        Reply reply = new Reply();
        reply.setCode(StateEnum.SUCCESS.getCode());
        reply.setMessage(StateEnum.SUCCESS.getMessage());
        reply.setData(goodsList);
        return reply;
    }

    public static Reply fail(String message) {
        Reply reply = new Reply();
        reply.setCode(StateEnum.FAILURE.getCode());
        reply.setMessage(message);
        return reply;
    }

    public static Reply fail() {
        Reply reply = new Reply();
        reply.setCode(StateEnum.FAILURE.getCode());
        reply.setMessage(StateEnum.FAILURE.getMessage());
        return reply;
    }
}
