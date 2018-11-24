package bean;

/**
 * 异常类型定义 <li>exceptionCode</li> <li>exceptionMsg</li>
 * 
 * @author wayne
 *
 */
public class ExceptionType {

	/**
	 * 异常码
	 */
	private String exceptionCode;

	/**
	 * 异常描述
	 */
	private String exceptionMsg;

	public ExceptionType(final String errorCode, final String errorMsg) {

		this.exceptionCode = errorCode;
		this.exceptionMsg = errorMsg;
	}

	public ExceptionType(final String errorCode) {

		this.exceptionCode = errorCode;
	}

	public String getExceptionCode() {

		return exceptionCode;
	}

	public void setExceptionCode(final String exceptionCode) {

		this.exceptionCode = exceptionCode;
	}

	public String getExceptionMsg() {

		return exceptionMsg;
	}

	public void setExceptionMsg(final String exceptionMsg) {

		this.exceptionMsg = exceptionMsg;
	}

	/**
	 * 返回格式 exceptionCode + " - " + exceptionMsg
	 */
	@Override
	public String toString() {

		return String.valueOf(exceptionCode) + " - " + exceptionMsg;
	}

}
