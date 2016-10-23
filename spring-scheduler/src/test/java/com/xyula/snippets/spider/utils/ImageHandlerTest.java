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
//                "                        <p>　　鸡蛋是天然食物中，富含大量的维生素和矿物质及有高生物价值的蛋白质。但是你知道吗?用错误的方法吃鸡蛋，这么好的营养品就会变成毒品!</p>\n" +
//                "<p><img class=\"conimg\" width=\"350\" height=\"300\" alt=\"鸡蛋6种最错误吃法会伤身nn.jpg\" src=\"http://images.meishij.net/p/20140911/6edf0615955dbeb7ed412e3549c62f9c.jpg\"></p>\n" +
//                "<p><strong>　　1.生吃</strong></p>\n" +
//                "<p>　　有些人觉得，食物一经煮熟，就会流失其营养价值。所以很多人喜欢生吃蔬菜、生吃海鲜。同样，有人认为生吃鸡蛋可以获取比熟鸡蛋更多的营养价值。</p>\n" +
//                "<p>　　但是，其实不然，生吃鸡蛋很可能会把鸡蛋中含有的细菌(例如大肠杆菌)吃进肚子去，造成肠胃不适并引起腹泻，严重的更可能导致死亡。并且，值得一说的是，鸡蛋的蛋白含有抗生物素蛋白，需要高温加热破坏，否则会影响食物中生物素的吸收，使身体出现食欲不振、全身无力、肌肉疼痛、皮肤发炎、脱眉等症状。</p>\n" +
//                "<p><strong>　　2.隔夜</strong></p>\n" +
//                "<p>　　鸡蛋其实是可以煮熟了之后，隔天再重新加热再吃的。但是，半生熟的鸡蛋，在隔夜了之后吃却不行!鸡蛋如果没有完全熟透，在保存不当的情形下容易滋生细菌，如造成肠胃不适、胀气等情形。</p>\n" +
//                "<p>　　同时，有的人认为鸡蛋煮越久越好，这也是错误的。因为鸡蛋煮的时间过长，蛋黄中的亚铁离子与蛋白中的硫离子化合生成难溶的硫化亚铁，很难被吸收。油煎鸡蛋过老，边缘会被烤焦，鸡蛋清所含的高分子蛋白质会变成低分子氨基酸，这种氨基酸在高温下常可形成对人体健康不利的化学物质。</p>\n" +
//                "<p><strong>　　3.过量</strong></p>\n" +
//                "<p>　　如大家所知，鸡蛋含有高蛋白，如果食用过多，可导致代谢产物增多，同时也增加肾脏的负担，造成肾脏机能的损伤。所以一般老年人每天吃1～2个鸡蛋为宜。中青年人、从事脑力劳动或轻体力劳动者，每天可吃2个鸡蛋;从事重体力劳动，消耗营养较多者，每天可吃2～3个鸡蛋;少年儿童由于长身体，代谢快，每天也应吃2～3个鸡蛋。</p>\n" +
//                "<p>　　孕妇、产妇、乳母、身体虚弱者以及进行大手术后恢复期的病人，需要多增加优良蛋白质，每天可吃3～4个鸡蛋，但不宜再多。</p>\n" +
//                "<p><strong>　　4.加糖、加豆浆</strong></p>\n" +
//                "<p>　　很多人喜欢在烹煮各种食物的时候将鸡蛋跟糖一起煮。其实鸡蛋与糖一起烹饪，二者之间会因高温作用生成一种叫糖基赖氨酸的物质，破坏了鸡蛋中对人体有益的氨基酸成分。值得注意的是，糖基赖氨酸有凝血作用，进入人体后会造成危害。所以应当等鸡制食物冷了之后再加入糖。</p>\n" +
//                "<p>　　另外有很多人喜欢在早餐的时候吃上一个鸡蛋一个面包，再加上一杯豆浆。其实大豆中含有的胰蛋白酶，与蛋清中的卵松蛋白相结合，会造成营养成分的损失，降低二者的营养价值。</p>\n" +
//                "<p><strong>　　5、空腹吃鸡蛋</strong></p>\n" +
//                "<p>　　空腹吃鸡蛋不是很好，空腹过量进食牛奶、豆浆、鸡蛋、肉类等蛋白质含量高的食品，蛋白质将\"被迫\"转化为热能消耗掉，起不到营养滋补作用。</p>\n" +
//                "<p>　　同时，在一个较短的时间内，蛋白质过量积聚在一起，蛋白质分解过程中会产生大量尿素、氨类等有害物质，不利于身体健康。</p>\n" +
//                "<p><strong>　　6.煎鸡蛋、茶叶蛋</strong></p>\n" +
//                "<p>　　有很多人喜欢吃煎鸡蛋，特别是边缘煎得金黄的那种，这个时候就要注意啦，因为被烤焦的边缘，鸡蛋清所含的高分子蛋白质会变成低分子氨基酸，这种氨基酸在高温下常可形成致癌的化学物质。另外，茶叶蛋也应少吃，一来是因为茶叶蛋反复的煎煮，其营养已经被破坏，另一方面就是在这个过程中茶叶中含酸化物质，与鸡蛋中的铁元素结合，对胃起刺激作用，影响胃肠的消化功能。</p>\n" +
//                "<p>　　看来，吃一个小小的鸡蛋所要注意的还真多，但是这都是些值得注意的tips。只要我们平时在吃的时候注意一点，就能够很好地吸收鸡蛋中有益的营养成分。</p>\n" +
//                "<p>　　来源：人民网</p>                    </div>\n" +
//                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span class=\"authors_copy_right\"><strong>鸡蛋6种最错误吃法会伤身的版权归作者所有，没有作者本人的书面许可任何人不得转载或使用其中整体或任何部分内容。</strong><a target=\"_blank\" title=\"打印鸡蛋6种最错误吃法会伤身\" href=\"http://www.meishi.cc/print.php?id=611465\">打印菜谱</a></span><div style=\"width:640px;padding:0px 15px 20px;background:#f5f5f5;overflow:hidden;\"><script>\n" +
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
                "\t\t\t\t\t\t\t     <div class=\"artText\" id=\"artText\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tbody><tr class=\"firstRow\"><td><!--文章内容--><p style=\"text-align: center;\"><a href=\"1601348_1.html\"><img alt=\"43\" src=\"http://img0.pclady.com.cn/pclady/1610/18/1601348_43.png\" img-height=\"450\" img-width=\"600\" img-size=\"425673\" title=\"43\"></a><span style=\"font-size: 14px;\">　　</span></p><p><span style=\"font-size: 14px;\">&nbsp; &nbsp; &nbsp; &nbsp; 屈指一数，《快乐大本营》也是陪伴各位亲故一路疯疯笑笑一路走来，何炅老师、娜娜，海涛，不知为什么，吴昕在这其中总是不那么显眼......但是!妞儿说美就美疯你，说时髦一下就去跑起了时装周，这样的反转魄力也是无人能及!</span></p><p style=\"text-align: center;\"><a href=\"1601348_1.html\"><img alt=\"422\" src=\"http://img0.pclady.com.cn/pclady/1610/18/1601348_6574676.jpg\" img-height=\"587\" img-width=\"500\" img-size=\"230199\" title=\"422\"></a></p><p><span style=\"font-size: 14px;\">　　吴昕的转变让人惊叹之余简直是从头到脚都想学一把~!(学一把都不够!)柔软如少女的肌肤搭配洋气又出挑的白金色头发，真是心脏少一点强大都无法承受!</span></p><p><span style=\"font-size: 14px;\">　　其实我们吴昕变美也不是一朝一夕的事情啦，日常她就与众多爱漂亮的妹砸一样，捣鼓脸蛋，也喜欢各种凹造型~~</span></p><p style=\"text-align: center;\"><a href=\"1601348_1.html\"><img alt=\"34\" src=\"http://img0.pclady.com.cn/pclady/1610/18/1601348_6446.jpg\" img-height=\"571\" img-width=\"500\" img-size=\"199156\" title=\"34\"></a></p><p><span style=\"font-size: 14px;\">　　看看时装周里的她，气场全开，这样二次元的浅发色要是肌肤状态差一点或不够白皙，简直就是HOLD不住，不过棒呆的是跟她气场完全相合，也是真的美。</span></p><p><!--文章内容end--></p></td></tr></tbody></table></div>\n" +
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
                // 路径为文件且不为空则进行删除
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
