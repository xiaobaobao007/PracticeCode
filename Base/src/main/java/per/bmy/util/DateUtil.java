package per.bmy.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 */
public class DateUtil extends DateUtils {

    public static String PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);

    /**
     * 获取当前系统时间
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 昨日0点
     */
    public static Date getLastDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 今日0点
     */
    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 周一0点
     */
    public static Date getMonday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
            return cal.getTime();
        }
        return cal.getTime();
    }

    /**
     * 上周一0点
     */
    public static Date getLastMonday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 下周一0点
     */
    public static int getNextMonday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, +1);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 获取当前系统秒数
     */
    public static int getCurSec() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取当前毫秒数
     */
    public static long getCurMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 根据秒数得到Date
     */
    public static Date changeSecond2Date(int sec) {
        return new Date(sec * 1000l);
    }

    /**
     * 格式化日期，默认格式yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate(Date date) {
        return sdf.format(date);
    }

    public static String formatDate(int sec) {
        return formatDate(changeSecond2Date(sec));
    }

    public static String formatDate() {
        return formatDate(changeSecond2Date(getCurSec()));
    }

    /**
     * 格式化日期，
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


    /**
     * 此时间是否是当前天
     */
    public static boolean isToday(long millis) {
        Calendar now = Calendar.getInstance();

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(millis);
        if (now.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH) && now.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && now.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            return true;
        }
        return false;
    }

    public static boolean isToday(int sec) {
        return isToday(sec * 1000L);
    }

    public static boolean isYestoday(long second) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -1);

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(second);
        if (now.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH) && now.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && now.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            return true;
        }
        return false;
    }

    /**
     * 获取当天几点的毫秒数
     */
    public static long getTodayHourMillis(int hours) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, hours);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTimeInMillis();
    }

    /**
     * 获取当天的相对秒数
     */
    public static int getTodaySec() {
        Calendar now = Calendar.getInstance();
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);

        return (int) ((now.getTimeInMillis() - todayStart.getTimeInMillis()) / 1000);
    }

    /**
     * 得到下一个制定时间的时间戳
     */
    public static long getNextTime(int hours, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.MILLISECOND, 0);
        long now = System.currentTimeMillis();
        if (now > cal.getTime().getTime()) {
            cal.add(Calendar.DATE, 1);
        }
        return cal.getTime().getTime();
    }

    /**
     * 得到今天指定时间的时间戳
     */
    public static Date getTodayDate(int hours, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static boolean isThisWeek(long time) {
        Calendar now = Calendar.getInstance();
        now.setFirstDayOfWeek(Calendar.MONDAY);

        Calendar c2 = Calendar.getInstance();
        c2.setFirstDayOfWeek(Calendar.MONDAY);
        c2.setTimeInMillis(time);
        return now.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && now.get(Calendar.WEEK_OF_YEAR) == c2.get(Calendar.WEEK_OF_YEAR);
    }

    public static boolean isSameMonth(int time) {
        Calendar now = Calendar.getInstance();

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(time * 1000l);
        return now.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && now.get(Calendar.MONTH) == c2.get(Calendar.MONTH);

    }

    /**
     * 距离现在的天数
     */
    public static int diffDaysNow(long start) {
        Calendar now = Calendar.getInstance();
        return diffDays(new Date(start), now.getTime());
    }

    public static int diffDays(Date startTime, Date endTime) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startTime);

        Calendar createCal = Calendar.getInstance();
        createCal.setTime(endTime);

        int year1 = startCal.get(Calendar.YEAR);
        int year2 = createCal.get(Calendar.YEAR);
        int day1 = startCal.get(Calendar.DAY_OF_YEAR);
        int day2 = createCal.get(Calendar.DAY_OF_YEAR);
        if (year1 != year2) { // 同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
                {
                    timeDistance += 366;
                } else // 不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { // 不同年
            return day2 - day1;
        }
    }

    public static int diffWeeks(Date startTime, Date endTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0)
            dayOfWeek = 7;

        int dayOffset = diffDays(startTime, endTime);

        int weekOffset = dayOffset / 7;
        int a;
        if (dayOffset > 0) {
            a = (dayOffset % 7 + dayOfWeek > 7) ? 1 : 0;
        } else {
            a = (dayOfWeek + dayOffset % 7 < 1) ? -1 : 0;
        }
        weekOffset = weekOffset + a;
        return weekOffset;
    }

    /**
     * 获取距离当天 几天后的凌晨秒数
     */
    public static int getAfterTodayMillis(int days) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.add(Calendar.DAY_OF_MONTH, days);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return (int) (todayStart.getTimeInMillis() / 1000);
    }

    /**
     * 获取指定时间 几天后的凌晨秒数
     */
    public static int getAfterTodayMillis(int time, int days) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTimeInMillis(time * 1000L);
        todayStart.add(Calendar.DAY_OF_MONTH, days);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return (int) (todayStart.getTimeInMillis() / 1000);
    }

    /**
     * 距离现在相差几天
     */
    public static int getDiffsDays(int time) {
        Date date = changeSecond2Date(time);
        return diffDays(date, getNow());
    }

    public static void main(String[] args) {
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 100);

        System.out.println(diffWeeks(d, cal.getTime()));
        System.out.println(formatDate(getAfterTodayMillis(-1)));
    }
}
