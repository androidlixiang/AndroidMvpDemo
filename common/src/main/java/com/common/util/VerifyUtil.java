package com.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XJ on 2015/8/26.
 * 验证工具类
 */
public class VerifyUtil {
    /**
     * 邮箱验证
     *
     * @param target
     * @return
     */
    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * 检验手机号是否有效
     */
    public static boolean isMobileNO(String mobiles) {
        if (mobiles == null)
            return false;
        Pattern p = Pattern.compile("^1[3456789]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

//    /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/


    /**
     * 检验密码是否符合格式
     */
    public static boolean isPaw(String mobiles) {
        if (mobiles == null)
            return false;
//        Pattern p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{6,25}$");
        Pattern p = Pattern.compile("^[0-9a-zA-Z]{6,18}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    /**
     * 验证电话号码 ^[\\d+-]*$
     */
    public static boolean isPhone(String str) {
        Pattern p = Pattern.compile("^[\\d+-]*$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean verifyPassWord(String str) {

        Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,11}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 身份证的有效验证
     *
     * @param IDStr 身份证号
     * @return 有效：true 无效：false
     * @throws java.text.ParseException
     */
    @SuppressWarnings("unchecked")
    public static boolean IDCardValidate(String IDStr) {
        IDStr = IDStr.toLowerCase(Locale.CHINA);
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        // String[] Checker = {"1","9","8","7","6","5","4","3","2","1","1"};
        String Ai = "";

        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "号码长度应该为15位或18位。";
            System.out.println(errorInfo);
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            System.out.println(errorInfo);
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份

        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "生日无效。";
            System.out.println(errorInfo);
            return false;
        }

        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "生日不在有效范围。";
                System.out.println(errorInfo);
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "月份无效";
            System.out.println(errorInfo);
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "日期无效";
            System.out.println(errorInfo);
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable<String, String> h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "地区编码错误。";
            System.out.println(errorInfo);
            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，最后一位字母错误";
                System.out.println(errorInfo);
                return false;
            }
        } else {
            System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
            System.out.println("新身份证号:" + Ai);
            return true;
        }
        // =====================(end)=====================
        System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
        return true;
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        }
        return false;
        /*
         * 判断一个字符时候为数字 if(Character.isDigit(str.charAt(0))) { return true; }
         * else { return false; }
         */
    }

    /**
     * 判断字符串是否为日期格式
     *
     * @param
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        return m.matches();
    }

    /**
     * 设置地区编码
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static String verifyMessage(String value) {

        while (true) {
            String aa = simpleLetterAndNumCheck(value, 4);
            if (aa.equals(value)) {
                return aa;
            } else {
                value = aa;
            }
        }

    }

    /**
     * SM 校验字符串连续多少位是纯数字或者纯字母，默认4位(字母区分大小写)
     *
     * @param length 校验长度,默认4为
     * @return
     */
    private static String simpleLetterAndNumCheck(String value, int length) {
        int startValidate = -1;
        int i = 0;
        //计数器
        int counter = 1;
        //
        for (; i < value.length() - 1; ) {
            //当前ascii值
            int currentAscii = Integer.valueOf(value.charAt(i));
            //下一个ascii值
            int nextAscii = Integer.valueOf(value.charAt(i + 1));
            //满足区间进行判断
            if ((rangeInDefined(currentAscii, 48, 57))) {
                //计算两数之间差一位则为连续
                if (Math.abs(Math.abs((nextAscii - currentAscii))) < 10) {
                    //计数器++
                    counter++;
                    if (startValidate == -1) {
                        startValidate = i;
                    }
                } else {
                    //满足连续数字或者字母
                    if (counter >= length) {
                        String aaa = value.substring(startValidate, counter + startValidate);
                        value = value.replace(aaa, "****");
                        return value;
                    }
                    startValidate = -1;
                    //否则计数器重新计数
                    counter = 1;
                }
                if (i == value.length() - 2) {
                    if (counter >= length) {
                        String aaa = value.substring(startValidate, counter + startValidate);
                        value = value.replace(aaa, "****");
                        return value;
                    }

                }
            }

            i++;
        }


        return value;
    }

    /**
     * SM 判断一个数字是否在某个区间
     *
     * @param current 当前比对值
     * @param min     最小范围值
     * @param max     最大范围值
     * @return
     */
    public static boolean rangeInDefined(int current, int min, int max) {
        return Math.max(min, current) == Math.min(current, max);
    }
}
