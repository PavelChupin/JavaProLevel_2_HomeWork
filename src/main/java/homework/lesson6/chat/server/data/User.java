package homework.lesson6.chat.server.data;

public class User {
    private String nick;
    private String login;
    private String password;

    public User(String login, String password,String nick) {
        this.nick = nick;
        this.login = login;
        this.password = password;
    }


    public String getNick() {
        return nick;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
