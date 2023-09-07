package com.summer.common.util;

/**
 * @description: 数组工具类
 * @author: chenhao
 * @date: 2023-7-21 9:33
 */
public class ArrayUtils {

    /**
     * 不存在的参数
     */
    public static int INVALID_INDEX=-1;
    /**
     * 获取对应参数数中的下标
     * @param array
     * @param parameter
     * @return
     */
    public static int indexOf(String[] array,String parameter){
        for (int i=0;i<array.length;i++){
            if(array[i].equals(parameter)){
                return i;
            }
        }
        return -1;
    }
}
