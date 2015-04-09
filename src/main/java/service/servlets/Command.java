package main.java.service.servlets;

/**
 * Enumeration which contains different types of command.
 *
 * @author vladimir.tikhomirov
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
