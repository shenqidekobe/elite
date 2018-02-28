package com.ledao.elite.core.framework.plugin.pay.alipay.builder;


import org.springframework.util.StringUtils;

import com.google.gson.annotations.SerializedName;


public class AlipayTradeQueryCententBuilder  {
    // 支付宝交易号,和商户订单号不能同时为空, 如果同时存在则通过tradeNo查询支付宝交易
    @SerializedName("trade_no")
    private String tradeNo;

    // 商户订单号，通过此商户订单号查询当面付的交易状态
    @SerializedName("out_trade_no")
    private String outTradeNo;

   
    public boolean validate() {
        if (StringUtils.isEmpty(tradeNo) &&
                StringUtils.isEmpty(outTradeNo)) {
            throw new IllegalStateException("tradeNo and outTradeNo can not both be NULL!");
        }
        return true;
    }

  
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"trade_no:\"").append(tradeNo).append('\"');
        sb.append(",out_trade_no:\"").append(outTradeNo).append('\"');
        sb.append('}');
  
        return sb.toString();
    }
   
    public String getTradeNo() {
        return tradeNo;
    }

    public AlipayTradeQueryCententBuilder setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public AlipayTradeQueryCententBuilder setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }
}

