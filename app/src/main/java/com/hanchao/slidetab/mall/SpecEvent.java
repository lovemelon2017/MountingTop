package com.hanchao.slidetab.mall;

public class SpecEvent {
    int position;
    String specName;//父级 分类
    String specSonName;//子级 名称
    String specKeyValue;//父id : 子id

    public SpecEvent(int position, String specName, String specSonName, String specKeyValue) {
        this.position = position;
        this.specName = specName;
        this.specSonName = specSonName;
        this.specKeyValue = specKeyValue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecKeyValue() {
        return specKeyValue;
    }

    public void setSpecKeyValue(String specKeyValue) {
        this.specKeyValue = specKeyValue;
    }

    public String getSpecSonName() {
        return specSonName;
    }

    public void setSpecSonName(String specSonName) {
        this.specSonName = specSonName;
    }
}
