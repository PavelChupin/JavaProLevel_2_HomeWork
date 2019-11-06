package homework.lesson2.messageconvert.message;

public class PrivateMessage {

    public String from;
    public String to;
    public String message;


    public PrivateMessage(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
/*
    public String toJson() {
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //return gson.toJson(this);
        return new Gson().toJson(this);
    }

    public static PrivateMessage fromJson(String json){
        return new Gson().fromJson(json, PrivateMessage.class);
    }*/

    /*
    public static void main(String[] args) {
        PrivateMessage m = new PrivateMessage();
        m.from = "Nick1";
        m.to = "Nick2";
        m.message = "Hello, World";

        System.out.println(m.toJson());

        System.out.println(m.fromJson(m.toJson()));

    }*/


}
