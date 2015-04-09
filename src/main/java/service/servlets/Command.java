package main.java.service.servlets;

/**
 * Created by vladt89 on 9.4.2015.
 */
public enum Command {
    CREATE_MESSAGE ("create message"),
    LIST_MESSAGES ("list messages"),
    CREATE_NEW_MESSAGE ("create new message");

    private String value;
    Command(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
