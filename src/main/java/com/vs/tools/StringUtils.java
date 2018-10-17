package com.vs.tools;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	private static final char SEPARATOR = '_';
	
	public static String toPath(String date){
		StringBuffer result = new StringBuffer();
		result.append(File.separator);
		result.append(date.substring(0,4));
		result.append(File.separator);
		result.append(date.substring(4,6));
		result.append(File.separator);
		result.append(date.substring(6,8));
		result.append(File.separator);
		result.append(date.substring(8));
		result.append(File.separator);
		return result.toString();
	}
	
	public static boolean isNullorBlank(final String str) {
		boolean isNorB = false;
		if (null == str || 0 >= str.length() || str.equals("null")) {
			isNorB = true;
		}
		return isNorB;
	} 

	public static List<Integer> stringArrayToIntegerList(String[] source){
		List<Integer> list = new ArrayList<Integer>();
		if(source != null && source.length > 0){
			for(String s : source){
				list.add(new Integer(s));
			}
		}
		return list;
	}
	
	/**
	 * 判断字符串是否为数字
	 */
	public static boolean isNumeric(String str){
	     Pattern pattern = Pattern.compile("[0-9]*");
	     return pattern.matcher(str).matches();   
	}
	/**
	 * 判断字符串是否为数字(整数或者小数)
	 */
	public static boolean isNumericOrDouble(String str){
	     Pattern pattern = Pattern.compile("[0-9]*|[0-9]*\\.[0-9]*");
	     return pattern.matcher(str).matches();   
	}
	
	/** 
	 * 方法说明 :过滤文本内的换行符
	 * @author  joker 
	 * 创建时间：2015-01-29
	 * <p>@param null</p>
	 */
	public static String valEnter(String str){
		String replacedString = null;
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			replacedString = str.replaceAll("\\r|\\r\\n|\\n|\\t", "");
			return replacedString;
		}
		return str;
	}

	
	/** 
	 * 方法说明 :小数转百分比
	 * @author  joker 
	 * 创建时间：2015-01-29
	 * <p>@param null</p>
	 */
	public static String getDecimalToFraction(String value){
		double temp = Double.valueOf(value);
		String fraction = null;
		double count = 1 / temp;
		fraction = "1:" + String.valueOf(count).substring(0, String.valueOf(count).lastIndexOf(".") + 2);
		fraction = fraction.substring(0, fraction.lastIndexOf("."));
		return fraction;
	}
	
	/** 
	 * 方法说明 :字符串逗号分隔从新拼接
	 * @author  joker 
	 * 创建时间：2015-01-29
	 * <p>@param null</p>
	 */
	public static String getStringSplit(String string){
		if(!StringUtils.isNullorBlank(string)){
			String[] strings = string.split(",");
			StringBuffer stringBuffer = new StringBuffer();
			for(int i = 0; i < strings.length; i++){
				if(i == 0){
					stringBuffer.append("'" + strings[i] + "'");
				}else {
					stringBuffer.append(", '" + strings[i] + "'");
				}
			}
			return stringBuffer.toString();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>作者:joker	pu</p> 
	 * <p>功能描述:是否包含传入的字符串</p>
	 * <p>创建时间:2015-02-03 11:25:00</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static boolean isIncludeText(String text, String includeText){
		return text.contains(includeText);
	}
	
	/**
	 * 
	 * <p>作者:joker	pu</p> 
	 * <p>功能描述:获取对应schoolMomentType</p>
	 * <p>创建时间:2015-06-24 11:25:00</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static int getSchoolMomentType(String gradeStage){
		int flag = 404;
		if("读中学".equals(gradeStage)) flag = 0;
		if("读本科".equals(gradeStage)) flag = 1;
		if("读研究生".equals(gradeStage)) flag = 2;
		return flag;
	}
	
	/**
	 * 
	 * <p>作者:joker	pu</p> 
	 * <p>功能描述:获取对应schoolMomentType的中文</p>
	 * <p>创建时间:2015-06-24 11:25:00</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static String getMomentName(int schoolMomentType){
		String momentName = null;
		if(schoolMomentType == 0) momentName = "读中学";
		if(schoolMomentType == 1) momentName = "读本科";
		if(schoolMomentType == 2) momentName = "读研究生";
		return momentName;
	}
	
	/**
	 * 
	 * <p>作者:jokerPu</p> 
	 * <p>功能描述:驼峰转下划线</p>
	 * <p>创建时间:2015-12-09</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static String toUnderlineName(String s) {
        if (s == null) {
            return null;
        }
 
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
 
            boolean nextUpperCase = true;
 
            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }
 
            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
 
            sb.append(Character.toLowerCase(c));
        }
 
        return sb.toString();
    }
 
	/**
	 * 
	 * <p>作者:jokerPu</p> 
	 * <p>功能描述:下划线转驼峰</p>
	 * <p>创建时间:2015-12-09</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
 
        s = s.toLowerCase();
 
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
 
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
 
        return sb.toString();
    }
 
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    /**
	 * 
	 * <p>作者:jokerPu</p> 
	 * <p>功能描述:首字母小写</p>
	 * <p>创建时间:2016-01-06</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    /**
	 * 
	 * <p>作者:jokerPu</p> 
	 * <p>功能描述:首字母大写</p>
	 * <p>创建时间:2016-01-06</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

	/**
	 *
	 * <p>作者:jokerPu</p>
	 * <p>功能描述:逗号拼接字符串转list</p>
	 * <p>创建时间:2016-01-06</p>
	 * <p>@param str
	 * <p>@return</p>
	 * <p>修改:</p>
	 */
	public static List<String> stringTolist(String s){
		if(isNullorBlank(s))
			return null;
		List<String> list = new ArrayList<>();
		String[] ss = s.split(",");
		for (int i = 0; i < ss.length; i++){
			list.add(ss[i]);
		}
		return list;
	}



	
	public static void main(String[] args){
//		String[] imgUrlSplit = "http://66xue-img.oss-cn-beijing.aliyuncs.com/affix/icon_school_2015_12_09.png".split("\\.");
//		StringBuffer imgsBuffer = new StringBuffer();
//		imgsBuffer.append(imgUrlSplit[0]).append(".").append(imgUrlSplit[1]).append(".").append(imgUrlSplit[2]).append(".").append(imgUrlSplit[3]).append("_android").append(".").append(imgUrlSplit[4]);
//		System.out.println(imgsBuffer);
//		System.out.println(1 + "1");
//		Map<String, Object> map = new HashMap<>();
//		if(map.get("id") != null){
//			System.out.println(1);
//		}else{
//			System.out.println(2);
//		}
//		String time = "2016-08-29 00:00:00";
//		System.out.println(time.substring(0, 10));
//		StringUtils.stringTolist("1,2,3,4,5,6,7,8,9,0");
		List<String> comments = new ArrayList<>();
		comments.add("1");
		comments.add("2");
		comments.add("3");
		comments.add("4");
//		comments.add("5");
//		comments.add("6");
		List<String> temp = comments.subList(0, 5);
		System.out.println(temp.size());
	}
	
	
}