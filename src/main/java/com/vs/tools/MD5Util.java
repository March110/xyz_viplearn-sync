package com.vs.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** MD5 utility. */
public class MD5Util {
  /** Returns the MD5 digest string for the given string. */
  public static String getMD5String(String str) {
    MessageDigest messageDigest = null;

    try {
      messageDigest = MessageDigest.getInstance("MD5");

      messageDigest.reset();

      messageDigest.update(str.getBytes("UTF-8"));
    } catch (NoSuchAlgorithmException e) {
      System.out.println("NoSuchAlgorithmException caught!");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    byte[] byteArray = messageDigest.digest();

    StringBuffer md5StrBuff = new StringBuffer();

    for (int i = 0; i < byteArray.length; i++) {
      if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
        md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
      } else {
        md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
      }
    }

    return md5StrBuff.toString().toUpperCase();
  }

  /** Main methods. */
  public static void main(String[] args) {
    //    String md5String = MD5Util.getMD5String("isftostone_xdf");
    System.out.println(MD5Util.getMD5String("123321"));

    //    List<String> strings = new ArrayList<>();
    //    List<String> stringList = new ArrayList<>();
    //    strings.add("1");
    //    strings.add("2");
    //    stringList.add("sl1");
    //    stringList.add("sl2");
    //    stringList.add("sl3");
    //    stringList.add("sl4");
    //    stringList.add("sl5");
    //    stringList.add("sl6");
    //    strings.addAll(0,stringList);
    //    for(int i = 0; i < strings.size(); i++){
    //      System.out.println(strings.get(i));
    //    }
  }
}
