package main.java.org.projet.util;

public class ErrorUtil {
    private static ErrorUtil instance;
    
    private Integer errorCode;
    private String message;
    
    private ErrorUtil() {
        
    }
    
    public static ErrorUtil getInstance() {
        if (instance == null) {
            instance = new ErrorUtil();
        }
        
        return instance;
    }

    public Integer getErrorCode() { //0: success, 1: error
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
