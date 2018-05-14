package com.binwang.service.impl;

import com.binwang.bean.shop.ShopListModel;
import com.binwang.bean.shop.ShopParam;
import com.binwang.bean.shop.ShopResource;
import com.binwang.bean.votedata.VoteListModel;
import com.binwang.bean.votedata.VoteParam;
import com.binwang.bean.votedata.VoteResource;
import com.binwang.dao.IShopDao;
import com.binwang.dao.IVoteDataDao;
import com.binwang.exception.UserException;
import com.binwang.service.ShopService;
import com.binwang.service.VoteDataService;
import com.binwang.util.ArrayUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
@Service
public class VoteDataServiceImpl implements VoteDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Resource
    private IVoteDataDao iVoteDataDao;

    private final ExecutorService exec = Executors.newFixedThreadPool(10);

    @Override
    @Transactional
    public Boolean add(String content,List<String>voteDataImgs) {
        String voteImgsUrl="";
        if (voteDataImgs.size() > 0) {
            voteImgsUrl+= StringUtils.join(voteDataImgs.toArray(),"&&&");
        }
        int res=iVoteDataDao.addVoteData(content,voteImgsUrl);
        if(res>0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public List<VoteListModel> list(String content,int curPage, int pageSum) {
        try {
            List<VoteListModel> res = iVoteDataDao.listVoteData(content, (curPage - 1) * pageSum, pageSum);
            return res;
        } catch (Exception e) {
            LOGGER.error("获取投票作品数据列表失败");
            throw new UserException("获取投票作品数据列表失败");
        }
    }
//
    @Override
    public int listSum(String content) {
        try {
            int sum = iVoteDataDao.listVoteDataSum(content);
            return sum;
        } catch (Exception e) {
            LOGGER.error("统计投票作品数据列表数量失败");
            throw new UserException("统计投票作品数据列表数量失败");
        }
    }

    @Override
    public VoteParam get(long id) {
        if (id <= 0)
            throw new UserException("投票作品数据id不符合要求");
        try {
            String[] imgs=iVoteDataDao.getImgById(id).split("&&&");
            LOGGER.info(imgs.toString());
            List<String>imgUrls=new ArrayList<>();
            for(int i=0;i<imgs.length;i++){
                imgUrls.add(imgs[i]);
            }
            String content=iVoteDataDao.getContentById(id);
            VoteParam s=new VoteParam();
            s.setId(id);
            s.setContent(content);
            s.setVoteDataImgs(imgUrls);
            return s;
        } catch (Exception e) {
            LOGGER.error("投票作品数据查询失败，id:" + id);
            throw new UserException("投票作品数据详情查询失败");
        }
    }
////
    @Override
    public Boolean edit(VoteParam voteParam) {
        String voteImgsUrl="";
        if (voteParam.getVoteDataImgs().size() > 0) {
            voteImgsUrl+= StringUtils.join(voteParam.getVoteDataImgs().toArray(),"&&&");
        }
        long id=voteParam.getId();
        String content=voteParam.getContent();
        int mainRes = iVoteDataDao.edit(id,content,voteImgsUrl);
        if (mainRes > 0) {
            return true;
        } else {
            LOGGER.error("投票作品数据更新失败，id:" + voteParam.getId());
            throw new UserException("投票作品数据更新失败");
        }
    }
//
    @Override
    @Transactional
    public Boolean delete(long id) {
        try {
            int mainRes = iVoteDataDao.delete(id);
            if(mainRes > 0)
                return true;
            else {
                LOGGER.error("投票作品数据删除传入不存在id:" + id);
                throw new UserException("投票作品数据删除传入不存在id");
            }
        } catch (Exception e) {
            LOGGER.error("投票作品数据删除接口出错，id:" + id);
            throw new UserException("投票作品数据删除接口出错");
        }
    }
}
