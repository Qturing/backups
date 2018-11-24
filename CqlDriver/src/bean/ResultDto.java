package bean;




public class ResultDto {

	/**
	 * 通过id调用获取taskInfo数据，taskInfo 对应的 javabean
	 */

	private static final String SUCCESS="success";
	private static final String ERROR="error";
	
	private String type;
	
	private String code;
	
	private String message;
	
	private TaskOutputDto data;
	
	private String callId;

	
	
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public ResultDto(TaskOutputDto data, String type, String code, String message){
		this.data=data;
		this.type=type;
		this.message=message;
		this.code=code;
	}
	public ResultDto(){
	}
/*	public static ResultDto successResult(Object data){
		ResultDto dto=new ResultDto(data, SUCCESS, null, null);
		return dto;
	}
	
	public static ResultDto errorResult(String message,String code){
		ResultDto dto=new ResultDto(null, ERROR, code, message);
		return dto;
	}*/
	
	public static ResultDto result(final TaskOutputDto result, final ExceptionType excp){
		 final ResultDto dto = new ResultDto();
	        //dto.setUserStatus(userStatus);
	        dto.setCallId(BusinessContext.getProperty(Constants.ID.CALLID.toString()));
	        if (excp != null) {
	            dto.setMessage(excp.getExceptionMsg());
	            dto.setType(ERROR);
	        } else {
	            dto.setData(result);
	            dto.setType(SUCCESS);
	        }
	        return dto;
	}
	
	public boolean isSuccess(){
		if(SUCCESS.equals(this.type)){
			return true;
		}else{
			return false;
		}
	}
	
	public ResultDto success(TaskOutputDto data){
		this.data=data;
		this.type=SUCCESS;
		this.message=null;
		this.code=null;
		return this;
	}
	
	public ResultDto error(String message, String code){
		this.data=null;
		this.type=ERROR;
		this.message=message;
		this.code=code;
		return this;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TaskOutputDto getData() {
		return data;
	}

	public void setData(TaskOutputDto data) {
		this.data = data;
	}

	public String toString(){
		return "type=" + type + "data=" + data + "message=" + message + "code=" + code + "callId=" + callId;
	}
	
	
}
