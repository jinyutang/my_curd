package com.hxkj.common.util;


import com.hxkj.common.constant.Constant;
import com.jfinal.kit.AesKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;


/**
 * 字符串处理常用方法
 */
public abstract class StringUtils {
    private final static Logger LOG = Logger.getLogger(StringUtils.class);

    /**
     * double精度调整
     *
     * @param doubleValue 需要调整的值123.454
     * @param format      目标样式".##"
     * @return
     */
    public static String decimalFormat(double doubleValue, String format) {
        DecimalFormat myFormatter = new DecimalFormat(format);
        String formatValue = myFormatter.format(doubleValue);
        return formatValue;
    }

    /**
     * Url Base64编码
     *
     * @param data 待编码数据
     * @return String 编码数据
     * @throws Exception
     */
    public static String encodeBase64URLSafe(String data) {
        String str = null;
        try {
            byte[] b = Base64.encodeBase64URLSafe(data.getBytes(Constant.DEFAULT_ENCODEING));
            str = new String(b, Constant.DEFAULT_ENCODEING);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }
        return str;
    }

    /**
     * Base64解码
     *
     * @param data 待解码数据
     * @throws Exception
     */
    public static String decodeBase64(String data) {
        String str = null;
        try {
            byte[] b = Base64.decodeBase64(data.getBytes(Constant.DEFAULT_ENCODEING));
            str = new String(b, Constant.DEFAULT_ENCODEING);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }
        return str;
    }

    /**
     * URL编码
     *
     * @param source
     * @return
     */
    public static String urlEncode(String source) {
        String result = source;
        try {
            result = URLEncoder.encode(source, Constant.DEFAULT_ENCODEING);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * 获取 对象 的类名称（不带包名）
     *
     * @param bean
     * @return
     */
    public static String beanName(Object bean) {
        // 报名.类名
        String fullClassName = bean.getClass().getName();
        // 类名
        return fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
    }


    /**
     * 中文文件 下载编码，多浏览器适配
     *
     * @param request
     * @param fileName
     * @return
     */
    public static String encodeFileName(HttpServletRequest request, String fileName) {
        String userAgent = request.getHeader("User-Agent");
        try {
            String encodedFileName = URLEncoder.encode(fileName, "UTF8");
            if (userAgent == null) {
                return "filename=\"" + encodedFileName + "\"";
            } else {
                userAgent = userAgent.toLowerCase();
                if (userAgent.indexOf("msie") != -1) {
                    return "filename=\"" + encodedFileName + "\"";
                } else if (userAgent.indexOf("opera") != -1) {
                    return "filename*=UTF-8''" + encodedFileName;
                } else if (userAgent.indexOf("safari") == -1 && userAgent.indexOf("applewebkit") == -1 && userAgent.indexOf("chrome") == -1) {
                    return userAgent.indexOf("mozilla") != -1 ? "filename*=UTF-8''" + encodedFileName : "filename=\"" + encodedFileName + "\"";
                } else {
                    return "filename=\"" + new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1") + "\"";
                }
            }
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
            return "";
        }
    }

    /**
     * 汉字拼音全拼
     *
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        char[] t1;
        t1 = src.toCharArray();
        String[] t2;
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            LOG.error(e.getMessage(), e);
        }
        return t4;
    }

    /**
     * 汉字拼音首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }


    /**
     * html特殊字符 转义，避免XSS
     *
     * @param content
     * @return
     */
    public static String escapeHtml(String content) {
        return StringEscapeUtils.escapeHtml4(content);
    }

    /**
     * html特殊字符转义 反转义，避免XSS
     *
     * @param content
     * @return
     */
    public static String unescapeHtml(String content) {
        return StringEscapeUtils.unescapeHtml4(content);
    }

    /**
     * 富文本内容处理返回纯文本
     *
     * @param unsafe
     * @return
     */
    public static String cleanHtml(String unsafe) {
        String clear = Jsoup.clean(unsafe, Whitelist.simpleText());
        return clear;
    }

    /**
     * 富文本内容处理返回安全文本
     *
     * @param unsafe
     * @return
     */
    public static String safeHtml(String unsafe) {
        String safe = Jsoup.clean(unsafe, Whitelist.basic());
        return safe;
    }


    /**
     * xml 字符串 格式美化
     *
     * @param inputXML
     * @return
     */
    public static String formatXML(String inputXML) {
        String requestXML = null;
        Document document = null;

        try {
            SAXReader reader = new SAXReader();
            document = reader.read(new StringReader(inputXML));
        } catch (DocumentException e) {
            LOG.error(e.getMessage(), e);
        }

        if (document != null) {
            XMLWriter writer = null;
            try {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = new OutputFormat("    ", true);
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
        }

        return requestXML;
    }

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * byte[] to hex string
     * @param bytes
     * @return
     */
    public static String bytesToHexString(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for(byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

    /**
     * 将16进制字符串转换为byte[]
     * @param str
     * @return
     */
    public static byte[] hexStringToBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }


    private  final static String AES_KEY = PropKit.use("config.properties").get("aesKey");

    /**
     * 字符串先aes 加密为 byte[],再转 hex String
     * @return
     */
    public static String encryptAesHex(String str){
        return  bytesToHexString(AesKit.encrypt(str,AES_KEY));
    }

    /**
     * hex 字符串先转 byte[], 再aes 解密
     * @param str
     * @return
     */
    public static  String decryptAesHex(String str){
        String resultStr = null;
        try{
            resultStr = AesKit.decryptToStr(hexStringToBytes(str),AES_KEY);
        }catch (Exception e){
            LOG.error(e.getMessage(),e);
        }
        return resultStr;
    }

}
