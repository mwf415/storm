package com.youyicn.controller.cycle;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.entity.chart.EchartData;
import com.youyicn.entity.chart.Series;
import com.youyicn.entity.chart.TotalNum;
import com.youyicn.entity.cycle.Actives;
import com.youyicn.entity.cycle.Base;
import com.youyicn.entity.cycle.Room;
import com.youyicn.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

@Controller
public class ChartController {


    @RequestMapping("/chartController/index.htm")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model, String li, String div, String type) throws ParseException {

        return "/chart/index";
    }


    @RequestMapping("/chartController/getData.htm")
    @ResponseBody
    public EchartData getData(HttpServletRequest request, HttpServletResponse response, ModelMap model, String li, String div, String type) throws ParseException {

        /**
         * 1、时间赋值：根据传过来的值查询  如果为空，则查询当月的数据
         */


        /**
         * 2、封装查询条件
         */

        System.out.println("柱状图");
        List<String> category = new ArrayList<String>();
        category.add("眼科专业基地教学任务");
        category.add("耳鼻喉专业基地教学任务");
        category.add("消化内科专业基地");
        
        category.add("急诊基地");
        category.add("内科专业基地");
        category.add("全科专业基地");
        
        category.add("全科专业基地");
        category.add("全科专业基地");
        category.add("全科专业基地");
        
        category.add("全科专业基地");
        category.add("全科专业基地");
        category.add("全科专业基地");

        category.add("全科专业基地");
        category.add("全科专业基地");
        category.add("全科专业基地");
        List<Long> serisData=new ArrayList<Long>();
        serisData.add(1000L);
        serisData.add(2000L);
        serisData.add(3000L);

        serisData.add(1000L);
        serisData.add(1000L);
        serisData.add(1000L);

        serisData.add(1000L);
        serisData.add(1000L);
        serisData.add(2000L);

        serisData.add(1000L);
        serisData.add(1000L);
        serisData.add(2000L);
        
        serisData.add(1000L);
        serisData.add(1000L);
        serisData.add(2000L);

        
        List<String> legend = new ArrayList<String>(Arrays.asList(new String[] { "总数比较" }));// 数据分组
        List<Series> series = new ArrayList<Series>();// 纵坐标
        series.add(new Series("总数比较", "bar", serisData));
        EchartData data = new EchartData(legend, category, series);
        return data;
    }



}
