package com.winterchen.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.CacheManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class DateUtil
{
    public static String getSystemTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String str = df.format(new Date());// new Date()为获取当前系统时间
        return str;
    }

    public static String getTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String str = df.format(new Date());// new Date()为获取当前系统时间
        return str;
    }

    //当前日期加上day天
    public static String getAfterDate(String date, int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = format.parse(date);
            Date date2 = new Date(d.getTime() + day * 24 * 60 * 60 * 1000);
            return format.format(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
//    public static String getSystemSmallTime() {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        String str = df.format(new Date());// new Date()为获取当前系统时间
//        return str;
//    }

    /**
     * 获取当前星期第一天
     */
    public static boolean checkCellphone(String cellphone) {
        if (StringUtils.isEmpty(cellphone)) {
            return false;
        }
        String regex = "^1([357869][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
        return cellphone.matches(regex);
    }

    public static int compare_date(String DATE1, String DATE2) {

        // String DATE2 = getSystemTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


//public static String getWeekStartDate(){
//    Calendar cal = Calendar.getInstance();
//    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//    cal.set(Calendar.HOUR_OF_DAY, 0);
//    cal.set(Calendar.MINUTE, 0);
//    cal.set(Calendar.SECOND, 0);
//    Date date = cal.getTime();
//    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//    String str = format.format(date);
//    return str;
//}
//    public static Map<String, String> mondayToSunday() {
//        Date now = new Date();
//        Date time = new Date(now.getYear(), now.getMonth(), now.getDate());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(time);
//        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
//        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
//        if (1 == dayWeek) {
//            cal.add(Calendar.DAY_OF_MONTH, -1);
//        }
//        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
//        cal.setFirstDayOfWeek(Calendar.MONDAY);
//        // 获得当前日期是一个星期的第几天
//        int day = cal.get(Calendar.DAY_OF_WEEK);
//        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
//        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
//        cal.set(Calendar.HOUR, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        String imptimeBegin = sdf.format(cal.getTime());
//        Date mondayDate = cal.getTime();
//        // System.out.println("所在周星期一的日期：" + imptimeBegin);
//
//        cal.add(Calendar.DATE, 6);
//        cal.set(Calendar.HOUR, 23);
//        cal.set(Calendar.MINUTE, 59);
//        cal.set(Calendar.SECOND, 59);
//
//        String imptimeEnd = sdf.format(cal.getTime());
//        Date sundayDate = cal.getTime();
////      System.out.println("所在周星期日的日期：" + imptimeEnd);
//        DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////      System.out.println("星期一的开始："+datetimeDf.format(mondayDate));
////      System.out.println("星期天的结束："+datetimeDf.format(sundayDate));
//        Map<String, String> map = new HashMap<>();
//        map.put("beginDate", datetimeDf.format(mondayDate));
//        map.put("endDate", datetimeDf.format(sundayDate));
//        return map;
//    }

//    /**
//     * 获得当月第一天
//     *
//     * @return
//     */
//    public static String getMonthFirstDay() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.MONTH, 0);
//        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
//        String first = format.format(c.getTime());
//        return first;
//    }
//
//    /**
//     * 获得当月最后一天
//     *
//     * @return
//     */
//    public static String getMonthLastDay() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
//        Calendar ca = Calendar.getInstance();
//        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//        String last = format.format(ca.getTime());
////        System.out.println("===============last:"+last);
//        return last;
//    }

    public static String dayStartTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Calendar c1 = new GregorianCalendar();
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);

        String str1 = format.format(c1.getTime());
        return str1;
    }

    public static String dayEndTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Calendar c2 = new GregorianCalendar();
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);
        String str2 = format.format(c2.getTime());
        return str2;
    }

    public static String getRandomResult() {

        for (int i = 0; i <= 100; i++) {
            String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
            Random rand = new Random();
            StringBuffer flag = new StringBuffer();
            for (int j = 0; j < 6; j++) {
                flag.append(sources.charAt(rand.nextInt(9)) + "");
            }
            return flag.toString();
        }
        return "";
    }

    //public static String getCompanyResult(String companyId){
//    if(companyId.length()>1){
//        String[] arr = companyId.split(",");
//        String companyId_string = "";
//        for (int i = 0, len =arr.length; i < len; i++) {
//            if (i > 0) {
//                companyId_string += ",";
//            }
//            companyId_string+= "'" + arr[i]+ "'";
//        }
//        companyId =companyId_string;
//    }
//    return companyId;
//}
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(0);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    public static long getDiff(Date endTime, Date startTime) {
        try {


            //毫秒ms
            long diff = endTime.getTime() - startTime.getTime();

//            long diffSeconds = diff / 1000 % 60;
//            long diffMinutes = diff / (60 * 1000) % 60;
//            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print("两个时间相差：");
            System.out.print(diffDays + " 天, ");
//            System.out.print(diffHours + " 小时, ");
//            System.out.print(diffMinutes + " 分钟, ");
//            System.out.print(diffSeconds + " 秒.");
            return diffDays;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 日期减几月
     */
    public static String dateMinusMonth(Date dt,int count) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);//使用给定的 Date 设置此 Calendar 的时间。
        rightNow.add(Calendar.MONTH, count);// 日期减1个月
        Date dt1 = rightNow.getTime();//返回一个表示此 Calendar 时间值的 Date 对象。
        String reStr = sdf.format(dt1);//将给定的 Date 格式化为日期/时间字符串，并将结果添加到给定的 StringBuffer。
        return reStr;
    }

    public static int getBetweendays(String date1, String date2) {
        long between_days = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(date1));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(date2));
            long time2 = cal.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static void main(String[] args) {


        System.out.println(getNowDate());
    }


}
