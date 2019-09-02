package com.youyicn.controller.cycle;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.entity.LoginSession;
import com.youyicn.entity.RspInfo;
import com.youyicn.entity.User;
import com.youyicn.entity.cycle.CycleScoreModel;
import com.youyicn.service.cycle.IOverallScoreService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Title overallScoreController
 * @Description 360评分模板管理
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author wangtan
 * @Version 1.0.0
 * @Create 2018/11/26 19:42
 */
@Controller
public class OverallScoreModelController {

    @Autowired
    private IOverallScoreService overallScoreServiceImpl;

    //进入360互评首页，返回评分模板列表
    @RequestMapping("/overallScoreModel/index.htm")
    public String scoreIndex(HttpServletResponse response, HttpServletRequest request, ModelMap model, String type, String li, String div, Integer pageIndex) {

        //封装页面信息参数
        String userName = request.getParameter("userName");
        model.put("userName", userName);
        model.put("type", type);
        model.put("li", li);
        model.put("div", div);

        //获取当前页
        if (pageIndex == null) {
            pageIndex = 1;
        }
        PageHelper.startPage(pageIndex, 15);
        //获取模板列表
        List<CycleScoreModel> allScoreModelList = overallScoreServiceImpl.findAllScoreModelList();
        PageInfo<CycleScoreModel> page = new PageInfo<>(allScoreModelList);
        model.put("page", page);
        return "/overallScore/index";
    }


    /**
     * @param [response, request, model, type, li, div, scoreModel]
     * @return java.lang.String
     * @title
     * @description 添加评分模板
     * @author wangtan
     * @createDate 2018/12/3 11:47
     */
    @RequestMapping(value = "/overallScore/addScoreModel.htm", method = RequestMethod.POST)
    @ResponseBody
    public RspInfo addScoreModel(HttpServletResponse response, HttpServletRequest request, ModelMap model, CycleScoreModel scoreModel) {
        RspInfo rspInfo = new RspInfo();
        String[] itemNames = scoreModel.getItemNames();
        String[] itemScores = scoreModel.getItemScores();
        //判断关键字段是否为Null
        if (StringUtils.isBlank(scoreModel.getModelName()) || itemScores.length == 0 || itemNames.length == 0 || itemScores.length != itemNames.length) {
            rspInfo.setRspMessage("模板名称及评分项不能为空！");
            return rspInfo;
        } else {
            LoginSession<User> loginSession = (LoginSession<User>) request.getSession().getAttribute("loginSession");
            scoreModel.setUserId(loginSession.getUserID());
            String scoreItem = potScoreItem(itemNames, itemScores);
            scoreModel.setScoreItem(scoreItem);
            overallScoreServiceImpl.addScoreModel(scoreModel);
            rspInfo.setRspMessage("添加评分模板成功！");
            return rspInfo;
        }
    }

    /**
     * @param [response, request, model, type, li, div, scoreModel]
     * @return java.lang.String
     * @title
     * @description 通过模板Id查看模板详情
     * @author wangtan
     * @createDate 2018/12/4 11:13
     */
    @RequestMapping(value = "/overallScore/findScoreModelById.htm", method = RequestMethod.POST)
    @ResponseBody
    public CycleScoreModel findScoreModelById(HttpServletResponse response, HttpServletRequest request, ModelMap model, CycleScoreModel scoreModel) {
        Integer modelId = scoreModel.getModelId();
        if (modelId == null) {
            scoreModel.setRspMessage("请指定所要查看的模板！");
            return scoreModel;
        }
        CycleScoreModel scoreModelBean = overallScoreServiceImpl.findScoreModelById(modelId);
        if (scoreModelBean == null || scoreModelBean.getModelId() == null) {
            scoreModel.setRspMessage("您所查看的模板不存在！");
            return scoreModel;
        }
        BeanUtils.copyProperties(scoreModelBean, scoreModel);
        scoreModel = splitScoreModel(scoreModel);
        return scoreModel;
    }

    /**
     * @param [response, request, model,  scoreModel]
     * @return com.youyicn.entity.RspInfo
     * @title
     * @description 修改评分模板
     * @author wangtan
     * @createDate 2018/12/4 16:27
     */
    @RequestMapping(value = "/overallScore/editScoreModel.htm", method = RequestMethod.POST)
    @ResponseBody
    public RspInfo editScoreModel(HttpServletResponse response, HttpServletRequest request, ModelMap model, CycleScoreModel scoreModel) {
        RspInfo rspInfo = new RspInfo();
        String[] itemNames = scoreModel.getItemNames();
        String[] itemScores = scoreModel.getItemScores();
        //判断关键字段是否为Null
        if (StringUtils.isBlank(scoreModel.getModelName()) || itemScores.length == 0 || itemNames.length == 0 || itemScores.length != itemNames.length) {
            rspInfo.setRspMessage("模板名称及评分项不能为空！");
            return rspInfo;
        } else {
            LoginSession<User> loginSession = (LoginSession<User>) request.getSession().getAttribute("loginSession");
            scoreModel.setUserId(loginSession.getUserID());
            String scoreItem = potScoreItem(itemNames, itemScores);
            scoreModel.setScoreItem(scoreItem);
            overallScoreServiceImpl.editScoreModel(scoreModel);
            rspInfo.setRspMessage("修改评分模板成功！");
            return rspInfo;
        }
    }

    /**
     * @param [response, request, model,scoreModel]
     * @return com.youyicn.entity.RspInfo
     * @title
     * @description 删除评分模板
     * @author wangtan
     * @createDate 2018/12/4 16:27
     */
    @RequestMapping(value = "/overallScore/deleteScoreModel.htm", method = RequestMethod.POST)
    @ResponseBody
    public RspInfo deleteScoreModel(HttpServletResponse response, HttpServletRequest request, ModelMap model, CycleScoreModel scoreModel) {
        RspInfo rspInfo = new RspInfo();
        if (scoreModel.getModelId() == null) {
            rspInfo.setRspMessage("请指定所要删除的模板！");
            return rspInfo;
        }
        overallScoreServiceImpl.deleteScoreModelById(scoreModel.getModelId());
        rspInfo.setRspMessage("删除模板成功！");
        return rspInfo;
    }

    /**
     * @param [itemNames, itemScores]
     * @return java.lang.String
     * @title
     * @description 封装评分项
     * @author wangtan
     * @createDate 2018/12/3 16:44
     */
    private String potScoreItem(String[] itemNames, String[] itemScores) {
        StringBuilder scoreItemBuilder = new StringBuilder("{");
        if (itemScores.length != itemNames.length) {
            System.out.println("参数个数对应不一致！");
        }
        for (int i = 0; i < itemNames.length; i++) {
            scoreItemBuilder.append("\"").append(itemNames[i]).append("\":\"").append(itemScores[i]).append("\"");
            if (i < itemNames.length - 1) {
                scoreItemBuilder.append(",");
            }
        }
        scoreItemBuilder.append("}");
        return scoreItemBuilder.toString();
    }

    /**
     * @param [scoreItem]
     * @return com.youyicn.entity.cycle.CycleScoreModel
     * @title
     * @description 拆分评分项
     * @author wangtan
     * @createDate 2018/12/5 8:40
     */
    private CycleScoreModel splitScoreModel(CycleScoreModel scoremodel) {
        String scoreItem = scoremodel.getScoreItem();
        Map<String,String> parse = (Map) JSONObject.parse(scoreItem);
        String[] names = (String[]) parse.keySet().toArray(new String[0]);
        int i = 0;
        String[] scores = new String[names.length];
        while(i < names.length){
            scores[i] = parse.get(names[i]);
            ++i;
        }
        scoremodel.setItemNames(names);
        scoremodel.setItemScores(scores);
        return scoremodel;
    }
}
