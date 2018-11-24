package bean;



import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务上下文,线程共享
 * @author wangchao
 *
 */
public class BusinessContext {


	public static void main(final String[] args) {
		System.out.println(new Date(1521302154832L));
	}
    /**
     * ThreadLocal常量，线程共享
     */
    private static final ThreadLocal<Map<String, String>> THREADLOCAL = new ThreadLocal<Map<String, String>>();

    /**
     * DateThreadLocal常量，线程共享
     */
    private static final ThreadLocal<Map<String, Date>> DATETHREADLOCAL = new ThreadLocal<Map<String, Date>>();

    /**
     * 获取上下文中的值
     * @param key
     * @return 获取的变量值
     */
    public static String getProperty(final String key) {

        if (!StringUtils.isEmpty(key)) {
            final Map<String, String> threadMap = THREADLOCAL.get();
            if (null != threadMap) {
                return threadMap.get(key);
            }
        }
        return null;
    }

    /**
     * 获取上下文中的值(时间)
     * @param key
     * @return 获取的变量值
     */
    public static Date getDateProperty(final String key) {

        if (!StringUtils.isEmpty(key)) {
            final Map<String, Date> threadMap = DATETHREADLOCAL.get();
            if (null != threadMap) {
                return threadMap.get(key);
            }
        }
        return null;
    }

    /**
     * 设置上下文中的值
     * @param key
     * @param value
     */
    public static void setProperty(final String key, final String value) {

        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            Map<String, String> threadMap = THREADLOCAL.get();
            if (null == threadMap) {
                threadMap = new HashMap<String, String>();
                THREADLOCAL.set(threadMap);
            }
            threadMap.put(key, value);
        }

    }

    /**
     * 设置上下文中的值(时间)
     * @param key
     * @param value
     */
    public static void setDateProperty(final String key, final Date value) {

        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
            Map<String, Date> threadMap = DATETHREADLOCAL.get();
            if (null == threadMap) {
                threadMap = new HashMap<String, Date>();
                DATETHREADLOCAL.set(threadMap);
            }
            threadMap.put(key, value);
        }

    }

    /**
     * 清空上下文中的值
     */
    public static void clear() {

        THREADLOCAL.remove();
        DATETHREADLOCAL.remove();

    }

}
