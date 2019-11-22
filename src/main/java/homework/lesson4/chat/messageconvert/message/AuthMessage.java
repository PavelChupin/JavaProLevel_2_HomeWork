package homework.lesson4.chat.messageconvert.message;

public class AuthMessage {
    public String login;
    public String pass;

    public AuthMessage(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    /*
    public String toJson() {
        return new Gson().toJson(this);
    }

    public static AuthMessage fromJson(String json) {
        return new Gson().fromJson(json, AuthMessage.class);
    }*/
}
