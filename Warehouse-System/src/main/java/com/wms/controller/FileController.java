package com.wms.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.wms.entity.Goods;
import com.wms.entity.User;
import com.wms.service.GoodsService;
import com.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
@RestController
public class FileController{

@Autowired
private UserService userService;
@Autowired
private GoodsService goodsService;
/**
 *@Author:
 *@Date: 2024-10-17
 *@Describe:
*/
    @GetMapping("/export")
    public void exportfile(HttpServletResponse response) throws IOException {
        List<Goods> list = goodsService.list();
        ExcelWriter writer= ExcelUtil.getWriter(true);
//    writer.addHeaderAlias("name","用户名");
//    writer.addHeaderAlias("age","年龄");
//    writer.addHeaderAlias("number","电话号");
//    writer.addHeaderAlias("address","地址");
        writer.write(list,true);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("货物信息",  "UTF-8");
        response.setHeader("Content-Disposition",  "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }
}
