package com.binwang.service.impl;

import com.binwang.bean.activity.*;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.gallery.GalleryListModel;
import com.binwang.dao.IActivityDao;
import com.binwang.dao.IGalleryDao;
import com.binwang.exception.UserException;
import com.binwang.service.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
@Service
public class GalleryServiceImpl implements GalleryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GalleryServiceImpl.class);


    @Resource
    private IGalleryDao iGalleryDao;

    @Override
    public List<GalleryListModel> list(int curPage, int pageSum, String name, String begin, String end) {
        try {
            List<GalleryListModel> res = iGalleryDao.listGallery(name,begin, end, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UserException("获取活动列表失败！");
        }
    }
    @Override
    public int listSum(String name, String begin, String end) {
        try {
            return iGalleryDao.listGallerySum(name,begin, end);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new UserException("获取活动列表数量失败!");
        }
    }
//    @Override
//    public List<CListModel> getList(long collectId, int type, String openId, int curPage, int pageSum) {
//        try {
//            List<CListModel> res = collectDao.getList(collectId, type, openId, (curPage - 1) * pageSum, pageSum);
//            return res;
//        } catch (Exception e) {
//            LOGGER.error("获取作品集列表失败，collectId为:" + collectId);
//            throw new RuntimeException("获取作品集列表失败");
//        }
//    }
//
//    @Override
//    public CDetailModel getDetail(long collectId, long itemId) {
//        if (collectId < 0 || itemId < 0)
//            throw new UserException("参数不合法");
//        return collectDao.getDetail(itemId, collectId);
//    }
//
//    @Override
//    @Transactional
//    public Boolean handleApprove(long collectId, long itemId, int type) {
//        if (collectId < 0 || itemId < 0)
//            throw new UserException("参数不合法");
//        int res = collectDao.handleApprove(collectId, itemId, type);
//        if (res > 0)
//            return true;
//        else {
//            LOGGER.error("更新作品是否审核失败，id为：" + itemId);
//            return false;
//        }
//    }

}
