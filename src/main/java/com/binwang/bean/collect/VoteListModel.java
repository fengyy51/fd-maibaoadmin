package com.binwang.bean.collect;

public class VoteListModel {
    private Long id;
    private long actId;
    private String actName;
    private String begin;
    private String end;
    public VoteListModel() {
    }
    public VoteListModel(Long id, Long actId,String actName, String begin, String end) {
        this.id = id;
        this.actId=actId;
        this.actName = actName;
        this.begin = begin;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getActId() {
        return actId;
    }

    public void setActId(long actId) {
        this.actId = actId;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }
}
