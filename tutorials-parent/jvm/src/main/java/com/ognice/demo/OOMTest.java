package com.ognice.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/24
 */
public class OOMTest {
    /**
     * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(new byte[5 * 1024 * 1024]);
            System.out.println("分配次数：" + (++i));
        }

    }
}
