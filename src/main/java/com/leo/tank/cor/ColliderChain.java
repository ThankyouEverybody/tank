package com.leo.tank.cor;

import com.leo.tank.GameObject;
import com.leo.tank.PropertyMgr;

import javax.lang.model.element.VariableElement;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Leo
 * @ClassName ColliderChain
 * @DATE 2021/1/4 2:20 下午
 * @Description 责任链模式
 * 碰撞检测链
 * 1.实现了Collider接口可以将两个链在继续串起来
 */
public class ColliderChain implements Collider {


    private List<Collider> colliderList;

    /**
     * 功能描述 : 构造
     *
     * @author Leo
     * @date 2021/1/4 2:22 下午
     */
    public ColliderChain() {
        colliderList = new LinkedList<>();
        //从配置文件读取.
        String configStr = String.valueOf(PropertyMgr.get("colliders"));
        if (configStr != null && configStr.trim().length() != 0) {
            String[] strArray = configStr.split(",");
            for (int i = 0; i < strArray.length; i++) {
                try {
                    colliderList.add((Collider) Class.forName(strArray[i]).newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 功能描述 : 添加碰撞器
     *
     * @param collider
     * @return void
     * @author Leo
     * @date 2021/1/4 2:21 下午
     */
    public void add(Collider collider) {
        colliderList.add(collider);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliderList.size(); i++) {
            colliderList.get(i).collide(o1, o2);
        }
        return true;
    }
}
