package com.block.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    /**
     * 判断是否为空字符串最优代码
     *
     * @param str
     * @return 如果为空，则返回true
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否非空
     *
     * @param str 如果不为空，则返回true
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 拆分字符串，转化为列表
     *
     * @param str 字符串
     * @param regex 规则
     * @return 字符串列表
     * @author wangsiqian
     * @date 2021/02/20
     */
    public static List<String> split(String str, String regex) {
        if (StringUtil.isEmpty(str)) {
            return new ArrayList<>();
        }

        String[] split = str.split(regex);
        return Arrays.stream(split).filter(StringUtil::isNotEmpty).collect(Collectors.toList());
    }
}
