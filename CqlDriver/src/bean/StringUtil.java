package bean;

import java.text.DecimalFormat;
import java.util.Date;

public class StringUtil {
	
	public static String dealNull(String str) {
		String resultStr = null;
		if (str == null || str.trim().length() <= 0) {
			resultStr = "";
		} else {
			resultStr = str.trim();
		}
		return resultStr;
	}
	 
    public static boolean isNull(String str){
		return dealNull(str).equals("")? true:false;
	}
	
	public static Integer dealNull(Integer str) {
		Integer resultStr = 0;
		if (str == null ) {
			return resultStr;
		}
		return str;
	}

	
	public static String formatDouble(double d){
		DecimalFormat df=new DecimalFormat("0.00");
		return df.format(d);
	}
	
	/**
	 * @deprecated
	 * @param date
	 * @return
	 * String
	 */
	public static String getDateToString(Date date){
		if(date==null){
			return "";
		}
		String strDate;
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		strDate = df.format(date);
		return strDate;
		
	}
	
	/**
	 * @Deprecated
	 * @param date
	 * @return
	 * String
	 */
	public static String geYYYYMMToString(Date date){
		if(date==null){
			return "";
		}
		String strDate;
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM"); 
		strDate = df.format(date);
		return strDate;
		
	}
	
	/**
	 * 
	 * @Deprecated
	 * @param date
	 * @return
	 * String
	 */
	public static String getYYYYMMDDToString(Date date){
		if(date==null){
			return "";
		}
		String strDate;
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd"); 
		strDate = df.format(date);
		return strDate;
		
	}
	
	/**
	 *将字符串首字母变大写 
	 * TODO
	 * @param s
	 * @return
	 * String
	 */
	public static String upFirstChart(String s){
		char[] cs=s.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
	}

	/**
	 * 判断字符串是否为空
	 * @param str 字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * 判断字符串是否不为空
	 * @param str 字符串
	 * @return 是否不为空
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str);
	}
	
}
