package com.ledao.elite.core.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * web utils
 */
@UtilityClass
public class WebUtils {

    /**
     * 获取请求的域名
     * @param request
     * @return
     */
    public static String getDomain(HttpServletRequest request){
        String domain = request.getServerName();
        int port = request.getServerPort();
        if(port != 80){
            domain += ":" + port;
        }
        return domain;
    }

    /**
     * 判断是否为ajax请求
     *
     * @param request 请求
     * @return 是否为ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        if (request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        }
        return false;
    }

    /**
     * 获取访问IP
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

}
