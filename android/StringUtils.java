package com.example.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;

public class StringUtils {
    
    /**
     * Pseudo-random number generator object for use with randomString(). The
     * Random class is not considered to be cryptographically secure, so only
     * use these random Strings for low to medium security applications.
     */
    private static Random sRandGen = new Random();

    /**
     * Array of numbers and letters. Numbers appear in the list
     * twice so that there is a more equal chance that a number will be picked.
     * We can use the array to get a random number or letter by picking a random
     * array index.
     */
    private static char[] sNumbersAndLetters =
        ("0123456789abcdefghijklmnopqrstuvwxyz0123456789").toCharArray();

    /**
     * Array of numbers.
     */
    private static char[] sNumbers = ("0123456789").toCharArray();

    /**
     * This class cannot be instantiated
     */
    private StringUtils() {
    }
    
    /**
     * Returns a random String of numbers and letters (lower and upper case) of
     * the specified length. The method uses the Random class that is built-in
     * to Java which is suitable for low to medium grade security uses. This
     * means that the output is only pseudo random, i.e., each number is
     * mathematically generated so is not truly random.
     * <p>
     * The specified length must be at least one. If not, the method will return null.
     *
     * @param length the desired length of the random String to return.
     * @return a random String of numbers and letters of the specified length.
     */
    public static String randomString(int length) {
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        final char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = sNumbersAndLetters[sRandGen.nextInt(sNumbersAndLetters.length - 1)];
        }

        return new String(randBuffer);
    }
    
    /**
     * Returns a random String of numbers of the specified length.
     * This means that the output is only pseudo random, i.e., each number is
     * mathematically generated so is not truly random.
     * <p>
     * The specified length must be at least one. If not, the method will return null.
     *
     * @param length the desired length of the random String to return.
     * @return a random String of numbers of the specified length.
     */
    public static String randomStringOfNumbers(int length) {
        if (length < 1) {
            return null;
        }
        // Create a char buffer to put random letters and numbers in.
        final char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = sNumbers[sRandGen.nextInt(sNumbers.length - 1)];
        }
        return new String(randBuffer);
    }
    
    /**
     * Get a readable string displaying the time
     *
     * @param context The context (needed only for relative time)
     * @param time The time
     *
     * @return The time string
     */
    public static String getTimestampAsString(Context context, long time) {
        final long hours = time / 3600000;
        time %= 3600000;
        final long mins = time / 60000;
        time %= 60000;
        final long sec = time / 1000;
        time %= 1000;
        time /= 100;
        return String.format("%02d:%02d:%02d.%01d", hours, mins, sec, time);
    }

    /**
     * Get a readable string displaying the time
     *
     * @param context The context (needed only for relative time)
     * @param time The time
     *
     * @return The time string
     */
    public static String getSimpleTimestampAsString(Context context, long time) {
        final long hours = time / 3600000;
        time %= 3600000;
        final long mins = time / 60000;
        time %= 60000;
        final long sec = time / 1000;
        return String.format("%02d:%02d:%02d", hours, mins, sec);
    }

    /**
     * 判断用户名，英文诗2-24位，中文是1-8位。
     * @param userName
     * @return
     */
    public static boolean checkUserName(String userName) {
        String regex = "^([a-zA-Z]{2,24})|([\\u4e00-\\u9fa5]{1,8})$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(userName);
        return m.matches();
    }
    
    /**
     * 一个用户注册功能的密码有如下要求：由数字和字母组成，
     * 并且要同时含有数字和字母，且长度要在8-16位之间。
     * 参考url：http://blog.sina.com.cn/s/blog_5082f7b901015r2r.html
     * @param pwd
     * @return
     */
    public static boolean checkPassword(String pwd){
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";    
        
        /**
        //test code
        String value = "aaa";  // 长度不够
        System.out.println(value.matches(regex));
 
        value = "1111aaaa1111aaaaa";  // 太长
        System.out.println(value.matches(regex));
 
        value = "111111111"; // 纯数字
        System.out.println(value.matches(regex));
 
        value = "aaaaaaaaa"; // 纯字母
        System.out.println(value.matches(regex));
 
        value = "####@@@@#"; // 特殊字符
        System.out.println(value.matches(regex));
 
        value = "1111aaaa";  // 数字字母组合
        System.out.println(value.matches(regex));
 
        value = "aaaa1111"; // 数字字母组合
        System.out.println(value.matches(regex));
 
        value = "aa1111aa";    // 数字字母组合
        System.out.println(value.matches(regex));
 
        value = "11aaaa11";    // 数字字母组合
        System.out.println(value.matches(regex));
 
        value = "aa11aa11"; // 数字字母组合
        System.out.println(value.matches(regex));
        **/
        
        return true;
    }
    
    /**
     * 判断是否是邮箱
     * @param param
     * @return
     */
    public static boolean isEmailValid(String param)
    {
        if(param == null)
            return false;
        if(param.length() <= 0)
            return false;
        boolean isValid = false;
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(param);
        isValid = m.matches();
        return isValid;
    } 
    
    /**
     * 判断手机号码
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null || TextUtils.isEmpty(phoneNumber)) return false;
        boolean isValid = false;

        String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        CharSequence inputStr = phoneNumber;

        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }
    
    /**
     * Trim text to a maximum size
     *
     * @param text The text
     * @param p The paint
     * @param maxSize The maximum size
     *
     * @return The text
     */
    public static String trimText(String text, Paint p, int maxSize) {
        if (text == null || "".equals(text)) {//avoid ... if text is "" or null
            return "";
        }

        final int textSize = (int)p.measureText(text);
        if (textSize > maxSize) {
            final int chars = p.breakText(text, true, maxSize - 12, null);
            text = text.substring(0, chars);
            text += "...";
        }

        return text;
    }
    
    /**
     * capitalize first letter
     * 
     * <pre>
     * capitalizeFirstLetter(null)     =   null;
     * capitalizeFirstLetter("")       =   "";
     * capitalizeFirstLetter("2ab")    =   "2ab"
     * capitalizeFirstLetter("a")      =   "A"
     * capitalizeFirstLetter("ab")     =   "Ab"
     * capitalizeFirstLetter("Abc")    =   "Abc"
     * </pre>
     * 
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str : new StringBuilder(str.length())
                .append(Character.toUpperCase(c)).append(str.substring(1)).toString();
    }

    /**
     * encoded in utf-8
     * 
     * <pre>
     * utf8Encode(null)        =   null
     * utf8Encode("")          =   "";
     * utf8Encode("aa")        =   "aa";
     * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     * 
     * @param str
     * @return
     * @throws UnsupportedEncodingException if an error occurs
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * encoded in utf-8, if exception, return defultReturn
     * 
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * get innerHtml from href
     * 
     * <pre>
     * getHrefInnerHtml(null)                                  = ""
     * getHrefInnerHtml("")                                    = ""
     * getHrefInnerHtml("mp3")                                 = "mp3";
     * getHrefInnerHtml("&lt;a innerHtml&lt;/a&gt;")                    = "&lt;a innerHtml&lt;/a&gt;";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com"&gt;innerHtml&lt;/a&gt;")               = "innerHtml";
     * getHrefInnerHtml("&lt;a href="baidu.com" title="baidu"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
     * getHrefInnerHtml("   &lt;a&gt;innerHtml&lt;/a&gt;  ")                           = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                      = "innerHtml";
     * getHrefInnerHtml("jack&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                  = "innerHtml";
     * getHrefInnerHtml("&lt;a&gt;innerHtml1&lt;/a&gt;&lt;a&gt;innerHtml2&lt;/a&gt;")        = "innerHtml2";
     * </pre>
     * 
     * @param href
     * @return <ul>
     *         <li>if href is null, return ""</li>
     *         <li>if not match regx, return source</li>
     *         <li>return the last string that match regx</li>
     *         </ul>
     */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

/**
     * process special char in html
     * 
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     * 
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString(String source) {
        return StringUtils.isEmpty(source) ? source : source.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    /**
     * transform half width char to full width char
     * 
     * <pre>
     * fullWidthToHalfWidth(null) = null;
     * fullWidthToHalfWidth("") = "";
     * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
     * fullWidthToHalfWidth("！＂＃＄％＆) = "!\"#$%&";
     * </pre>
     * 
     * @param s
     * @return
     */
    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char)(source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * transform full width char to half width char
     * 
     * <pre>
     * halfWidthToFullWidth(null) = null;
     * halfWidthToFullWidth("") = "";
     * halfWidthToFullWidth(" ") = new String(new char[] {12288});
     * halfWidthToFullWidth("!\"#$%&) = "！＂＃＄％＆";
     * </pre>
     * 
     * @param s
     * @return
     */
    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char)12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char)(source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }
    
    /**
     * is null or its length is 0 or it is made by space
     * 
     * <pre>
     * isBlank(null) = true;
     * isBlank(&quot;&quot;) = true;
     * isBlank(&quot;  &quot;) = true;
     * isBlank(&quot;a&quot;) = false;
     * isBlank(&quot;a &quot;) = false;
     * isBlank(&quot; a&quot;) = false;
     * isBlank(&quot;a b&quot;) = false;
     * </pre>
     * 
     * @param str
     * @return if string is null or its size is 0 or it is made by space, return true, else return false.
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * is null or its length is 0
     * 
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
     * </pre>
     * 
     * @param str
     * @return if string is null or its size is 0, return true, else return false.
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    /**
     * null string to empty string
     * 
     * <pre>
     * nullStrToEmpty(null) = &quot;&quot;;
     * nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
     * nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
     * </pre>
     * 
     * @param str
     * @return
     */
    public static String nullStrToEmpty(String str) {
        return (str == null ? "" : str);
    }
    
    //http://blog.csdn.net/h7870181/article/details/8480452
    public static String[] getIpAndLocation(){
        StringBuffer strForeignIP =new StringBuffer("strForeignIPUnkown");  
        StringBuffer strLocation =new StringBuffer("strLocationUnkown");
        try {  
            
            URL url = new URL("http://www.cz88.net/ip/viewip778.aspx");  
   
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gb2312"));  
   
            String s = "";  
            StringBuffer sb = new StringBuffer("");  
            while ((s = br.readLine()) != null) {  
             sb.append(s + "\r\n");  
            }  
            br.close();  
              
            String webContent = "";  
            webContent = sb.toString();  
              
            if( webContent.equals(null)|| webContent.equals("") ) return null;  
             
              
              
            String flagofForeignIPString ="IPMessage";  
            int startIP = webContent.indexOf(flagofForeignIPString)+flagofForeignIPString.length()+2;  
            int endIP = webContent.indexOf("</span>",startIP);  
            strForeignIP.delete(0, webContent.length());  
            strForeignIP.append(webContent.substring(startIP,endIP));  
            
            String flagofLocationString ="AddrMessage";  
            int startLoc = webContent.indexOf(flagofLocationString)+flagofLocationString.length()+2;  
            int endLoc = webContent.indexOf("</span>",startLoc);  
            strLocation.delete(0, webContent.length());  
            strLocation.append(webContent.substring(startLoc,endLoc)); 
            return new String[]{String.valueOf(strForeignIP), getCity(String.valueOf(strLocation))};

        } catch (Exception e) {  
            //e.printStackTrace();  
        }
        return null;
    }

    public static String getCity(String address){
        if (address.startsWith("北")||address.startsWith("上")||address.startsWith("重")){
            return address.substring(0,address.indexOf("市"));
            }
        if(address.startsWith("香")){
            return address.substring(0,address.indexOf("港"));
            }
        if(address.startsWith("澳")){
            return address.substring(0,address.indexOf("门"));
            }
        if (address.indexOf("省") != -1) {
            return address.substring(address.indexOf("省") + 1, address.indexOf("市"));
        }
        return null;
    }
}
