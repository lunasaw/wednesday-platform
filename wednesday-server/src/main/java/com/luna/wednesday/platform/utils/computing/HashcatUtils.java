package com.luna.wednesday.platform.utils.computing;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.iteknical.common.utils.MathUtils;

/**
 * @author Tony
 */
public class HashcatUtils {

    private static final String            CHARSET_L             = "?l";
    private static final String            CHARSET_U             = "?u";
    private static final String            CHARSET_D             = "?d";
    private static final String            CHARSET_H             = "?h";
    private static final String            CHARSET_UPPER_H       = "?H";
    private static final String            CHARSET_S             = "?s";
    private static final String            CHARSET_A             = "?a";
    private static final String            CHARSET_B             = "?b";

    private static final long              CHARSET_L_LINES       = 26L;
    private static final long              CHARSET_U_LINES       = 26L;
    private static final long              CHARSET_D_LINES       = 10L;
    private static final long              CHARSET_H_LINES       = 16L;
    private static final long              CHARSET_UPPER_H_LINES = 16L;
    private static final long              CHARSET_S_LINES       = 33L;
    private static final long              CHARSET_A_LINES       = 95L;
    private static final long              CHARSET_B_LINES       = 256L;

    private static final Map<String, Long> CHARSET_2_LINES       = ImmutableMap.<String, Long>builder()
        .put(CHARSET_L, CHARSET_L_LINES)
        .put(CHARSET_U, CHARSET_U_LINES)
        .put(CHARSET_D, CHARSET_D_LINES)
        .put(CHARSET_H, CHARSET_H_LINES)
        .put(CHARSET_UPPER_H, CHARSET_UPPER_H_LINES)
        .put(CHARSET_S, CHARSET_S_LINES)
        .put(CHARSET_A, CHARSET_A_LINES)
        .put(CHARSET_B, CHARSET_B_LINES)
        .build();

    /**
     * 计算掩码的实际行数
     * 
     * @param mask
     * @return
     */
    public static long calculateMaskLines(String mask) {
        // TODO 先不考虑mask中有 -这样极端恶心的case
        // TODO mask的设计还是躲不开一个合理的数据结构，才能处理类似于有空格情况下的恶心的场景

        // 掩码部分
        String realMask = null;
        // 自定义字符部分
        List<String> customerCharsetList = Lists.newArrayList();
        if (mask.contains(" -")) {
            // 说明掩码有自定义部分，拆分掩码和自定义部分
            String[] split = mask.split(" -");
            realMask = split[0];
            for (int i = 1; i < split.length; i++) {
                customerCharsetList.add(split[i]);
            }
        } else {
            realMask = mask;
        }

        // 计算自定义字符的长度
        long cs1Lines = 0;
        long cs2Lines = 0;
        long cs3Lines = 0;
        long cs4Lines = 0;
        if (CollectionUtils.isNotEmpty(customerCharsetList)) {
            for (String customerCharset : customerCharsetList) {
                if (customerCharset.startsWith("1 ")) {
                    cs1Lines = calculateCustomerCharsetLines(customerCharset.substring(2));
                }
                if (customerCharset.startsWith("2 ")) {
                    cs2Lines = calculateCustomerCharsetLines(customerCharset.substring(2));
                }
                if (customerCharset.startsWith("3 ")) {
                    cs3Lines = calculateCustomerCharsetLines(customerCharset.substring(2));
                }
                if (customerCharset.startsWith("4 ")) {
                    cs4Lines = calculateCustomerCharsetLines(customerCharset.substring(2));
                }
            }
        }

        long lines = 1;

        // 计算标准charSet
        for (Map.Entry<String, Long> entry : CHARSET_2_LINES.entrySet()) {
            int countMatches = StringUtils.countMatches(realMask, entry.getKey());
            if (countMatches != 0) {
                lines = lines * MathUtils.pow(entry.getValue(), countMatches);
            }
        }

        // 计算自定义charSet
        if (cs1Lines != 0) {
            int countMatches = StringUtils.countMatches(realMask, "?1");
            if (countMatches != 0) {
                lines = lines * MathUtils.pow(cs1Lines, countMatches);
            }
        }
        if (cs2Lines != 0) {
            int countMatches = StringUtils.countMatches(realMask, "?1");
            if (countMatches != 0) {
                lines = lines * MathUtils.pow(cs2Lines, countMatches);
            }
        }
        if (cs3Lines != 0) {
            int countMatches = StringUtils.countMatches(realMask, "?1");
            if (countMatches != 0) {
                lines = lines * MathUtils.pow(cs3Lines, countMatches);
            }
        }
        if (cs4Lines != 0) {
            int countMatches = StringUtils.countMatches(realMask, "?1");
            if (countMatches != 0) {
                lines = lines * MathUtils.pow(cs4Lines, countMatches);
            }
        }

        return lines;
    }

    private static long calculateCustomerCharsetLines(String customerCharset) {
        String customerCharsetCopy = new String(customerCharset);
        long lines = 0;

        // 计算标准charSet
        for (Map.Entry<String, Long> entry : CHARSET_2_LINES.entrySet()) {
            if (customerCharsetCopy.contains(entry.getKey())) {
                lines = lines + entry.getValue();
                customerCharsetCopy = customerCharsetCopy.replace(entry.getKey(), "");
            }

        }

        // 计算剩余字符
        if (StringUtils.isNotEmpty(customerCharsetCopy)) {
            Set<Character> uniqueChar = Sets.newHashSet();
            for (int i = 0; i < customerCharsetCopy.length(); i++) {
                uniqueChar.add(customerCharset.charAt(i));
            }
            lines = lines + uniqueChar.size();
        }
        return lines;
    }

}
