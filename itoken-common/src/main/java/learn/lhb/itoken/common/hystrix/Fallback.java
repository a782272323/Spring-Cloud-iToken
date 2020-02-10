package learn.lhb.itoken.common.hystrix;

import com.google.common.collect.Lists;
import learn.lhb.itoken.common.constants.HttpStatusConstants;
import learn.lhb.itoken.common.dto.BaseResult;
import learn.lhb.itoken.common.utils.MapperUtils;

/**
 * 通用的熔断器方法
 *
 * @author 梁鸿斌
 * @date 2020/2/4.
 * @time 12:53 上午
 */
public class Fallback {

    /**
     * HTTP状态码 502
     * 若服务崩溃，返回的错误信息
     * @return
     */
    public static String badGateway()    {

        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(
                new BaseResult.Error(String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()),
                HttpStatusConstants.BAD_GATEWAY.getContent())
        ));

        try{
            return MapperUtils.obj2json(baseResult);
        }   catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
