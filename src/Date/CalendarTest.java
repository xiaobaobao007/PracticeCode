package Date;

import java.util.Calendar;
import java.util.Date;

/**
 * 如果是 JDK8 的应用，可以使用 Instant 代替 Date，LocalDateTime 代替 Calendar，
 * DateTimeFormatter 代替 SimpleDateFormat，官方给出的解释：simple beautiful strong immutable
 * thread-safe。date使用calender来代替
 */
public class CalendarTest {

	public static String PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
	public static String PATTERN_DATE_NUMBER = "yyyyMMdd";

	public static int getTodaySec() {

		Calendar now = Calendar.getInstance();
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);

		return (int) ((now.getTimeInMillis() - todayStart.getTimeInMillis()) / 1000);
	}

	/**
	 * 今日0点
	 *
	 * @return
	 */
	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

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

	/**
	 * 时间时候进行宽松解释，宽松的解释，例如“1996年2月9日”，将被视为相当于1996年2月1日以后的第941天。
	 * 以严格（不宽容的）解释，这样的日期会引起异常。 默认是宽松的。
	 * cal.setLenient(!cal.isLenient());
	 */
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MONTH,12);
		System.out.println(cal.get(Calendar.YEAR));//年份
		System.out.println(cal.get(Calendar.MONTH));//月份需要加1
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));//当月的第几天
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));//当天的第几个小时
		System.out.println(cal.get(Calendar.MINUTE));//几分
		System.out.println(cal.get(Calendar.SECOND));//几秒
		System.out.println(cal.get(Calendar.MILLISECOND));//几毫秒
		System.out.println(diffDaysNow(0));//距离的天数

		Calendar test = Calendar.getInstance();
		test.add(Calendar.DAY_OF_MONTH, -3);
		System.out.println(diffDaysNow(test.getTimeInMillis()));//距离的天数
	}
}