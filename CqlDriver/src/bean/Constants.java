package bean;

public class Constants {

	public final static String TIMEZONEDEFAULT="8";
	 /**
     * comma ","
     */
    public static final String COMMA_STR = ",";
    

    
    
	 /**
     * 设备归属的redis缓存key名字
     */
    public static final String REDIS_KEY_MAP_DEVICE_AREA="deviceArea";
    /**
     * 设备的定位缓存redis的key
     */
    public static final String REDIS_KEY_MAP_LOCATION="location";
    /**
     * 设备的心率缓存redis的key
     */
    public static final String REDIS_KEY_MAP_HEARTERATE="hearterate";
    /**
     * 设备的血压缓存redis的key
     */
    public static final String REDIS_KEY_MAP_BLOODPRESSURE="bloodpressure";
    /**
     * app用户是否读取过周报的标志redis的key
     */
    public static final String REDIS_KEY_MAP_REPORTREADFLAG="reportreadflag";
    
    
    /**
     * 各种业务id
     * @author keke
     *
     */
public static enum ID{
		
	
		CALLID("callId");
		private String value;

		ID(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}
	}
    
    

/**
 * 会话常量定义
 */
public static enum Session{

	USER("session_user"),
	ROLE("session_role"),
	OPERATORID("oprator_id"),
	SLIDER_MENU("session_slider_menu"),
	LOCALE_CONTEXT("session_locale");
	private final String value;

	Session(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
    
	/**
	 * 命令码
	 * @author keke
	 *
	 */
	public static enum Identifier{
		
		REC_REQ_LOGIN("AP00"),
		REC_REQ_LOCATION("AP01"),
		REC_REQ_KEEPALIVE("AP03"),
		REC_REQ_HEARTRATE("AP49"),
		REC_REQ_HEARTRATEANDBLOODPRESSURE("APHT"),
		SEND_RES_LOGIN("BP00"),
		SEND_RES_LOCATION("BP01"),
		REC_RES_SW_LOCATION("BP02"),
		SEND_RES_KEEPALIVE("BP03"),
		SEND_RES_HEARTRATE("BP49"),
		SEND_RES_HEARTRATEANDBLOODPRESSURE("BPHT"),
		
		
		SEND_REQ_HEARTRATE("BPXL"),
		SEND_REQ_BLOODPRESSURE("BPXY"),
		SEND_REQ_WHITELIST("BP84"),
		SEND_REQ_NAOZHONG("BP85"),
		SEND_REQ_WORKMODEL("BP33"),
		SEND_REQ_MAINCONTR("BP11"),
		SEND_REQ_SOS("BP12"),
		SEND_REQ_FAMILYPHONE("BP14"),
		SEND_REQ_FASTLOCATION("BP16");
		private String value;

		Identifier(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}
	}
	
	public static final String DELETED="1";
	public static final String UNDELETED="0";
	public static final String DISABLED="1";
	public static final String ABLE="0";
}
