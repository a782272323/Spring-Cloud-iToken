package learn.lhb.itoken.common.service;

import com.github.pagehelper.PageInfo;
import learn.lhb.itoken.common.domain.BaseDomain;

/**
 * 通用的增删改查 (CRUD)
 * @author 梁鸿斌
 * @date 2020/2/6.
 * @time 2:26 下午
 */
public interface BaseService<T extends BaseDomain> {

    /**
     * 增加
     * @param t
     * @return
     */
    int insert(T t,String createBy);

    /**
     * 删除
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 修改
     * @param t
     * @return
     */
    int update(T t,String updateBy);

    /**
     * 查询数量
     * @param t
     * @return
     */
    int count(T t);

    /**
     * 查找单个
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 查找全部（分页）
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<T> page(int pageNum,int pageSize,T t);
}
