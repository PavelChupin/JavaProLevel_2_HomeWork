package homework.lesson3.messageconvert.message;

public class AuthErrorMessage {
    public String errorMsg;

    public AuthErrorMessage(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /*    public String toJson() {
        return new Gson().toJson(this);
    }

    public static AuthErrorMessage fromJson(String json) {
        return new Gson().fromJson(json, AuthErrorMessage.class);
    }*/
}
