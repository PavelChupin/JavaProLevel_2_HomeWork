package homework.lesson3.messageconvert.message;

public class PublicMessage {
    public String from;
    public String message;

    public PublicMessage(String from, String message) {
        this.from = from;
        this.message = message;
    }

    /*
    public String toJson() {

        return new Gson().toJson(this);
    }

    public static PublicMessage fromJson(String json){
        return new Gson().fromJson(json, PublicMessage.class);
    }*/
}
