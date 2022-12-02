package com.alidaodao.web.utlis;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Objects;
import java.util.Random;

public class NumberTool {
    public static final BigDecimal RATE_USD = BigDecimal.valueOf(6.8);

    static Random random = new Random();
    /**
     * 本地格式化的解析器
     */
    static NumberFormat numberFormat = NumberFormat.getNumberInstance();
    /**
     * 验证码的数据范围
     */
    static String str = "abcedfghijklmnopqrstuvwxyz1234567890";

    /**
     * 生成随机邮件验证码
     *
     * @param codeLength
     * @return
     */
    public static String randomEmailCode(Integer codeLength) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int length = str.length();
        for (int i = 0; i < codeLength; i++) {
            int number = random.nextInt(length);
            builder.append(str.charAt(number));
        }
        return builder.toString();
    }




    /**
     * 根据最大值与最小值随机获取
     *
     * @param min
     * @param max
     * @return
     */
    public static int randDom(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 字符串转金额,可以处理金额 如 5000万,15亿,1.4 亿
     *
     * @param str
     * @return
     * @throws ParseException
     */
    public static BigDecimal strToBigDecimal(String str) throws ParseException {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //统一成大写形式
        str = str.toUpperCase().trim();
        if (ArrayUtils.contains(new String[]{"非公开", "-", "--", "- -", "---"}, str)) {
            return null;
        }
        char[] chars = str.toCharArray();
        int coefficient = 1;
        int subIndex = 0;
        for (char c : chars) {
            if (Character.isDigit(c) || ArrayUtils.contains(new char[]{',', '.'}, c)) {
                subIndex++;
                continue;
            } else {
                switch (c) {
                    case '十':
                        coefficient *= 10;
                        break;
                    case '百':
                        coefficient *= 100;
                        break;
                    case '千':
                        coefficient *= 1000;
                        break;
                    case '万':
                        coefficient *= 10000;
                        break;
                    case 'M':
                        coefficient *= 1000000;
                        break;
                    case '亿':
                        coefficient *= 100000000;
                        break;
                    case ' ':
                        break;
                    default:
                        throw new NumberFormatException("Unsupported string conversion!");
                }
            }
        }
        return BigDecimal.valueOf(numberFormat.parse(new String(ArrayUtils.subarray(chars, 0, subIndex))).doubleValue() * coefficient);
    }


    /**
     * 百分比数字转 BigDecimal
     *
     * @param str
     * @return
     */
    public static BigDecimal percentStrToBigDecimal(String str) {
        try {
            NumberFormat nf = NumberFormat.getPercentInstance();
            return BigDecimal.valueOf(nf.parse(str).doubleValue());
        } catch (Exception e) {
            return null;
        }
    }

    public static Double strToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer strToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 元->万元
     *
     * @param str
     * @return java.math.BigDecimal
     */
    public static BigDecimal turnWan(BigDecimal str) {
        return turnWan(str, 6);
    }


    public static Double turnWan(Double str) {
        return turnWan(str, 6);
    }

    public static String turnWan(String str) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        str = b.divide(new BigDecimal(10000), 6, BigDecimal.ROUND_HALF_UP).toString();
        return str;
    }

    public static String roundHalfUp(String str, Integer scale) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        str = b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
        return str;
    }

    public static String roundHalfUp(Double str, Integer scale) {
        if (str == null) {
            return "--";
        }
        BigDecimal b = BigDecimal.valueOf(str);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static BigDecimal turnWan(BigDecimal str, Integer scale) {
        if (str == null) {
            return BigDecimal.ZERO;
        }
        str = str.divide(new BigDecimal(10000), scale, BigDecimal.ROUND_HALF_UP);
        return str;
    }

    public static Double turnWan(Double str, Integer scale) {
        if (str == null) {
            return BigDecimal.ZERO.doubleValue();
        }
        BigDecimal b = BigDecimal.valueOf(str);
        str = b.divide(new BigDecimal(10000), 6, BigDecimal.ROUND_HALF_UP).doubleValue();
        return str;
    }

    public static String turnWan(String str, Integer scale) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        str = b.divide(new BigDecimal(10000), scale, BigDecimal.ROUND_HALF_UP).toString();
        return str;
    }

    /**
     * 校验值，为空返回 --
     */
    public static String checkVal(String str) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        return str;
    }

    /**
     * 校验参数保留两位小数，添加%
     */
    public static String addPercentage(String str) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        str = new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
        return str + "%";
    }

    /**
     * 保留两位小数
     * @param content
     * @return
     */
    public static Object twoPoint(String content) {
        if (StringUtils.isBlank(content) || !NumberUtils.isCreatable(content)) {
            return null;
        }
        return new BigDecimal(content).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    /**
     * 校验参数，百分比换算
     */
    public static String toPercentage(String str) {
        if (StringUtils.isBlank(str)) {
            return "--";
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return "--";
        }
        return b.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP) + "%";
    }

    /**
     * 加美元汇率
     */
    public static String mulRateUsd(String str) {
        if (StringUtils.isBlank(str)) {
            return BigDecimal.ZERO.toString();
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.toString();
        }
        str = b.multiply(RATE_USD).toString();
        return str;
    }

    /**
     * 减美元汇率
     */
    public static String divRateUsd(String str) {
        if (StringUtils.isBlank(str)) {
            return BigDecimal.ZERO.toString();
        }
        BigDecimal b = new BigDecimal(str);
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO.toString();
        }
        str = b.divide(RATE_USD, 6, BigDecimal.ROUND_HALF_UP).toString();
        return str;
    }

    /**
     * 第一位数四舍五入取整补零
     */
    public static Integer roundToZero(Integer num) {
        //为空 返回0
        if (num == null) {
            return ZERO;
        }
        //小于10的返回10
        if (num < TEN) {
            return TEN;
        }
        BigDecimal numRound = new BigDecimal(0);
        String numStr = String.valueOf(num);
        //10的n次方
        BigDecimal pow = new BigDecimal(10).pow(numStr.length() - 1);
        Integer indexTwo = Integer.valueOf(numStr.substring(ZERO, ONE));
        if (Integer.valueOf(numStr.substring(ONE, TWO)) >= FIVE) {
            numRound = new BigDecimal(indexTwo + ONE).multiply(pow);
        } else {
            numRound = new BigDecimal(indexTwo).multiply(pow);
        }
        return numRound.intValue();
    }


    /**
     * 将对应的数值相乘
     *
     * @param num1 数值
     * @param num2 数值
     * @return
     */
    public static Long mathNumMultiply(Long num1, Long num2) {
        if (Objects.nonNull(num1) && Objects.nonNull(num2)) {
            return num1 * num2;
        }
        return null;
    }

    /**
     * 将对应的数值 / divisor
     *
     * @param num 数值
     * @return 响应结果
     */
    public static Long mathNumDivide(Long num, Long divisor) {
        if (Objects.nonNull(num) && Objects.nonNull(divisor) && !(ZERO.longValue() == divisor)) {
            return new BigDecimal(num).divide(new BigDecimal(divisor), 0, RoundingMode.HALF_UP).longValue();
        }
        return num;
    }
    private static final Integer ZERO = 0;
    private static final Integer ONE = 1;

    private static final Integer TWO = 2;
    private static final Integer TEN = 10;
    private static final Integer FIVE = 5;

    private static final String SCORE = "-";

    private static final String PERCENT = "%";

    private static final Integer FOUR = 4;

    private static final Long ONE_HUNDRED_LONG = 100L;

    /**
     * 占比计算，返回百分比和null还有"-"
     *
     * @param num 分子
     * @param divisor 分母
     * @param isDownload 是否为下载
     * @return 响应结果
     */
    public static String mathNumDivideForPercentage(Long num, Long divisor, boolean isDownload) {
        if (Objects.isNull(num) || Objects.isNull(divisor) || ZERO.equals(num.intValue()) || ZERO.equals(divisor.intValue())) {
            return isDownload ? SCORE : null;
        }
        return translateNumForStr(num,divisor,FOUR,ONE_HUNDRED_LONG,PERCENT);
    }

    /**
     * 单元测试公式是否正确
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = mathNumDivideForPercentage(5L, 10L, false);
        String y = matchMomOrYoyForPercentage(10L, 5L, false);
        System.out.println(String.format("s-%s",s));
        System.out.println(String.format("y-%s",y));
    }

    /**
     * 计算同比、环比 = (分子-分母)*100 / 分母 = 百分比
     * @param molecular 分子
     * @param denominator 分母
     * @return 除了%号之外的百分比
     */
    public static String matchMomOrYoyForPercentage(Long molecular, Long denominator, boolean isDownload) {
        if (Objects.isNull(molecular) || Objects.isNull(denominator) || Integer.valueOf(0).equals(molecular.intValue()) || Integer.valueOf(0).equals(denominator.intValue())) {
            return isDownload ? "-" : null;
        }
        return translateNumForStr((molecular - denominator),denominator,4,100L,"%");
    }

    /**
     * 计算同比、环比 = (分子-分母)*100 / 分母 = 百分比
     * @param molecular 分子
     * @param denominator 分母
     * @return 除了%号之外的百分比
     */
    public static Double matchMomOrYoyForPercentageNum(Long molecular, Long denominator) {
        if (Objects.isNull(molecular) || Objects.isNull(denominator) || Integer.valueOf(0).equals(molecular.intValue()) || Integer.valueOf(0).equals(denominator.intValue())) {
            return null;
        }
        BigDecimal v = divideAndMultiplyNum((molecular - denominator), denominator, 4, 100L);
        return Objects.nonNull(v) ? v.doubleValue() : null;
    }

    /**
     * 转化数字结果为String
     *
     * @param molecular 除数
     * @param denominator 被除数
     * @param scale 保留小数位
     * @param multiplier 乘数
     * @param str 后缀字符串
     * @return
     */
    public static String translateNumForStr(Long molecular, Long denominator, int scale, Long multiplier, String str) {
        if (Objects.isNull(molecular) || Objects.isNull(denominator) || Integer.valueOf(0).equals(molecular.intValue()) || Integer.valueOf(0).equals(denominator.intValue())) {
            return null;
        }
        BigDecimal v = divideAndMultiplyNum(molecular, denominator, scale, multiplier);
        return Objects.isNull(v) ? null : v.doubleValue() + str;
    }

    /**
     *
     *
     * @param molecular 除数
     * @param denominator 被除数
     * @param scale 保留小数位
     * @param multiplier 乘数
     * @return
     */
    public static BigDecimal divideAndMultiplyNum(Long molecular, Long denominator,int scale,Long multiplier){
        if (Objects.isNull(molecular) || Objects.isNull(denominator) || Integer.valueOf(0).equals(denominator.intValue())) {
            return null;
        }
       return new BigDecimal((molecular))
                .divide(new BigDecimal(denominator), scale, RoundingMode.HALF_UP).multiply(new BigDecimal(multiplier));
    }

    /**
     * 加百分号
     *
     * @param value
     * @return
     */
    public static String addPercentByValue(Double value) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (NumberUtils.isCreatable(String.valueOf(value))){
            return new BigDecimal(String.valueOf(value)) + PERCENT;
        }
        return null;
    }

    /**
     * 控制double显示的位数
     *
     * @param value
     * @param scale
     * @return
     */
    public static Double formatDoubleValue(Double value, Integer scale) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (NumberUtils.isCreatable(String.valueOf(value))) {
            return new BigDecimal(String.valueOf(value)).setScale(scale, RoundingMode.HALF_UP).doubleValue();
        }
        return null;
    }

}
