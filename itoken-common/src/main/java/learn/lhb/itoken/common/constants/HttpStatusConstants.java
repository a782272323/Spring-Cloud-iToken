package learn.lhb.itoken.common.constants;


/**
 * HttpStatus常量类
 */
//TODO  百度java枚举
public enum  HttpStatusConstants {
    BAD_GATEWAY(502,"从上游服务器接受到无效的响应");//枚举类型，多个用,分开


    private int status;//状态码
    private String content;//内容

    private HttpStatusConstants(int status,String content)  { //构造器
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
