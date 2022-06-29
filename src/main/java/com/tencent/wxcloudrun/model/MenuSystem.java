package com.tencent.wxcloudrun.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuSystem {
    private Long menuId;
    private String menuName;
    private Long  parentId;
    private int order_num;
    private String path;
    private String component;
    private String query;
    private String menuType;
    private String visible;
    private String icon;
    private Date createTime;
    private String remark;
    private List<MenuSystem> children = new ArrayList<MenuSystem>();



    public String getMenuName() {
        return menuName;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<MenuSystem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuSystem> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuSystem{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", order_num=" + order_num +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", query='" + query + '\'' +
                ", menuType='" + menuType + '\'' +
                ", visible='" + visible + '\'' +
                ", icon='" + icon + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", children=" + children +
                '}';
    }
}
