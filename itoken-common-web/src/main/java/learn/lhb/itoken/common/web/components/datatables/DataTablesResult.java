package learn.lhb.itoken.common.web.components.datatables;

import learn.lhb.itoken.common.dto.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Bootstrap 结果集
 *
 * @author 梁鸿斌
 * @date 2020/2/7.
 * @time 10:04 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataTablesResult extends BaseResult implements Serializable {

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private String error;
}
