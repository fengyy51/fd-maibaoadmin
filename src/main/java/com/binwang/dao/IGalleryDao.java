package com.binwang.dao;

import com.binwang.bean.activity.ActListModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.gallery.GalleryListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface IGalleryDao {
    List<GalleryListModel> listGallery(@Param("name") String name, @Param("begin") String begin, @Param("end") String end,
                                       @Param("start") int start, @Param("pageSum") int pageSum);

    int listGallerySum(@Param("name") String name,@Param("begin") String begin, @Param("end") String end);

    List<CListModel> getList(@Param("collectId") long collectId, @Param("type") int type, @Param("openId")String openId, @Param("start") int start, @Param("pageSum") int pageSum);


    //审核通过人数
    @Select("select count(id) from f_user_act where act_id = #{collectId} and is_ok = 1")
    int approveSum(@Param("collectId") long collectId);
    //改变审核状态
    @Update("update f_user_act set is_ok = #{isOk} where id = #{itemId} and act_id = #{collectId}")
    int handleApprove(@Param("collectId") long collectId, @Param("itemId") long itemId, @Param("isOk") int isOk);

}
