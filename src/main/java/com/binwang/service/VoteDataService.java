package com.binwang.service;

import com.binwang.bean.votedata.VoteListModel;
import com.binwang.bean.votedata.VoteParam;

import java.util.List;

public interface VoteDataService {
    Boolean add(String content,List<String>voteDataImgs);

    List<VoteListModel> list(String content,int curPage, int pageSum);

    int listSum(String content);

    VoteParam get(long id);

    Boolean edit(VoteParam voteParam);

    Boolean delete(long id);
}
