package per.bmy.util;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class TranslationUtil {

    static String url = "https://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule";

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("i", " 城市形");
        jsonObject.put("from", " AUTO");
        jsonObject.put("to", " AUTO");
        jsonObject.put("smartresult", " dict");
        jsonObject.put("client", " fanyideskweb");
        jsonObject.put("salt", " 16317773924223");
        jsonObject.put("sign", " b24f8dacf97b14e263970ab9d9e541ad");
        jsonObject.put("lts", " 1631777392422");
        jsonObject.put("bv", " 89e18957825871c419be045180c67d3b");
        jsonObject.put("doctype", " json");
        jsonObject.put("version", " 2.1");
        jsonObject.put("keyfrom", " fanyi.web");
        jsonObject.put("action", " FY_BY_REALTlME");

        System.out.println(sendPost(url, jsonObject));
    }

    public static String sendPost(String url, JSONObject param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}