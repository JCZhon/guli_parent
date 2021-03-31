package com.zjh.eduService.controller;

import java.util.*;

/**
 * 个人联系测试2
 */
public class Test2 {

    public static void main(String[] args) {
        //System.out.println(Test2.itelatorList());
        Test2.itelatorList();
        Test2.itelatorMap();
    }


    /**
     * list和map的遍历
     */
    public static void itelatorList() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("001", "数据1");
        map1.put("002", "数据2");
        map1.put("003", "数据3");
        System.out.println(map1.toString());

        Map<String, String> map2 = new HashMap<>();
        map2.put("010", "数据4");
        map2.put("020", "数据5");
        map2.put("030", "数据6");

        List<Map> list = new ArrayList();
        list.add(map1);
        list.add(map2);

        Iterator<Map> i = list.iterator();
        Map<String, String> map;
        while (i.hasNext()) {
            map = i.next();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key + value);
            }
            System.out.println("----------单独获取key--------");
            for (String key : map.keySet()) {
                System.out.println(key);
            }
        }

    }

    /**
     * 遍历map的四种方式
     */
    public static void itelatorMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }

    }
}
