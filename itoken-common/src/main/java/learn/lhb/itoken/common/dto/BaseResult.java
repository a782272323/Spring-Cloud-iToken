package learn.lhb.itoken.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 封装的一个json对象，用来做接口请求的返回模式,即接口请求的结构体
 * 具体请参考itoken-api
 */
@Data //TODO 有空把lombok的文档做个笔记，或者文档连接保存下来,然后整合到springboo里面的笔记去
public class BaseResult implements Serializable {
    public static final String RESULT_OK = "ok";
    public static final String RESULT_NOT_OK = "not_ok";
    public static final String SUCCESS = "成功操作";

    private String result; // 包括两种状态：“ok”和“not_ok”，不可为空。
    private Object data; // 返回具体的数据，可以为空。 默认为空Object,存在数据时不一定为Object,有可能会是Array
    private String success; //如果接口操作成功，如果有成功的提示信息，在“success”部分输出。
    private Cursor cursor; //分页信息
    private List<Error> errors; //错误信息

    /**
     * TODO 实战时记得返回来补充这是什么用法
     *
     * @param result
     * @param data
     * @param success
     * @param cursor
     * @param errors
     * @return
     */
    private static BaseResult createResult(String result,Object data,String success,Cursor cursor,List<Error> errors)   {

        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccess(success);
        baseResult.setCursor(cursor);
        baseResult.setErrors(errors);

        return baseResult;
    }

    /**
     * ok的返回码，无参数
     * @return
     */
    public static BaseResult ok()   {
        return createResult(RESULT_OK,null,SUCCESS,null,null);
    }

    public static BaseResult ok(String success) {
        return createResult(RESULT_OK,null,success,null,null);
    }

    /**
     * ok的返回码,有参数
     * @param data
     * @return
     */
    public static BaseResult ok(Object data)   {
        return createResult(RESULT_OK,null,SUCCESS,null,null);
    }

    /**
     * 返回分页数据，和查询的数据
     * @param data
     * @param cursor
     * @return
     */
    public static BaseResult ok(Object data,Cursor cursor)  {
        return createResult(RESULT_OK,data,SUCCESS,cursor,null);
    }

    /**
     * error的返回码
     * @param errors
     * @return
     */
    public static BaseResult notOk(List<BaseResult.Error> errors)    {
        return createResult(RESULT_NOT_OK,null,"",null,errors);
    }

    /**
     * 分页的封装,内部类来封装
     */
    @Data
    public static class Cursor  {
        private Integer total; //全部条数
        private Integer offset; //当前所在位置（页数）
        private Integer limit; //每页条数
    }

    /**
     * 当客户端向接口的请求失败，“errors”用来返回具体错误信息。“errors”包含了一个数组，每一个数组包括了一个具体的错误对象。
     * 每一个具体的错误对象都包含了“错误信息”（message）和“错误字段”（field）。如果错误信息没有针对一个具体的错误字段，
     * 错误字段的赋值为空。
     */
    @Data
    @AllArgsConstructor //lombok的构造函数注解,不写默认为空
    public static class Error   {
        private String field; //错误字段
        private String message; //错误信息
    }



}
