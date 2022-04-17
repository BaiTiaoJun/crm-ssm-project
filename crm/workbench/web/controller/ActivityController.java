package com.zhangshun.crm.workbench.web.controller;

import com.sun.scenario.effect.impl.prism.PrImage;
import com.zhangshun.crm.commons.util.ConstantUtil;
import com.zhangshun.crm.commons.util.DateFormatUtil;
import com.zhangshun.crm.commons.util.HSSFUtil;
import com.zhangshun.crm.commons.util.UUIDUtil;
import com.zhangshun.crm.commons.vo.ReturnInfoVo;
import com.zhangshun.crm.settings.domain.User;
import com.zhangshun.crm.settings.service.UserService;
import com.zhangshun.crm.workbench.domain.Activity;
import com.zhangshun.crm.workbench.domain.ActivityRemark;
import com.zhangshun.crm.workbench.service.ActivityRemarkService;
import com.zhangshun.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
public class ActivityController {
    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRemarkService activityRemarkService;

    /**
     * 生成市场活动excel表的参数信息
     */
    //excel表的表头字段
    private static final String[] titles = {"活动id", "所有人", "名称", "开始时间", "结束时间", "成本", "描述", "创建时间", "创建人", "编辑时间", "编辑人"};
    //市场活动实例的get方法获取字段值
    private static final String[] methods = {"getId", "getOwner", "getName", "getStartdate", "getEnddate", "getCost", "getDescription", "getCreatetime", "getCreateby", "getEdittime", "getEditby"};

    @RequestMapping("/workbench/activity/selectUserList.do")
    public String selectUserList(HttpServletRequest request) {
        List<User> users = userService.queryAllUsers();
        request.setAttribute("users", users);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody
    Object saveCreateActivity(Activity activity, HttpSession session) {
        //封装参数
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateby(((User) session.getAttribute(ConstantUtil.SESSION_USER)).getId());
        activity.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));

        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        try {
            int res = activityService.saveCreateActivity(activity);
            if (res == 0) {
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage(ConstantUtil.INSERT_FAIL_MESSAGE);
            } else {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
            returnInfoVo.setMessage(ConstantUtil.INSERT_FAIL_MESSAGE);
        }
        return returnInfoVo;
    }

    @RequestMapping("/workbench/activity/selectActivityByConditionForPage.do")
    public @ResponseBody
    Object selectActivityByConditionForPage(String name, String owner, String startDate, String endDate, Integer pageNo, Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("owner", owner);
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("pageBegin", (pageNo - 1) * pageSize);
        paramMap.put("pageSize", pageSize);

//        根据条件和页数返回activity所有记录
        List<Activity> activityList = activityService.selectActivityByConditionForPage(paramMap);

//        根据根据条件和页数返回activity总记录的条数
        int totalRows = activityService.queryCountOfActivityByCondition(paramMap);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("activityList", activityList);
        resMap.put("totalRows", totalRows);
        return resMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityByList.do")
    public @ResponseBody
    Object deleteActivityByList(String[] ids) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        try {
            int res = activityService.deleteActivityByList(ids);
            if (res > 0) {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
            } else {
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage("系统忙，请稍后再试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
            returnInfoVo.setMessage("系统忙，请稍后再试...");
        }
        return returnInfoVo;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    public @ResponseBody
    Object queryActivityById(String id) {
        return activityService.queryActivityById(id);
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    public @ResponseBody
    Object saveEditActivity(Activity activity, HttpSession session) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();

        activity.setEditby(((User) session.getAttribute("user")).getId());
        activity.setEdittime(DateFormatUtil.getDateFormat_FULL(new Date()));

        try {
            int res = activityService.updateEditActivity(activity);
            if (res != 0) {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
            } else {
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage("系统繁忙，请稍后再试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
            returnInfoVo.setMessage("系统繁忙，请稍后再试...");
        }

        return returnInfoVo;
    }

    @RequestMapping("/workbench/activity/exportActivities.do")
    public void exportActivities(HttpServletResponse response) throws IOException {
        //获取市场活动表所有信息
        List<Activity> activityList = activityService.queryAllActivities();
        //创建工作表生成excel表格
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFUtil.generateWorkbook(titles, methods, workbook, activityList);
        //设置响应到浏览器的格式
        response.setContentType("application/octet-stream;charset=UTF-8");
        //设置响应头响应数据的方式为附件下载
        response.setHeader("Content-Disposition", "attachment;filename=activities.xls");
        //获取字节输出流管道
        OutputStream out = response.getOutputStream();
        //把生成的excel文件通过字节输出流从内存写到浏览器响应
        HSSFUtil.exportWorkbook(workbook, out);
    }

    @RequestMapping("/workbench/activity/exportActivitiesById.do")
    public void exportActivitiesById(HttpServletResponse response, String[] ids) throws IOException {
        //根据id获取市场活动表所有信息
        List<Activity> activityList = activityService.queryActivitiesById(ids);
        //创建工作表生成excel表格
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFUtil.generateWorkbook(titles, methods, workbook, activityList);
        //设置响应到浏览器的格式为二进制数据
        response.setContentType("application/octet-stream;charset=UTF-8");
        //设置响应头响应数据的方式为附件下载
        response.setHeader("Content-Disposition", "attachment;filename=activities.xls");
        //获取字节输出流管道
        OutputStream out = response.getOutputStream();
        //把生成的excel文件通过字节输出流从内存写到浏览器响应
        HSSFUtil.exportWorkbook(workbook, out);
    }

    @PostMapping("/workbench/activity/importActivitiesByExcel.do")
    public @ResponseBody Object importActivitiesByExcel(HttpSession session, MultipartFile activityFile) {
        ReturnInfoVo returnInfoVo = new ReturnInfoVo();
        //指定生成文件的路径和文件名称与格式
        //从内存获取就不用执行这行代码，就是不用往磁盘存储数据
        /*String path = "/Users/baitiaojun/java后端学习/java项目/CRM客户管理系统项目（SSM）/CRM_Project/exFiles" + activityFile.getOriginalFilename();
        File file = new File(path);*/
        try {
            //前台接收的文件转换到服务器上存储
            //本地磁盘生成文件，效率低
//            activityFile.transferTo(file);
            //服务器上获取上传的指定文件，效率低
//            FileInputStream in = new FileInputStream(path);

            //从内存获取文件，效率搞！！！！！
            InputStream in = activityFile.getInputStream();

            //从通过流从服务器获取excel文件封装到workbook
            HSSFWorkbook workbook = new HSSFWorkbook(in);

            HSSFSheet sheet = null;
            HSSFRow row = null;
            HSSFCell cell = null;
            User user = (User) session.getAttribute(ConstantUtil.SESSION_USER);
            List<Activity> activityList = new ArrayList<>();
            Activity activity = null;

            //把excel表中的数据封装到activity对象的集合中
            //获取工作簿中的页
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                sheet = workbook.getSheetAt(i);
                //获取每页的行(除开第0行的字段信息行)
                for (int j = 1; j < sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    if (row != null) {
                        //每行生成一个activity对象
                        activity = new Activity();
                        //根据业务生成id、owner、
                        activity.setId(UUIDUtil.getUUID());
                        activity.setOwner(user.getId());
                        activity.setCreatetime(DateFormatUtil.getDateFormat_FULL(new Date()));
                        activity.setCreateby(user.getId());
                        //从每行中获取每列
                        for (int k = 0; k < row.getLastCellNum(); k++) {
                            cell = row.getCell(k);
                            // 如果每列字段和每列字段的内容不为空
                            //每读取一列Excel表中的信息就封装到对应的activity属性中
                            if (k == 0) {
                                activity.setName(HSSFUtil.getValueForColum(cell));
                            } else if (k == 1) {
                                activity.setStartdate(HSSFUtil.getValueForColum(cell));
                            } else if (k == 2) {
                                activity.setEnddate(HSSFUtil.getValueForColum(cell));
                            } else if (k == 3) {
                                activity.setCost(HSSFUtil.getValueForColum(cell));
                            } else if (k == 4) {
                                activity.setDescription(HSSFUtil.getValueForColum(cell));
                            }
                        }
                    } else {
                        break;
                    }
                }
                //每生成一个对象就封装到list集合中
                activityList.add(activity);
            }
            //调用saveCreateActivityByList方法把数据集合添加到数据库
            int res = activityService.saveCreateActivityByList(activityList);
            //如果添加成功，返回状态码和添加的数据条数，如果添加失败，返回状态码和提示信息
            if (res > 0) {
                returnInfoVo.setCode(ConstantUtil.SUCCESS_CODE);
                returnInfoVo.setReturnRes(res);
            } else {
                returnInfoVo.setCode(ConstantUtil.FAIL_CODE);
                returnInfoVo.setMessage("系统忙，请稍后再试...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnInfoVo;
    }

    @RequestMapping(value = "/workbench/activity/activityDetail.do")
    public String activityDetail(String id, HttpServletRequest request) {
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> activityRemarks = activityRemarkService.queryActivityRemarkForDetailByActivityId(activity.getId());

        request.setAttribute("activity", activity);
        request.setAttribute("activityRemarks", activityRemarks);

        return "workbench/activity/detail";
    }

    //测试文件下载
/*
    @RequestMapping("/workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response) {
//        设置响应类型
        response.setContentType("application/octet-stream;charset=UTF-8");
//        设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=downloadTest.xls");

        InputStream in = null;
        try {
//            输入流读取文件
            in = new FileInputStream("/Users/baitiaojun/java后端学习/codeDemo/apache-poi/test.xls");
//            定义缓冲区
            byte[] buffer = new byte[256];
//            从输入流读取数据到缓冲区,如果缓冲区的数据长度不为-1，就通过输出流继续把缓冲区的数据写到浏览器
            int len;
            while ((len = in.read(buffer)) != -1) {
//            定义字节输出流输出到浏览器
                OutputStream out = response.getOutputStream();
                out.write(buffer, 0, len);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    //测试文件上传
/*    @RequestMapping(value = "/workbench/activity/submitFile.do", produces = "text/plain;charset=utf-8")
    public @ResponseBody String SubmitFile(MultipartFile myFile) throws IOException {
        String fileName = myFile.getOriginalFilename();
        File file = new File("/Users/baitiaojun/java后端学习/codeDemo/apache-poi/", fileName);
        myFile.transferTo(file);

        return "上传成功";
    }*/
}