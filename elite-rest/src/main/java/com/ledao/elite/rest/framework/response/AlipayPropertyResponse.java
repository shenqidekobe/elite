package com.ledao.elite.rest.framework.response;

import lombok.Data;

@Data
public class AlipayPropertyResponse {
     private String appId;
     private String privatekey;
     private String partner;
     private String sellerId;
     private String alipayUrl;
     private String notifyUrl;
}
