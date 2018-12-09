package com.winterchen.controller;

import com.battcn.swagger.properties.ApiDataType;
import com.battcn.swagger.properties.ApiParamType;
import com.winterchen.dao.TeamDao;
import com.winterchen.dao.UserDao;
import com.winterchen.model.TeamDomain;
import com.winterchen.model.UserDomain;
import com.winterchen.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;



import com.winterchen.util.DateUtil;
import com.winterchen.util.wx.StringUtils;

import java.util.*;


/**
 * Created by Administrator on 2018/9/10.
 */


//controller  获取从页面传来的数据，然后处理或者不处理，直接交给server层去实现其逻辑。从service层获取数据，发送给前端

@RestController
@RequestMapping(value = "/user")
@Api(tags = "1.2", description = "用户管理", value = "用户管理")
public class UserController {


    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TeamDao teamDao;

    /**
     * 订单池中和正常的订单量和提现待处理的金额
     * @param
     * @return
     */
    @PostMapping("/getPoolAndNormalTotal")
    @ApiOperation(value = "订单池中和正常的订单量和提现待处理的金额")
    public Map<String, Object> getPoolAndNormalTotal() {
        Map map = userDao.getPoolAndNormalTotal();
        Double wd = userDao.getWithDrawTotal();
        map.put("withDrawTotal",wd);
        return map;
    }

    /**
     * 用户量统计/每日
     * @param
     * @return
     */
    @PostMapping("/getUserTotalByDay")
    @ApiOperation(value = "用户量统计/每日")
    public Map getUserTotalByDay(String startDate,String endDate) {

        Map map = new HashMap();
        if(StringUtils.isEmpty(startDate)){
            map.put("startDate", DateUtil.getAfterDate(DateUtil.getSystemTime(), -7));
        } else {
            map.put("startDate",startDate);
        }

        if(StringUtils.isEmpty(endDate)){
            map.put("endDate",DateUtil.getSystemTime());
        } else {
            map.put("endDate",endDate);
        }
        List<Map> result = userDao.getUserTotalByDay(map);
        String[] array = {"date","Order"};
        Map mapResult = new HashMap();
        mapResult.put("columns",array);
        mapResult.put("rows",result);
        return mapResult;
    }

    /**
     * 完成/失效订单量/每月
     * @param
     * @return
     */
    @PostMapping("/getCompleteOrderByMonth")
    @ApiOperation(value = "完成订单量/每月")
    public Map getCompleteOrderByMonth(String startDate,String endDate) throws Exception{
        Map para = new HashMap();
        if(StringUtils.isEmpty(startDate)){
            para.put("startDate", DateUtil.dateMinusMonth(new Date(),-7));
        } else {
            para.put("startDate",startDate);
        }

        if(StringUtils.isEmpty(endDate)){
            para.put("endDate",DateUtil.getSystemTime());
        } else {
            para.put("endDate",endDate);
        }
        List<Map> map = userDao.getCompleteOrderByMonth(para);
        String[] array = {"date","overOrder","loseOrder"};
        Map mapResult = new HashMap();
        mapResult.put("columns",array);
        mapResult.put("rows",map);
        return mapResult;
    }


    /**
     * 下单金额/每日
     * @param
     * @return
     */
    @PostMapping("/getOrderAmountByDay")
    @ApiOperation(value = "下单金额/每日")
    public Map getOrderAmountByDay() {
        List<Map> map = userDao.getOrderAmountByDay();
        String[] array = {"date","orderMoney"};
        Map mapResult = new HashMap();
        mapResult.put("columns",array);
        mapResult.put("rows",map);
        return mapResult;
    }

    /**
     * 团队总成交订单量
     * @param
     * @return
     */
    @PostMapping("/getTeamOrderTotal")
    @ApiOperation(value = "团队总成交订单量")
    public Map getTeamOrderTotal() {
        List<Map> map = userDao.getTeamOrderTotal();
        TeamDomain teamDomain = teamDao.showData();
        Map mapResult = new HashMap();
        List<Map> listMap = new ArrayList<>();

        if(null != teamDomain.getTeam1()){
            Map domainMap1 = new HashMap();
            domainMap1.put("team",teamDomain.getTeam1());
            domainMap1.put("teamTotalNum",0);
            listMap.add(domainMap1);
        }  if(null != teamDomain.getTeam2()){
            Map domainMap2 = new HashMap();
            domainMap2.put("team",teamDomain.getTeam2());
            domainMap2.put("teamTotalNum",0);
            listMap.add(domainMap2);
        }  if(null != teamDomain.getTeam3()){
            Map domainMap3 = new HashMap();
            domainMap3.put("team",teamDomain.getTeam3());
            domainMap3.put("teamTotalNum",0);
            listMap.add(domainMap3);
        }  if(null != teamDomain.getTeam4()){
            Map domainMap4 = new HashMap();
            domainMap4.put("team",teamDomain.getTeam4());
            domainMap4.put("teamTotalNum",0);
            listMap.add(domainMap4);
        }  if(null != teamDomain.getTeam5()){
            Map domainMap5 = new HashMap();
            domainMap5.put("team",teamDomain.getTeam5());
            domainMap5.put("teamTotalNum",0);
            listMap.add(domainMap5);
        }  if(null != teamDomain.getTeam6()){
            Map domainMap6 = new HashMap();
            domainMap6.put("team",teamDomain.getTeam6());
            domainMap6.put("teamTotalNum",0);
            listMap.add(domainMap6);
        }
        for(Map ma: map){
            for(Map lsmap: listMap){
                if(null != ma.get("team") && lsmap.get("team").equals(ma.get("team"))){
                    lsmap.put("teamTotalNum",ma.get("teamTotalNum"));
                    continue;
                }
            }
        }
        String[] array = {"team","teamTotalNum"};
        mapResult.put("columns",array);
        mapResult.put("rows",listMap);
        return mapResult;
    }


    /**
     * 分成金额/每日
     * @param
     * @return
     */
    @PostMapping("/getDivideAmountByDay")
    @ApiOperation(value = "分成金额/每日")
    public Map getDivideAmountByDay(String startDate,String endDate) {

        Map map = new HashMap();
        if(StringUtils.isEmpty(startDate)){
            map.put("startDate", DateUtil.getAfterDate(DateUtil.getSystemTime(), -7));
        } else {
            map.put("startDate",startDate);
        }

        if(StringUtils.isEmpty(endDate)){
            map.put("endDate",DateUtil.getSystemTime());
        } else {
            map.put("endDate",endDate);
        }
        List<Map> result = userDao.getDivideAmountByDay(map);
        Map mapResult = new HashMap();
        String[] array = {"date","divideMoney"};
        mapResult.put("columns",array);
        mapResult.put("rows",result);
        return mapResult;
    }

    /**
     * 团队平均分
     * @param
     * @return
     */
    @PostMapping("/getTeamAvg")
    @ApiOperation(value = "团队平均分")
    public Map getTeamAvg(String startDate,String endDate) {

        Map map = new HashMap();
        if(StringUtils.isEmpty(startDate)){
            map.put("startDate", DateUtil.getAfterDate(DateUtil.getSystemTime(), -7));
        } else {
            map.put("startDate",startDate);
        }

        if(StringUtils.isEmpty(endDate)){
            map.put("endDate",DateUtil.getSystemTime());
        } else {
            map.put("endDate",endDate);
        }
        List<Map> result = userDao.getTeamAvg(map);
        TeamDomain teamDomain = teamDao.showData();
        List<Map> listMap = new ArrayList<>();
        if(null != teamDomain.getTeam1()){
            Map domainMap1 = new HashMap();
            domainMap1.put("team",teamDomain.getTeam1());
            domainMap1.put("teamScore",0);
            listMap.add(domainMap1);
        }  if(null != teamDomain.getTeam2()){
            Map domainMap2 = new HashMap();
            domainMap2.put("team",teamDomain.getTeam2());
            domainMap2.put("teamScore",0);
            listMap.add(domainMap2);
        }  if(null != teamDomain.getTeam3()){
            Map domainMap3 = new HashMap();
            domainMap3.put("team",teamDomain.getTeam3());
            domainMap3.put("teamScore",0);
            listMap.add(domainMap3);
        }  if(null != teamDomain.getTeam4()){
            Map domainMap4 = new HashMap();
            domainMap4.put("team",teamDomain.getTeam4());
            domainMap4.put("teamScore",0);
            listMap.add(domainMap4);
        }  if(null != teamDomain.getTeam5()){
            Map domainMap5 = new HashMap();
            domainMap5.put("team",teamDomain.getTeam5());
            domainMap5.put("teamScore",0);
            listMap.add(domainMap5);
        }  if(null != teamDomain.getTeam6()){
            Map domainMap6 = new HashMap();
            domainMap6.put("team",teamDomain.getTeam6());
            domainMap6.put("teamScore",0);
            listMap.add(domainMap6);
        }
        for(Map ma: result){
            for(Map lsmap: listMap){
                if(null != ma.get("team") && lsmap.get("team").equals(ma.get("team"))){
                    lsmap.put("teamScore",ma.get("teamScore"));
                    continue;
                }
            }
        }
        Map mapResult = new HashMap();
        String[] array = {"team","teamScore"};
        mapResult.put("columns",array);
        mapResult.put("rows",listMap);
        return mapResult;
    }


    /**
     * 新增
     * @param user
     * @return
     *
     */

    @PostMapping("/add")
    @ApiOperation(value = "添加用户（DONE）")
    @ApiImplicitParam(name = "userId", value = "用户ID，新增为0，编辑时必填", dataType = ApiDataType.LONG, paramType = ApiParamType.PATH)
    public  Map<String, Object> addUser(UserDomain user){

        log.info(user.getUserName()+user.getPassword()+user.getPhone());

        Map<String,Object> modelMap = new HashMap<String, Object>();
        if(user.getPhone()==null || user.getPhone()==""){
            modelMap.put("success",false);
            modelMap.put("msg","手机号不能为空！");

        }else{
            modelMap.put("success",userService.addUser(user));
        }

        return modelMap;

    }


//    @PostMapping("/login")
//    public Map<String, Object> login()


    /**
     * 分页和模糊查询数据
     * @param pageNum
     * @param pageSize
     * @param name
     * @param phone
     * @return
     */

    @GetMapping("/all")
    @ApiOperation(value = "条件查询（DONE）")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name", value = "用户名", dataType = ApiDataType.STRING, paramType = ApiParamType.QUERY),
//            @ApiImplicitParam(name = "phone", value = "手机号", dataType = ApiDataType.STRING, paramType = ApiParamType.QUERY),
//            @ApiImplicitParam(name = "pageNum", value = "第几页", dataType = ApiDataType.INT, paramType = ApiParamType.QUERY),
//            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = ApiDataType.INT, paramType = ApiParamType.QUERY),
//    })
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
             String name,
            String phone){ //获取参数
        return userService.findAllUser(pageNum,pageSize,name,phone);
    }






    /**
     * 获取单条记录
     * @param id
     * @return
     */

    @PostMapping("/alone")
    @ApiOperation(value = "回显单条记录（DONE）")
    public Map<String, Object> alone(Integer userId)  {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        if(userId==null){
            modelMap.put("success",false);
            modelMap.put("msg", "用户id不能为空！");
        }else{
            UserDomain userDomain = userService.selectOneUser(userId);
            modelMap.put("data", userDomain);
        }


        return modelMap;
    }

    /**
     * 删除
     * @param id
     * @return
     */

    @PostMapping("/del")
    @ApiOperation(value = "删除单条记录（DONE）")
    public Map<String, Object> removeArea(Integer userId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if(userId==null){
            modelMap.put("success",false);
            modelMap.put("msg", "用户id不能为空！");
        }else{
            modelMap.put("success", userService.delOneUser(userId));
        }

        return modelMap;
    }







}
