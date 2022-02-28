package com.block.util;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author :Anderson
 * @apiNote : 接口通用签名工具
 * */
public class SignUtils {

    public static Map<String, String> GetQueryParamsFromQueryString(String url) {
        if (!url.contains("?"))
            return null;
        String qs = url.split("\\?")[1];
        if (qs == null || qs.equals(""))
            return null;

        String[] segments = qs.split("&");

        Map<String, String> queryParams = new HashMap<>();

        if (segments.length > 0) {
            for (String item : segments) {
                String[] keyPair = item.split("=");
                String value = keyPair.length == 2 ? keyPair[1] : "";
                queryParams.put(keyPair[0], value);
            }

        }

        return queryParams;
    }

    /**
     * 接口签名方式主要是这样的，所有的接口都需要在请求头header中传递这几个公共参数access_key,sign,timestamp,nonce。
     * sign 的计算规则为：
     *  1：拼接接口的所有参数，参数名按照 ASCII 码从小到大排序（字典序），拼接的格式如 k1=v1&k2=v2&k3=v3 得到 params
     *  2：将拼接参数params和appKey、timestamp、nonce封装成map，进行Base64(HMAC_SHA1(map, secretKey))加密，得到 sign 值
     *  3：sign加到请求头header中，发送请求目标接口
     * 以上就是生成签名的过程。
     * */
    public static Map<String, String> AppendGatewayHeaders(String access_key,String secretKey,Map<String, String> headers, Map<String, String> queryParams) {
        if (headers == null)
            headers = new HashMap<>();
        long timestamp = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();//  LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        String nonce = UUID.randomUUID().toString().replaceAll("-", "");
        Map<String, String> toSignDic = new HashMap<>();
        toSignDic.put("access_key", access_key);
        toSignDic.put("timestamp", String.valueOf(timestamp));
        toSignDic.put("nonce", nonce);
        for (String key : headers.keySet()) {
            String value = headers.get(key);
            toSignDic.put(key, value);
        }
        if (queryParams != null) {
            for (String key : queryParams.keySet()) {
                String value = queryParams.get(key);
                toSignDic.put(key, value);
            }
            System.out.println(toSignDic);
        }
        byte[] hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secretKey).hmac(AsciiMap(toSignDic));
        String sign = Base64.getEncoder().encodeToString(hmac);
        toSignDic.put("sign", sign);
        return toSignDic;

    }

    public static String AsciiMap(Map<String, String> map) {
        String result;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<>(map.entrySet());
            infoIds.sort(Map.Entry.comparingByKey());
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                String key = item.getKey();
                String val = item.getValue();
                if (!val.equals("")) {
                    sb.append(key).append("=").append(val).append("&");
                }
            }
            result = sb.toString();
            result = result.substring(0,result.length() - 1);
        } catch (Exception e) {
            return null;
        }
        return result;
    }
}
