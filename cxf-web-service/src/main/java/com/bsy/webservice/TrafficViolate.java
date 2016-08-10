package com.bsy.webservice;

import javax.jws.WebService;

/**
 * Created by Laijie on 2016/8/9.
 *
 * 车辆违章查询接口
 */

@WebService
public interface TrafficViolate {
    /**
     * 下订单
     *
     * @param extNo 接入方订单号
     * @param type  5为扣分单，1为非扣分单
     * @param plateType 车类型
     * @param plateNo 车牌
     * @param vin 车辆识别码
     * @param engineNo 发动机号
     * @param violationTime 违章时间
     * @param behavior 违章行为
     * @param site 违章地点
     * @param score 扣分
     * @param fine 罚款
     * @param sequences 违章序号或决定书号
     * @param overdue 滞纳金
     * @param drivingLicence 驾驶证号
     * @param fileNo 驾驶证编号
     * @param phone 手机号
     * @return 详见接口说明文档
     */
    String order(String extNo, int type, int plateType, String plateNo, String vin, String engineNo,
                 String violationTime, String behavior, String site, String score, String fine,
                 String sequences, String overdue, String drivingLicence, String fileNo, String phone);

    /**
     * 提交订单
     *
     * @param extNo 订单号
     * @return 详见接口说明文档
     */
    String Submit(String extNo);

    /**
     * 查询订单状态
     *
     * @param extNo 订单号
     * @return 详见接口说明文档
     */
    String QueryOrder(String extNo);

    /**
     * 判断是否扣分
     *
     * @param cityId       城市代码
     * @param behaviorCode 违章行为代码
     * @return 详见接口说明文档
     */
    String Judge(String cityId, String behaviorCode);

    /**
     * 查询违章
     * @param plateNumber 车牌号码
     * @param plateType 车类型
     * @param vin 车辆识别码
     * @param engineNo 发动机号
     * @return 详见接口说明文档
     */
    String Pecc(String plateNumber, String plateType, String vin, String engineNo);
}
