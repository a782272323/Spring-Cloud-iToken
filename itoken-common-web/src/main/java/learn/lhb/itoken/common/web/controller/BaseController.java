package learn.lhb.itoken.common.web.controller;

import learn.lhb.itoken.common.domain.BaseDomain;
import learn.lhb.itoken.common.utils.MapperUtils;
import learn.lhb.itoken.common.web.components.datatables.DataTablesResult;
import learn.lhb.itoken.common.web.service.BaseClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 梁鸿斌
 * @date 2020/2/7.
 * @time 9:49 下午
 */
public abstract class BaseController<T extends BaseDomain,S extends BaseClientService> {

    //TODO 先把必要的笔记，工具类整合了，然后过一遍myshop，然后整合出自己理解的封装到springboot里面去

    /**
     * 注入业务逻辑层
     */

    @Autowired
    protected S service;

    @ResponseBody
    @GetMapping(value = "page")
    public DataTablesResult page(HttpServletRequest request) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 0 : Integer.parseInt(strLength);

        String json = service.page(start, length, null);
        DataTablesResult dataTablesResult = null;
        try{
            dataTablesResult = MapperUtils.json2pojo(json,DataTablesResult.class);
            dataTablesResult.setDraw(draw);
            dataTablesResult.setRecordsTotal(dataTablesResult.getCursor().getTotal());
            dataTablesResult.setRecordsFiltered(dataTablesResult.getCursor().getTotal());
        } catch (Exception e)   {
            e.printStackTrace();
        }

        return dataTablesResult;
    }


}
