package com.hxkj.common.util;

import com.hxkj.common.util.ws.server.UserIdEncryptUtils;
import com.jfinal.kit.AesKit;
/**
 * @author chuang
 * @date 2018/9/3
 */
public class Main {
    public static void main(String[] args) {
        String txt = "zhangchuang";
        // aes key 字符串大于16字符(128位), 需要修改 jdk 下的 限制策略
        System.out.println(UserIdEncryptUtils.CURRENT_USER_ID_AESKEY);
        System.out.println(UserIdEncryptUtils.CURRENT_USER_ID_AESKEY.length());
        System.out.println(UserIdEncryptUtils.encrypt(txt,UserIdEncryptUtils.CURRENT_USER_ID_AESKEY));
        System.out.println(AesKit.genAesKey());
    }
}
