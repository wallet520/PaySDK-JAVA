package com.block.client.connection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.block.constant.Options;
import com.block.exception.SDKException;
import com.block.util.SignUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anderson
 * */
public class RestConnection {

    private final Options options;

    public Options getOptions() {
        return options;
    }

    public RestConnection(Options options) {
        this.options = options;
    }

    public JSONObject executeGetWithSignature(String path,Map<String ,Object> paramMap) {
        Options options = this.getOptions();
        Map<String, String> header = new HashMap<>();
        String requestUrl =  options.getRestHost() + path;
        if(paramMap != null && paramMap.size()!= 0){
            requestUrl += buildUrl(paramMap);
        }
        header = SignUtils.AppendGatewayHeaders(options.getAccessKey(),options.getSecretKey(),header,
                SignUtils.GetQueryParamsFromQueryString(requestUrl));
        String resultString = "";
        CloseableHttpResponse response;
        try {
            CloseableHttpClient httpclient = new SSLClient();
            requestUrl = requestUrl.trim();
            URIBuilder builder = new URIBuilder(requestUrl);
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            for (String key : header.keySet()) {
                httpGet.addHeader(key, header.get(key));
            }
            response = httpclient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            if(resultString.equals("")){
                throw new SDKException("status:"+response.getStatusLine(),"body:"+resultString);
            }
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SDKException(SDKException.RUNTIME_ERROR, "[Invoking] Response is not expected: " + e.getMessage());
        }
        return checkAndGetResponse(resultString);
    }


    public JSONObject executePostWithSignature(String path,String jsonParamString){
        Options options = this.getOptions();
        Map<String, String> header = new HashMap<>();
        header = SignUtils.AppendGatewayHeaders(options.getAccessKey(),options.getSecretKey(),header, null);
        CloseableHttpResponse response;
        String resultString = "";
        try {
            CloseableHttpClient httpClient = new SSLClient();
            String requestUrl =  options.getRestHost() + path;
            HttpPost httpPost = new HttpPost(requestUrl);
            for (String key : header.keySet()) {
                httpPost.addHeader(key, header.get(key));
            }
            StringEntity entity = new StringEntity(jsonParamString, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() != 200 )
                throw new SDKException("status:"+response.getStatusLine(),"body:"+resultString);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            if(resultString.equals("")){
                throw new SDKException("status:"+response.getStatusLine(),"body:"+resultString);
            }
            response.close();
        } catch (Exception e) {
            throw new SDKException(SDKException.RUNTIME_ERROR, "[Invoking] Response is not expected: " + e.getMessage());
        }
        return checkAndGetResponse(resultString);
    }


    private JSONObject checkAndGetResponse(String resp) {
        return JSON.parseObject(resp);
    }

    private String AppendUrl(Map<String, Object> map, StringBuilder stringBuilder) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            if (!("").equals(stringBuilder.toString())) {
//                System.out.println(entry.getKey());
//            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append(urlEncode(entry.getValue().toString()));
            stringBuilder.append("&");
        }
        return stringBuilder.substring(0,stringBuilder.length() - 1);
    }

    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new SDKException(SDKException.RUNTIME_ERROR,
                    "[URL] UTF-8 encoding not supported!");
        }
    }
    public String buildUrl(Map<String,Object> paramMap) {
        StringBuilder head = new StringBuilder("?");
        return AppendUrl(paramMap, head);
    }

    public static JSONObject get(String path) {
        String resultString = "";
        CloseableHttpResponse response;
        try {
            CloseableHttpClient httpclient = new SSLClient();
            URIBuilder builder = new URIBuilder(path);
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            response = httpclient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            if(resultString.equals("")){
                throw new SDKException("status:"+response.getStatusLine(),"body:"+resultString);
            }
            response.close();
        } catch (Exception e) {
            throw new SDKException(SDKException.RUNTIME_ERROR, "[Invoking] Response is not expected: " + e.getMessage());
        }
        return JSONObject.parseObject(resultString);
    }

}
