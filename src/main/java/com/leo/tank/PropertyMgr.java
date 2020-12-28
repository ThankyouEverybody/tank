package com.leo.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Leo
 * @ClassName PropertyMgr
 * @DATE 2020/12/27 5:56 下午
 * @Description 读取配置文件
 */
public class PropertyMgr {

    private static Properties properties = null;

    private PropertyMgr() {

    }
    // TODO 单例模式 DCL double check lock
    private static Properties getInstance() {
        if (properties == null) {
            synchronized (PropertyMgr.class) {
                if (properties == null) {
                    properties = new Properties();
                }
            }
        }
        return properties;
    }


    static {
        try {
            getInstance().load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }

    public static void main(String[] args) {
        System.out.println(properties.get("initTankCount"));
    }
}


