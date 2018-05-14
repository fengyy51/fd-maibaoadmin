package com.binwang.dao;

import com.binwang.bean.votedata.VoteListModel;
import com.binwang.bean.votedata.VoteParam;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IVoteDataDao {
    @Insert("INSERT INTO vote_data_yiwu (content,img_url) VALUES(#{content},#{voteImgsUrl} )")
    int addVoteData(@Param("content")String content,@Param("voteImgsUrl")String voteImgsUrl);

    List<VoteListModel> listVoteData(@Param("content")String content,@Param("start") int start, @Param("pageSum") int pageSum);

    int listVoteDataSum(@Param("content")String content);

    @Select("select img_url from vote_data_yiwu where id=#{id}")
    String getImgById(@Param("id")long id);

    @Select("select content from vote_data_yiwu where id=#{id}")
    String getContentById(@Param("id")long id);

    @Update("update vote_data_yiwu set content=#{content},img_url=#{voteImgsUrl} where id=#{id}")
    int edit(@Param("id")long id,@Param("content")String content,@Param("voteImgsUrl")String voteImgsUrl);

    @Delete("delete from vote_data_yiwu where id=#{id}")
    int delete(@Param("id")long id);
}
