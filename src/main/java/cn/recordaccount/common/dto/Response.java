package cn.recordaccount.common.dto;

import cn.recordaccount.common.constant.ResStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rollin
 * @date 2021年02月22日13:48:13
 */
@Data
public class Response<T extends Serializable> implements Serializable {
    /**
     * 返回码
     * 0000 成功
     */
    @NotNull
    private String code;
    /**
     * 返回信息
     */
    @NotNull
    private String msg;
    /**
     * 返回数据
     */
    @NotNull
    private T body;

    /**
     * 默认成功
     * @return
     */
    public static Response success(){
        String code = ResStatusEnum.SUCCESS.getCode();
        String msg = ResStatusEnum.SUCCESS.getMsg();
        return Response.setRes(code, msg);
    }

    /**
     * 自定义返回信息，数据成功
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Serializable> Response<T> success(String msg, T t){
        String code = ResStatusEnum.SUCCESS.getCode();
        return Response.setRes(code, msg, t);
    }

    /**
     * 自定义返回数据成功
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Serializable> Response<T> success(T t){
        String code = ResStatusEnum.SUCCESS.getCode();
        String msg = ResStatusEnum.SUCCESS.getMsg();
        return Response.setRes(code, msg, t);
    }

    /**
     * 默认失败
     * @return
     */
    public static Response error(){
        String code = ResStatusEnum.SERVER_ERROR.getCode();
        String msg = ResStatusEnum.SERVER_ERROR.getMsg();
        return Response.setRes(code, msg);
    }

    /**
     * 自定义返回信息-失败
     * @param msg
     * @return
     */
    public static Response error(String msg){
        String code = ResStatusEnum.SERVER_ERROR.getCode();
        return Response.setRes(code, msg);
    }

    /**
     * 自定义返回数据-失败
     * @param resStatusEnum
     * @return
     */
    public static Response error(ResStatusEnum resStatusEnum){
        String code = resStatusEnum.getCode();
        String msg = resStatusEnum.getMsg();
        return Response.setRes(code, msg);
    }

    /**
     * 自定义返回信息，返回数据-失败
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Serializable> Response error(String msg, T t){
        String code = ResStatusEnum.SERVER_ERROR.getCode();
        return Response.setRes(code, msg, t);
    }

    /**
     * 自定义返回码，返回信息-失败
     * @return
     */
    public static Response error(String code, String msg){
        return Response.setRes(code, msg);
    }

    /**
     * 自定义返回码，返回信息-失败
     * @return
     */
    public static <T> Response error(String code, String msg, T ... param){
        List list = new ArrayList();
        list.addAll(Arrays.asList(param).subList(1, param.length - 1));
        return Response.setRes(code, msg);
    }

    /**
     * 自定义返回码，返回信息，返回数据-失败
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Response error(String code, String msg, T t){
        return Response.setRes(code, msg);
    }


    private static Response setRes(String code, String msg){
        Response res = new Response<>();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }

    private static <T extends Serializable> Response<T> setRes(String code, String msg, T t){
        Response res = new Response<>();
        res.setCode(code);
        res.setMsg(msg);
        res.setBody(t);
        return res;
    }
}
