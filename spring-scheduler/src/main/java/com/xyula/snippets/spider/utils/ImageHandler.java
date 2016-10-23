package com.xyula.snippets.spider.utils;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016-10-23.
 */
public class ImageHandler {

    public List<String> getImgSrcs(String htmlStr) {
        String img;
        Pattern p_image;
        Matcher m_image;
        List<String> pics = new ArrayList<String>();

        String regEx_img = "<img.*src=(.*?)[^>]*?>"; //ͼƬ���ӵ�ַ
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            img = m_image.group();
            Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //ƥ��src
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    public void download(String urlString, String filename, String savePath) throws Exception {
        // ����URL
        URL url = new URL(urlString);
        // ������
        URLConnection con = url.openConnection();
        //��������ʱΪ5s
        con.setConnectTimeout(5 * 1000);
        // ������
        InputStream is = con.getInputStream();

        // 1K�����ݻ���
        byte[] bs = new byte[1024];
        // ��ȡ�������ݳ���
        int len;
        // ������ļ���
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
        // ��ʼ��ȡ
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // ��ϣ��ر���������
        os.close();
        is.close();
    }

    public String upload(String filename, String path) {
        // todo �ϴ�ͼƬ

        return "http://img.skg.com/GI_9ca5c6f5-b4b2-49a8-86b2-3d87e25a421f.jpg";
    }

    public String replaceSrcs(String html, Map<String, String> maps) {
        String newHtml = html;
        for (String key : maps.keySet()) {
            newHtml = newHtml.replaceAll(key, maps.get(key));
        }

        return newHtml;
    }
}
