package com.joysim.pecc.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpPostUtils {

    public static String httpPost(String urlAddress, Map paramMap) throws IOException {
        if (paramMap == null) {
            paramMap = new HashMap();
        }
        String[] params = new String[paramMap.size()];
        int i = 0;
        Set set = paramMap.keySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            String paramKey = (String) iter.next();
            String param = paramKey + "=" + paramMap.get(paramKey);
            params[i] = param;
            i++;

        }
        return post(urlAddress, params);
    }

    public static String post(String urlAddress, String[] params) throws IOException {
        URL url = null;
        HttpURLConnection con = null;
        BufferedReader in = null;
        PrintWriter out = null;
        StringBuffer result = new StringBuffer();
        try {
            url = new URL(urlAddress);
            con = (HttpURLConnection) url.openConnection();

            // 设置通用的请求属性
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            String paramsTemp = "";
            for (int i = 0; i < params.length; i++) {
                String param = params[i];
                if (param != null && !"".equals(param.trim())) {
                    paramsTemp += "&" + param;
                }
            }
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(con.getOutputStream());
            // 发送请求参数
            out.print(paramsTemp);
            // flush输出流的缓冲
            out.flush();
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                } else {
                    result.append(line);
                }
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return result.toString();
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) throws IOException {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            //for (String key : map.keySet()) {
            //	System.out.println(key + "--->" + map.get(key));
            //}
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } finally {
            // 使用finally块来关闭输入流
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

    public static String http(String url, byte[] PostData, Map headers) throws IOException {
        URL u = null;
        HttpURLConnection con = null;
        InputStream inputStream = null;

        OutputStream outStream = null;
        ByteArrayOutputStream baos = null;
        String result = null;
        // 尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            //写http头信息
            if (headers != null && !headers.isEmpty()) {
                setHeader(con, headers);
            }


            outStream = con.getOutputStream();
            outStream.write(PostData);
            outStream.flush();
            outStream.close();

            // 读取返回内容
            inputStream = con.getInputStream();
            baos = new ByteArrayOutputStream();
            int i = -1;
            while ((i = inputStream.read()) != -1) {
                baos.write(i);
            }
            result = new String(baos.toByteArray(), "UTF-8");
        } finally {
            if (con != null) {
                con.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
            if (baos != null) {
                baos.close();
            }
        }
        return result;
    }


    public static void setHeader(HttpURLConnection con, Map headers) {
        Iterator iter = headers.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            con.setRequestProperty(key, (String) headers.get(key));
        }
    }

    public static String hashMapToJson(Map<String, Object> map) {
        String string = "{";
        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry e = (Map.Entry) it.next();
            string += "\"" + e.getKey() + "\":";
            string += "\"" + e.getValue() + "\",";
        }
        string = string.substring(0, string.lastIndexOf(","));
        string += "}";
        return string;
    }

}
