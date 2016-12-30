/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.wuliu.biz.orderdetail.AO.impl;

import java.util.List;

import com.wuliu.api.orderdetail.constant.WuliuOrderDetailConst;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailModel;
import com.wuliu.api.orderdetail.model.WuliuOrderDetailQueryParam;
import com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO;
import com.wuliu.biz.util.WuliuOrderDetailUtil;
import com.wuliu.dao.WuliuOrderDetailDAO;
import com.wuliu.dao.dataobject.WuliuOrderDetailDO;

/**
 * 类WuliuOrderDetailAOImpl.java的实现描述：TODO 类实现描述
 * 
 * @author yunbin.wangyb 2016年12月29日 下午2:17:29
 */
public class WuliuOrderDetailAOImpl implements WuliuOrderDetailAO {

    private WuliuOrderDetailDAO wuliuOrderDetailDAO;

    /*
     * (non-Javadoc)
     * @see
     * com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO#addOrderDetail(com.wuliu.api.orderdetail.model.WuliuOrderDetailModel
     * )
     */
    @Override
    public WuliuOrderDetailModel addOrderDetail(WuliuOrderDetailModel wuliuOrderDetailModel) {
        WuliuOrderDetailDO wuliuOrderDetailDO = WuliuOrderDetailUtil.convertToWuliuOrderDetailDO(wuliuOrderDetailModel);
        WuliuOrderDetailDO newWuliuOrderDetailDO = wuliuOrderDetailDAO.addOrderDetail(wuliuOrderDetailDO);
        return WuliuOrderDetailUtil.convertToWuliuOrderDetailModel(newWuliuOrderDetailDO);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO#updateOrderDetail(com.wuliu.api.orderdetail.model.
     * WuliuOrderDetailModel)
     */
    @Override
    public boolean updateOrderDetail(WuliuOrderDetailModel wuliuOrderDetailModel) {
        WuliuOrderDetailDO wuliuOrderDetailDO = WuliuOrderDetailUtil.convertToWuliuOrderDetailDO(wuliuOrderDetailModel);
        return wuliuOrderDetailDAO.updateOrderDetail(wuliuOrderDetailDO);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO#queryOrderDetails(com.wuliu.api.orderdetail.model.
     * WuliuOrderDetailQueryParam)
     */
    @Override
    public List<WuliuOrderDetailModel> queryOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam) {
        if (wuliuOrderDetailQueryParam.getStatus() == null) {
            wuliuOrderDetailQueryParam.setStatus(WuliuOrderDetailConst.STATUS_ENABLE);
        }
        
        List<WuliuOrderDetailDO> wuliuOrderDetailDOs = wuliuOrderDetailDAO.queryOrderDetails(wuliuOrderDetailQueryParam);
        
        return WuliuOrderDetailUtil.convertToWuliuOrderDetailModelList(wuliuOrderDetailDOs);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO#countOrderDetails(com.wuliu.api.orderdetail.model.
     * WuliuOrderDetailQueryParam)
     */
    @Override
    public int countOrderDetails(WuliuOrderDetailQueryParam wuliuOrderDetailQueryParam) {
        
        if (wuliuOrderDetailQueryParam.getStatus() == null) {
            wuliuOrderDetailQueryParam.setStatus(WuliuOrderDetailConst.STATUS_ENABLE);
        }
        
        return wuliuOrderDetailDAO.countOrderDetails(wuliuOrderDetailQueryParam);
    }

    /*
     * (non-Javadoc)
     * @see com.wuliu.biz.orderdetail.AO.WuliuOrderDetailAO#deleteOrderDetail(java.lang.Long)
     */
    @Override
    public boolean deleteOrderDetail(Long id) {
        WuliuOrderDetailModel wuliuOrderDetailModel = new WuliuOrderDetailModel();
        wuliuOrderDetailModel.setId(id);
        wuliuOrderDetailModel.setStatus(WuliuOrderDetailConst.STATUS_DISABLE);
        return updateOrderDetail(wuliuOrderDetailModel);
    }

    public WuliuOrderDetailDAO getWuliuOrderDetailDAO() {
        return wuliuOrderDetailDAO;
    }

    public void setWuliuOrderDetailDAO(WuliuOrderDetailDAO wuliuOrderDetailDAO) {
        this.wuliuOrderDetailDAO = wuliuOrderDetailDAO;
    }
}
