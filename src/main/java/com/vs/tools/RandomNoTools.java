package com.vs.tools;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Random number utility.
 */
public class RandomNoTools {
  /**
   * 返回6位随机整数.
   */
  public static int getRandomNo() {
    int mobile_code = (int) ((Math.random() * 9 + 1) * 100000);
    return mobile_code;
  }

  /**
   * 返回数组中随机一位.
   */
  public static String getRandomArrayValue(String[] args) {
    String arrayValue = null;
    int index;
    index = (int) (Math.random() * args.length);
    arrayValue = args[index];
    return arrayValue;
  }

  /**
   * 返回4位随机整数.
   */
  public static int getRandomNo4() {
    int mobile_code = (int) ((Math.random() * 9 + 1) * 1000);
    return mobile_code;
  }

  /**
   * 返回1位随机整数.
   */
  public static int getRandomNo1() {
    int mobile_code = (int) ((Math.random() * 9 + 1) * 1);
    return mobile_code;
  }

  /**
   * 返回2位随机整数.
   */
  public static int getRandomNo2() {
    int mobile_code = (int) ((Math.random() * 9 + 1) * 10);
    return mobile_code;
  }

  /**
   * 返回3位随机整数.
   */
  public static int getRandomNo3() {
    int mobile_code = (int) ((Math.random() * 9 + 1) * 100);
    return mobile_code;
  }
  public static int getRandom100to200() {
    Random r = new Random();
    int mobile_code = r.nextInt(100) + 100;
    return mobile_code;
  }
  public static Map<String, Object> randomScore(int SubordinateQId) {
    Map<String, Object> data = new HashMap<>();
    Calendar now = Calendar.getInstance();
    int day = now.get(Calendar.DAY_OF_MONTH);
    data.put("readScore", 20 + (SubordinateQId + day + 1) % 5);
    data.put("listenScore", 20 + (SubordinateQId + day) % 5);
    return data;
  }

  public static int getAB(int a, int b) {
    Random random = new Random();
    int number = random.nextInt(b - a + 1) + a;
    return number;
  }


  public static void main(String[] args) {
    System.out.println(RandomNoTools.getAB(70, 90));
  }
}
