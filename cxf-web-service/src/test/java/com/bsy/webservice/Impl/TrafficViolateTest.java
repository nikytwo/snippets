package com.bsy.webservice.Impl;

import com.bsy.webservice.TrafficViolate;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Laijie on 2016/8/10.
 *
 * api 测试
 */
public class TrafficViolateTest {
    @Test
    public void Pecc(){
        TrafficViolate violate = TrafficViolateImpl.instance();
        String result = violate.Pecc("粤AS8T03", "2", "074686", "D15140");

        Assert.assertEquals("{\"message\":\"查询违章成功\",\"result\":0,\"data\":[],\"rowCount\":0}", result);
    }
}
