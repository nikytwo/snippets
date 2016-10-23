package com.xyula.snippets.spider.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-10-23.
 */
public class ImageHandlerTest {

    @Test
    public void getImgSrcs() {
//        String html = "<div class=\"measure\">\n" +
//                "\t\t\t\t\t\t\t\t\t\t<div class=\"edit editwz\" data-plugin=\"keyword\">\n" +
//                "                        <p>������������Ȼʳ���У�����������ά���غͿ����ʼ��и������ֵ�ĵ����ʡ�������֪����?�ô���ķ����Լ�������ô�õ�Ӫ��Ʒ�ͻ��ɶ�Ʒ!</p>\n" +
//                "<p><img class=\"conimg\" width=\"350\" height=\"300\" alt=\"����6�������Է�������nn.jpg\" src=\"http://images.meishij.net/p/20140911/6edf0615955dbeb7ed412e3549c62f9c.jpg\"></p>\n" +
//                "<p><strong>����1.����</strong></p>\n" +
//                "<p>������Щ�˾��ã�ʳ��һ�����죬�ͻ���ʧ��Ӫ����ֵ�����Ժܶ���ϲ�������߲ˡ����Ժ��ʡ�ͬ����������Ϊ���Լ������Ի�ȡ���켦�������Ӫ����ֵ��</p>\n" +
//                "<p>�������ǣ���ʵ��Ȼ�����Լ����ܿ��ܻ�Ѽ����к��е�ϸ��(����󳦸˾�)�Խ�����ȥ����ɳ�θ���ʲ�����к�����صĸ����ܵ������������ң�ֵ��һ˵���ǣ������ĵ��׺��п������ص��ף���Ҫ���¼����ƻ��������Ӱ��ʳ���������ص����գ�ʹ�������ʳ������ȫ��������������ʹ��Ƥ�����ס���ü��֢״��</p>\n" +
//                "<p><strong>����2.��ҹ</strong></p>\n" +
//                "<p>����������ʵ�ǿ���������֮�󣬸��������¼����ٳԵġ����ǣ�������ļ������ڸ�ҹ��֮���ȴ����!�������û����ȫ��͸���ڱ��治������������������ϸ��������ɳ�θ���ʡ����������Ρ�</p>\n" +
//                "<p>����ͬʱ���е�����Ϊ������Խ��Խ�ã���Ҳ�Ǵ���ġ���Ϊ�������ʱ������������е����������뵰���е������ӻ����������ܵ������������ѱ����ա��ͼ弦�����ϣ���Ե�ᱻ�����������������ĸ߷��ӵ����ʻ��ɵͷ��Ӱ����ᣬ���ְ������ڸ����³����γɶ����彡�������Ļ�ѧ���ʡ�</p>\n" +
//                "<p><strong>����3.����</strong></p>\n" +
//                "<p>����������֪���������иߵ��ף����ʳ�ù��࣬�ɵ��´�л�������࣬ͬʱҲ��������ĸ��������������ܵ����ˡ�����һ��������ÿ���1��2������Ϊ�ˡ��������ˡ����������Ͷ����������Ͷ��ߣ�ÿ��ɳ�2������;�����������Ͷ�������Ӫ���϶��ߣ�ÿ��ɳ�2��3������;�����ͯ���ڳ����壬��л�죬ÿ��ҲӦ��2��3��������</p>\n" +
//                "<p>�����и�����������ĸ�������������Լ����д�������ָ��ڵĲ��ˣ���Ҫ���������������ʣ�ÿ��ɳ�3��4���������������ٶࡣ</p>\n" +
//                "<p><strong>����4.���ǡ��Ӷ���</strong></p>\n" +
//                "<p>�����ܶ���ϲ�����������ʳ���ʱ�򽫼�������һ������ʵ��������һ����⿣�����֮����������������һ�ֽ��ǻ�����������ʣ��ƻ��˼����ж���������İ�����ɷ֡�ֵ��ע����ǣ��ǻ�����������Ѫ���ã��������������Σ��������Ӧ���ȼ���ʳ������֮���ټ����ǡ�</p>\n" +
//                "<p>���������кܶ���ϲ������͵�ʱ�����һ������һ��������ټ���һ����������ʵ���к��е��ȵ���ø���뵰���е����ɵ������ϣ������Ӫ���ɷֵ���ʧ�����Ͷ��ߵ�Ӫ����ֵ��</p>\n" +
//                "<p><strong>����5���ո��Լ���</strong></p>\n" +
//                "<p>�����ո��Լ������Ǻܺã��ո�������ʳţ�̡�����������������ȵ����ʺ����ߵ�ʳƷ�������ʽ�\"����\"ת��Ϊ�������ĵ����𲻵�Ӫ���̲����á�</p>\n" +
//                "<p>����ͬʱ����һ���϶̵�ʱ���ڣ������ʹ���������һ�𣬵����ʷֽ�����л�����������ء�������к����ʣ����������彡����</p>\n" +
//                "<p><strong>����6.�弦������Ҷ��</strong></p>\n" +
//                "<p>�����кܶ���ϲ���Լ弦�����ر��Ǳ�Ե��ý�Ƶ����֣����ʱ���Ҫע��������Ϊ�������ı�Ե�������������ĸ߷��ӵ����ʻ��ɵͷ��Ӱ����ᣬ���ְ������ڸ����³����γ��°��Ļ�ѧ���ʡ����⣬��Ҷ��ҲӦ�ٳԣ�һ������Ϊ��Ҷ�������ļ�����Ӫ���Ѿ����ƻ�����һ�����������������в�Ҷ�к��ữ���ʣ��뼦���е���Ԫ�ؽ�ϣ���θ��̼����ã�Ӱ��θ�����������ܡ�</p>\n" +
//                "<p>������������һ��СС�ļ�����Ҫע��Ļ���࣬�����ⶼ��Щֵ��ע���tips��ֻҪ����ƽʱ�ڳԵ�ʱ��ע��һ�㣬���ܹ��ܺõ����ռ����������Ӫ���ɷ֡�</p>\n" +
//                "<p>������Դ��������</p>                    </div>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"authors_copy_right\"><strong>����6�������Է�������İ�Ȩ���������У�û�����߱��˵���������κ��˲���ת�ػ�ʹ������������κβ������ݡ�</strong><a target=\"_blank\" title=\"��ӡ����6�������Է�������\" href=\"http://www.meishi.cc/print.php?id=611465\">��ӡ����</a></span><div style=\"width:640px;padding:0px 15px 20px;background:#f5f5f5;overflow:hidden;\"><script>\n" +
//                "\t\t\t\t\t(function() {\n" +
//                "\t\t\t\t\t\tvar s = \"_\" + Math.random().toString(36).slice(2);\n" +
//                "\t\t\t\t\t\tdocument.write('<div id=\"' + s + '\"></div>');\n" +
//                "\t\t\t\t\t\t(window.slotbydup=window.slotbydup || []).push({\n" +
//                "\t\t\t\t\t\t\tid: '437173',\n" +
//                "\t\t\t\t\t\t\tcontainer: s,\n" +
//                "\t\t\t\t\t\t\tsize: '642,90',\n" +
//                "\t\t\t\t\t\t\tdisplay: 'inlay-fix'\n" +
//                "\t\t\t\t\t\t});\n" +
//                "\t\t\t\t\t})();\n" +
//                "</script><div id=\"_e7xarb4co7y9beucua8j8aor\"></div><script charset=\"utf-8\" src=\"http://pos.baidu.com/dcam?di=437173&amp;dri=0&amp;dis=0&amp;dai=2&amp;ps=2292x310&amp;dcb=BAIDU_SSP_define&amp;dtm=SSP_JSONP&amp;dvi=0.0&amp;dci=-1&amp;dpt=none&amp;tsr=0&amp;tpr=1477206097225&amp;ti=%E9%B8%A1%E8%9B%8B6%E7%A7%8D%E6%9C%80%E9%94%99%E8%AF%AF%E5%90%83%E6%B3%95%E4%BC%9A%E4%BC%A4%E8%BA%AB_%E9%A5%AE%E9%A3%9F%E5%B0%8F%E5%B8%B8%E8%AF%86%20-%20%E7%BE%8E%E9%A3%9F%E6%9D%B0&amp;ari=2&amp;dbv=2&amp;drs=1&amp;pcs=1580x782&amp;pss=1580x2332&amp;cfv=0&amp;cpl=5&amp;chi=1&amp;cce=true&amp;cec=UTF-8&amp;tlm=1477206097&amp;rw=782&amp;ltu=http%3A%2F%2Fwww.meishij.net%2Fchangshi%2Fjidan6zhongzuicuowuchifahuishangshen.html&amp;ltr=http%3A%2F%2Fwww.meishij.net%2Fjiankang%2Fjinji%2F&amp;ecd=1&amp;psr=1600x900&amp;par=1600x848&amp;pis=-1x-1&amp;ccd=24&amp;cja=false&amp;cmi=7&amp;col=zh-CN&amp;cdo=-1&amp;tcn=1477206097\"></script><script type=\"text/javascript\">\n" +
//                "        document.write('<a style=\"display:none!important\" id=\"tanx-a-mm_10008787_2640448_43896251\"></a>');\n" +
//                "        tanx_s = document.createElement(\"script\");\n" +
//                "        tanx_s.type = \"text/javascript\";\n" +
//                "        tanx_s.charset = \"gbk\";\n" +
//                "        tanx_s.id = \"tanx-s-mm_10008787_2640448_43896251\";\n" +
//                "        tanx_s.async = true;\n" +
//                "        tanx_s.src = \"http://p.tanx.com/ex?i=mm_10008787_2640448_43896251\";\n" +
//                "        tanx_h = document.getElementsByTagName(\"head\")[0];\n" +
//                "        if(tanx_h)tanx_h.insertBefore(tanx_s,tanx_h.firstChild);\n" +
//                "</script><ins style=\"display:inline-block;padding:0;margin:0;width:640px;height:90px;*zoom:1;*display:inline; position:relative;\" id=\"tanxssp-outer-conmm_10008787_2640448_43896251\"><div id=\"tanxssp_con_mm_10008787_2640448_43896251\" style=\"overflow:hidden;display:inline-block;position:relative;width:640px;height:90px;*display:inline;*zoom:1;font:12px/1.5 tahoma,'Hiragino Sans GB','microsoft yahei',sans-serif;\"><iframe id=\"tanxssp-tuwen-iframemm_10008787_2640448_43896251\" src=\"http://strip.taobaocdn.com/tfscom/TB1rGyxGVXXXXXUXpXXO04pFXXX.html?tanxdspv=http%3a%2f%2frdstat.tanx.com%2ftrd%3ff%3d%26k%3da09e279ad7f7a12a%26p%3dmm_10008787_2640448_43896251%26pvid%3d0bfbf7c70000580c5fc831d800efc6a4%26s%3d640x90%26d%3d0%26t%3d1477205960&amp;pid=mm_10008787_2640448_43896251&amp;tp=5&amp;tsid=0bfbf7c70000580c5fc831d800efc6a4&amp;pid=mm_10008787_2640448_43896251\" style=\"width:640px;height:90px\" border=\"0\" frameborder=\"0\" marginwidth=\"0\" marginheight=\"0\" scrolling=\"no\" allowtransparency=\"true\"></iframe><a id=\"sxmm_10008787_2640448_43896251\" href=\"javascript:;\" style=\"width:20px;height:13px;right:0px;bottom:0px;display:block;position:absolute;cursor:pointer;z-index:250;\">   <span id=\"sx1mm_10008787_2640448_43896251\" style=\"float:none;width:20px;display:block;height:13px;\"><img src=\"//atanx.alicdn.com/t/img/TB1tWvVJFXXXXc_aXXXXXXXXXXX-40-26.png\" style=\"width:20px;height:13px;margin:auto;display:block;\" border=\"0/\"></span><div id=\"sxtipmm_10008787_2640448_43896251\" style=\"display:none;position:absolute;left:-38px;bottom:13px;\"><img src=\"//atanx.alicdn.com/t/img/TB1upAiJXXXXXa5aXXXXXXXXXXX-116-30.png\" style=\"width:58px;height:15px;margin:auto;display:block;\" border=\"0\"></div></a></div></ins><a style=\"display:none!important\" id=\"tanx-a-mm_10008787_2640448_43896251\"></a></div>\n" +
//                "\t\t\t\t</div>";
        String html = "<div class=\"product_article_main\">\n" +
                "\t\t\t\t\t\t\t     <div class=\"artText\" id=\"artText\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr class=\"firstRow\"><td><!--��������--><p style=\"text-align: center;\"><a href=\"1601348_1.html\"><img alt=\"43\" src=\"http://img0.pclady.com.cn/pclady/1610/18/1601348_43.png\" img-height=\"450\" img-width=\"600\" img-size=\"425673\" title=\"43\"></a><span style=\"font-size: 14px;\">����</span></p><p><span style=\"font-size: 14px;\">&nbsp; &nbsp; &nbsp; &nbsp; ��ָһ���������ִ�Ӫ��Ҳ������λ�׹�һ·���ЦЦһ·������������ʦ�����ȣ����Σ���֪Ϊʲô����������������ǲ���ô����......����!椶�˵���������㣬˵ʱ��һ�¾�ȥ������ʱװ�ܣ������ķ�ת����Ҳ�������ܼ�!</span></p><p style=\"text-align: center;\"><a href=\"1601348_1.html\"><img alt=\"422\" src=\"http://img0.pclady.com.cn/pclady/1610/18/1601348_6574676.jpg\" img-height=\"587\" img-width=\"500\" img-size=\"230199\" title=\"422\"></a></p><p><span style=\"font-size: 14px;\">������꿵�ת�����˾�֮̾���ֱ�Ǵ�ͷ���Ŷ���ѧһ��~!(ѧһ�Ѷ�����!)��������Ů�ļ������������ֳ����İ׽�ɫͷ��������������һ��ǿ���޷�����!</span></p><p><span style=\"font-size: 14px;\">������ʵ������꿱���Ҳ����һ��һϦ�����������ճ��������ڶమƯ��������һ��������������Ҳϲ�����ְ�����~~</span></p><p style=\"text-align: center;\"><a href=\"1601348_1.html\"><img alt=\"34\" src=\"http://img0.pclady.com.cn/pclady/1610/18/1601348_6446.jpg\" img-height=\"571\" img-width=\"500\" img-size=\"199156\" title=\"34\"></a></p><p><span style=\"font-size: 14px;\">��������ʱװ�������������ȫ������������Ԫ��ǳ��ɫҪ�Ǽ���״̬��һ��򲻹���𪣬��ֱ����HOLD��ס�������������Ǹ���������ȫ��ϣ�Ҳ���������</span></p><p><!--��������end--></p></td></tr></tbody></table></div>\n" +
                "\t\t\t\t\t\t\t    \t\t</div>";
        ImageHandler imageHandler = new ImageHandler();
        List<String> srcs = imageHandler.getImgSrcs(html);
        Map<String, String> srcMaps = new HashMap<String, String>();
        for (String src : srcs) {
            System.out.println(src);
            try {
                imageHandler.download(src, "test.jpg", "temp");
                String newSrc = imageHandler.upload("test.jpg", "temp");
                srcMaps.put(src, newSrc);
                File file = new File("temp\\test.jpg");
                // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
                if (file.isFile() && file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String newHtml = imageHandler.replaceSrcs(html, srcMaps);

        System.out.println(newHtml);
        Assert.assertEquals(0, srcs.size());


    }
}
