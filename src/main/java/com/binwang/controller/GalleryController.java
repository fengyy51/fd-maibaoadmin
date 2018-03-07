package com.binwang.controller;

import com.binwang.bean.activity.ActListModel;
import com.binwang.bean.activity.ActParam;
import com.binwang.bean.activity.RegItemListModel;
import com.binwang.bean.activity.SignListModel;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.gallery.GalleryListModel;
import com.binwang.service.ActivityService;
import com.binwang.service.GalleryService;
import com.binwang.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by yy 2018-3-7
 */
@Controller
@RequestMapping(value = "/gallery")
public class GalleryController

{
        private static final Logger LOGGER = LoggerFactory.getLogger(com.binwang.controller.GalleryController.class);

        @Resource
        private GalleryService galleryService;

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        @ResponseBody
        public Object list(@RequestParam(value = "curPage") int curPage,
                           @RequestParam(value = "pageSum") int pageSum,
                           @RequestParam(value = "name", required = false, defaultValue = "") String name,
                           @RequestParam(value="username") String username,
                           @RequestParam(value = "begin", required = false, defaultValue = "") String begin,
                           @RequestParam(value = "end", required = false, defaultValue = "") String end) {
            try {
                List<GalleryListModel> res =new ArrayList<>();
                int sum=0;
                if(username.equals("binwang158")){
                    LOGGER.info(username);
                    res=galleryService.list(curPage, pageSum, name,begin, end);
                    LOGGER.info(res.toString());
                    sum = galleryService.listSum(name, begin, end);
                }
                HashMap<String, Object> m = new HashMap<>();
                m.put("list", res);
                m.put("sum", sum);
                return ResponseUtil.okJSON(m);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return ResponseUtil.errorJSON(e.getMessage());
            }
        }

//    @RequestMapping("/list")
//    @ResponseBody
//    public Object list(@RequestParam("collectId") long collectId,
//                       @RequestParam("type") int type,
//                       @RequestParam("openId") String openId,
//                       @RequestParam("curPage") int curPage,
//                       @RequestParam("pageSum") int pageSum) {
//        try {
//            List<CListModel> list = galleryService.getList(collectId, type,openId, curPage, pageSum);
//            int sum = galleryService.getListSum(collectId, type,openId);
//            //获取已通过人数
//            int approveSum = galleryService.getApproveSum(collectId);
//            Map<String, Object> m = new HashMap<>();
//            m.put("list", list);
//            m.put("sum", sum);
//            m.put("approveSum", approveSum);
//            return ResponseUtil.okJSON(m);
//        } catch (Exception e) {
//            return ResponseUtil.errorJSON("获取列表出错");
//        }
//    }
//
//
//    @RequestMapping("/detail")
//    @ResponseBody
//    public Object detail(@RequestParam("collectId") long collectId,
//                         @RequestParam("itemId") long itemId) {
//        try {
//            CDetailModel res = galleryService.getDetail(collectId, itemId);
//            return ResponseUtil.okJSON(res);
//        } catch (Exception e) {
//            return ResponseUtil.errorJSON("获取详情失败");
//        }
//    }
//
//    @RequestMapping(value = "/handle", method = RequestMethod.POST)
//    @ResponseBody
//    public Object handle(@RequestParam("collectId") long collectId,
//                         @RequestParam("itemId") long itemId,
//                         @RequestParam("approve") int approve) {
//        try {
//            Boolean res = galleryService.handleApprove(collectId, itemId, approve);
//            Map<String, Boolean> m = new HashMap<>();
//            m.put("result", res);
//            return ResponseUtil.okJSON(m);
//        } catch (Exception e) {
//            return ResponseUtil.errorJSON("更新审核状态出错");
//        }
//    }
//
//
//    @RequestMapping(value = "/post-first-img", method = RequestMethod.POST)
//    @ResponseBody
//    public Object firstImgPost(@RequestParam("id") long id,
//                               @RequestParam("url") String url) {
//        try {
//            Boolean res = galleryService.firstImgUpdate(id, url);
//            Map<String, Boolean> m = new HashMap<>();
//            m.put("result", res);
//            return ResponseUtil.okJSON(m);
//        } catch (Exception e) {
//            return ResponseUtil.errorJSON("出错,确保序号存在");
//        }
//    }
//
//    @RequestMapping(value = "/post-detail-img",method = RequestMethod.POST)
//    @ResponseBody
//    public Object detailImgPost(@RequestParam("id") long id,
//                                @RequestParam("urls") String urls) {
//        try {
//            Boolean res = galleryService.detailImgUpdate(id, urls);
//            Map<String, Boolean> m = new HashMap<>();
//            m.put("result", res);
//            return ResponseUtil.okJSON(m);
//        } catch (Exception e) {
//            return ResponseUtil.errorJSON("出错,确保序号存在");
//        }
//    }


}
