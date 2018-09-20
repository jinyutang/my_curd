package com.hxkj.common.util.ws.server;

import com.hxkj.common.util.StringUtils;
import com.jfinal.kit.AesKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/**
 * 用户id 加密工具
 * @author chuang
 * @date 2018/9/10
 */
public class UserIdEncryptUtils {
    public final  static  String CURRENT_USER_ID_AESKEY;
    public final static   String OTHER_USERS_ID_AESKEY;
    static {
        Prop prop = PropKit.use("chat.properties");
        CURRENT_USER_ID_AESKEY = prop.get("currentUserIdAeskey");
        OTHER_USERS_ID_AESKEY = prop.get("otherUsersIdAeskey");
    }

    /**
     * 加密
     * @param str    未加密字符串
     * @param aesKey
     * @return
     */
    public static String encrypt(String str,String aesKey){
        byte[] bytes= AesKit.encrypt(str,aesKey);
        return StringUtils.bytesToHexString(bytes);
    }

    /**
     * 解密
     * @param str   已加密字符串
     * @param aesKey
     * @return
     */
    public static String decrypt(String str,String aesKey){
        byte[] bytes = StringUtils.hexStringToBytes(str);
        return AesKit.decryptToStr(bytes,aesKey);
    }
}
