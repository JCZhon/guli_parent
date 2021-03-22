package com.zjh.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试easyExcel的读写操作
 */
public class DemoDataTest {
    public static void main(String[] args) {
        /**
         * 测试写操作
         * 写操作就是将数据写到excel中
         */
      /*  //1.设置excel文件及文件所在地址
        String fileName = "D:\\demoDataWrite.xlsx";

        //2.调用easyExcel中的方法实现写操作
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getList());
    }*/

        /**
         * 测试读操作
         */
        String fileName = "D:\\demoDataWrite.xlsx";
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();


    }

    private static List<DemoData> getList() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("Lucy" + i);
            list.add(data);
        }
        return list;
    }

}
