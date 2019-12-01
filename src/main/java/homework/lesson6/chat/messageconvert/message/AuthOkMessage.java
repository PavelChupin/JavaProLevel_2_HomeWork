package homework.lesson6.chat.messageconvert.message;

public class AuthOkMessage {
    public String nickName;

    public AuthOkMessage(String nickName) {
        this.nickName = nickName;
    }

    /*
    public String toJson() {
        return new Gson().toJson(this);
    }

    public static AuthOkMessage fromJson(String json) {
        return new Gson().fromJson(json, AuthOkMessage.class);
    }*/
}
