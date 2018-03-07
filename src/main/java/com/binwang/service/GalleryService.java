package com.binwang.service;

import com.binwang.bean.activity.ActListModel;
import com.binwang.bean.activity.ActParam;
import com.binwang.bean.activity.RegItemListModel;
import com.binwang.bean.activity.SignListModel;
import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.gallery.GalleryListModel;

import java.util.List;
/**
 * Created by yy on 17/12/13.
 */
public interface GalleryService {
    List<GalleryListModel> list(int curPage, int pageSum, String name, String begin, String end);
    int listSum(String name,String begin, String end);
//    List<CListModel> getList(long collectId, int type, String openId, int curPage, int pageSum);
//
//    CDetailModel getDetail(long collectId, long itemId);
//
//    Boolean handleApprove(long collectId, long itemId, int type);
}
