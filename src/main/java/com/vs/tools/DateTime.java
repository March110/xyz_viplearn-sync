package com.vs.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/** Created by jokerpu on 2017.6.6. */
public class DateTime {

  public static String getDateTime() {
    return LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public static String getDateTime(String pattern) {
    if (null == pattern || "".equals(pattern)) {
      pattern = "yyyy-MM-dd HH:mm:ss";
    }
    return LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern(pattern));
  }

  public static int getYear(){
    return LocalDateTime.now().getYear();
  }

  public static int getMonth(){
    return LocalDateTime.now().getMonthValue();
  }

  public static int getHour(){
    return LocalDateTime.now().getHour();
  }

  public static String getAFewMonthsAgo(int difference){
    LocalDateTime today = LocalDateTime.now();
    LocalDateTime month = today.minus(difference, ChronoUnit.MONTHS);
    return month.format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern("yyyy-MM-dd 00:00:00"));
  }

  public static String getAFewDaysAgo(int difference){
    LocalDateTime today = LocalDateTime.now();
    LocalDateTime day = today.minus(difference, ChronoUnit.DAYS);
    return day.format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern("yyyy-MM-dd 00:0:00"));
  }

  public static String getAFewDaysAgoTime(int difference){
    LocalDateTime today = LocalDateTime.now();
    LocalDateTime day = today.minus(difference, ChronoUnit.DAYS);
    return day.format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  public static String getAFewHoursAgo(int difference){
    LocalDateTime today = LocalDateTime.now();
    LocalDateTime day = today.minus(difference, ChronoUnit.HOURS);
    return day.format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern("yyyy-MM-dd HH:00:00"));
  }

  public static long getTimeMillis(String dateTime){
    long currentTimeMillis = 0;
    if(dateTime == null){
      currentTimeMillis = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }else {
      LocalDateTime time = LocalDateTime.parse(dateTime, DateTimeFormatter.BASIC_ISO_DATE.ofPattern("yyyy-MM-dd HH:mm:ss"));
      currentTimeMillis = time.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
    return currentTimeMillis;
  }

  public String getWeekStart(){
    return null;
  }

  public String getWeekEnd(){
    return null;
  }

  public static long daysDiff(LocalDateTime startTime, LocalDateTime endTime){
    return ChronoUnit.DAYS.between(startTime, endTime);
  }

  public static String transferByDate(Date date)  {
    SimpleDateFormat parse = null;
    try {
      parse = new SimpleDateFormat("MMMM,yyyy", Locale.UK);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return parse.format(date);
  }
  public static String transferByStr(String str)  {
    SimpleDateFormat parse = null;
    Date date = null;
    try {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      parse = new SimpleDateFormat("MMMM,yyyy", Locale.UK);
      date = format.parse(str);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return parse.format(date);
  }

  public static void main(String[] strings) throws Exception {
//    System.out.println(DateTime.getAFewDaysAgo(1));
//    System.out.println(DateTime.getAFewMonthsAgo(3));
//    Target t = new Target();
//    t.setYear("2017");
//    t.setMonth("08");
//    t.setDay("29");
//    LocalDateTime endTime = LocalDateTime.of(Integer.valueOf(t.getYear()), Integer.valueOf(t.getMonth()), Integer.valueOf(t.getDay()), 0, 0);
//    LocalDateTime startTime = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0);
//    System.out.println(DateTime.daysDiff(endTime, startTime));
//    System.out.println("2017-08-30".substring(0, 4));
//    System.out.println("2017-08-30".substring(5, 7));
//    System.out.println("2017-08-30".substring(8, 10));
    System.out.println(transferByStr("2018-05-23"));
  }
}
