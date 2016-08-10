package com.bsy.webservice;

import javax.jws.WebService;

/**
 * Created by Laijie on 2016/8/9.
 */

@WebService
public interface TrafficService {
    String getTest(int id);
}
