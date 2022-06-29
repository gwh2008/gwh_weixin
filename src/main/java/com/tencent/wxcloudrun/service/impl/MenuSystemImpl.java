package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.model.MenuSystem;
import com.tencent.wxcloudrun.dao.MenuSystemMapper;
import com.tencent.wxcloudrun.service.MenuSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.example.single")
public class MenuSystemImpl implements MenuSystemService {

    @Autowired
    private MenuSystemMapper menuSystemMapper;

    @Override
    public List<MenuSystem> findRouter() {
        List<MenuSystem> AllMenu = menuSystemMapper.findRouter();
        getChildPerms(AllMenu,0);
        return getChildPerms(AllMenu,0);
    }

    /**4
     * Iterator循环便利 获取所有子节点
     * @param list
     * @param paraentId
     * @return
     */
    public List<MenuSystem> getChildPerms(List<MenuSystem> list,int paraentId) {
        List<MenuSystem> returnList = new ArrayList<>();
        for (Iterator<MenuSystem> iterator = list.iterator();iterator.hasNext();){
            MenuSystem t = (MenuSystem) iterator.next();
            // 根据表menuID先获取一级菜单
            if (t.getParentId() == paraentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        System.out.println(returnList);
        return returnList;
    }

    /**
     * 递归列表
     * @param list
     * @param t
     */
    public void recursionFn(List<MenuSystem> list, MenuSystem t) {
        List<MenuSystem> childList = getChildList(list, t);
        t.setChildren(childList);
        for(MenuSystem tChild: childList) {
            if (hasChild(list, tChild)){
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     * @param list
     * @param t
     * @return
     */
    public List<MenuSystem> getChildList(List<MenuSystem> list, MenuSystem t) {
        List<MenuSystem> tlist = new ArrayList<MenuSystem>();
        Iterator<MenuSystem> it = list.iterator();
        while (it.hasNext()) {
            MenuSystem n = (MenuSystem) it.next();
            if(n.getParentId().longValue() == t.getMenuId().longValue()){
                tlist.add(n);
            }
        }
        return tlist;
    }


    private boolean hasChild(List<MenuSystem> list, MenuSystem t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

}
