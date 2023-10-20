package java_ex.enum_ex;

public enum CustomError {

    ILLEGAL_ARG(1100, "일치 하지 않은 매개변수"),
    NOT_FOUND_USER(2000, "유저를 찾지 못함."),
    
    EXAM_ERROR(3001, "에러 예시");

    private final int code;
    private final String message;

    private CustomError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
