/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.orderdetail.service.impl;

import com.wuliu.api.common.model.PageResultModel;
import com.wuliu.api.orderdetail.constant.WuliuOrderDetailConst;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;
import com.wuliu.api.orderdetail.service.WuliuOrderDetailService;
import com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO;


/**
 * 类WuliuOrderDetailServiceImpl.java的实现描述：TODO 类实现描述 
 * @author yunbin.wangyb 2016年12月29日 下午9:11:55
 */
public class WuliuOrderDetailServiceImpl implements WuliuOrderDetailService {

    private WuliuOrderDetailAO wuliuOrderDetailAO;
    
    /* (non-Javadoc)
     * @see com.wuliu.api.orderdetail.service.WuliuOrderDetailService#addOrderDetail(com.wuliu.api.orderdetail.model.WuliuOrderDetailModel)
     */
    @Override
    public WuliuOrderDetailModel addOrderDetail(WuliuOrderDetailModel wuliuOrderDetailModel) {
        return wuliuOrderDetailAO.addOrderDetail(wuliuOrderDetailModel);
    }

    /* (non-Javadoc)
     * @see com.wuliu.api.orderdetail.service.WuliuOrderDetailService#updateOrderDetail(com.wuliu.api.orderdetail.model.WuliuOrderDetailModel)
     */
    @Override
    public boolean updateOrderDetail(WuliuOrderDetailModel wuliuOrderDetailModel) {
        return wuliuOrderDetailAO.updateOrderDetail(wuliuOrderDetailModel);
    }

    /* (non-Javadoc)
     * @see com.wuliu.api.orderdetail.service.WuliuOrderDetailService#queryOrderDetails(com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam)
     */
    @Override
    public PageResultModel<WuliuOrderDetailModel> queryOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam) {
        
        if (wuliuOrderDetailQueryParam == null) {
            return null;
        }
        
        PageResultModel<WuliuOrderDetailModel> ret = new PageResultModel<WuliuOrderDetailModel>();
        ret.setPageSize(wuliuOrderDetailQueryParam.getPageSize());
        ret.setPageNum(wuliuOrderDetailQueryParam.getPageNum());
        ret.setResultList(wuliuOrderDetailAO.queryOrderDetails(wuliuOrderDetailQueryParam));
        ret.setTotalCount(wuliuOrderDetailAO.countOrderDetails(wuliuOrderDetailQueryParam));
        
        return ret;
    }

    /* (non-Javadoc)
     * @see com.wuliu.api.orderdetail.service.WuliuOrderDetailService#countOrderDetails(com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam)
     */
    @Override
    public int countOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam) {
        return wuliuOrderDetailAO.countOrderDetails(wuliuOrderDetailQueryParam);
    }

    /* (non-Javadoc)
     * @see com.wuliu.api.orderdetail.service.WuliuOrderDetailService#deleteOrderDetail(java.lang.Long)
     */
    @Override
    public boolean deleteOrderDetail(Long id) {
        WuliuOrderDetailModel wuliuOrderDetailModel = new WuliuOrderDetailModel();
        wuliuOrderDetailModel.setId(id);
        wuliuOrderDetailModel.setStatus(WuliuOrderDetailConst.STATUS_DISABLE);
        return wuliuOrderDetailAO.updateOrderDetail(wuliuOrderDetailModel);
    }

    
    public WuliuOrderDetailAO getWuliuOrderDetailAO() {
        return wuliuOrderDetailAO;
    }

    
    public void setWuliuOrderDetailAO(WuliuOrderDetailAO wuliuOrderDetailAO) {
        this.wuliuOrderDetailAO = wuliuOrderDetailAO;
    }

}
