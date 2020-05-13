/**
 * CopyRright @2014 SIM TECHNOLOGY ALL RIGHTS RESERVED
 * @Title: RestResponse.java
 * @Package cm.sim.com.core.rest
 * @Description: TODO
 * @author he.sun 
 * @date 2015年3月18日 下午12:14:33
 * @version V1.0
 */

package com.chuangmei.web.utils;

import java.io.Serializable;

/**
 * @ClassName: RestResponse
 * @Description: TODO>
 * @author he.sun
 * @date 2015年3月18日 下午12:14:33
 * 
 */
public class RestResponse<T> implements Serializable {

    /**
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = -3720728948596761669L;
    /**
     * @Fields STATUS_OK : 表示成功状态
     */
    public static final int STATUS_OK = 1;

    /**
     * @Fields STATUS_ERROR : 表示失败状态
     */
    public static final int STATUS_ERROR = 0;

    /**
     * @Fields data : 数据体
     */
    private T data;
    /**
     * @Fields code : 返回代码
     */
    private String msg;
    /**
     * @Fields status : 状态，1:失败，0：成功
     */
    private int status;

    /**
     * @Title：ok
     * @Description: 返回正确状态
     * @author he.sun
     * @date 2015年3月18日 下午1:49:37
     * @param data
     * @return
     */
    public static <T> RestResponse<T> ok(T data){
        RestResponse<T> rp = status(RestResponse.STATUS_OK);
        rp.setData(data);
        return rp;
    }

    /**
     * @Title：error
     * @Description: 返回错误，添加错误代码
     * @author he.sun
     * @date 2015年3月18日 下午1:49:53
     * @param code
     * @return
     */
    public static <T> RestResponse<T> error(String msg){
        RestResponse<T> rp = status(RestResponse.STATUS_ERROR);
        rp.setMsg(msg);
        return rp;
    }

    /**
     * @Title：status
     * @Description: 设置一个新的返回的状态
     * @author he.sun
     * @date 2015年3月18日 下午1:50:12
     * @param status
     * @return
     */
    public static <T> RestResponse<T> status(int status){
        RestResponse<T> rp = new RestResponse<T>();
        rp.setStatus(status);
        return rp;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RestResponse [data=" + data + ", msg=" + msg + ", status="
				+ status + "]";
	}
}
