package me.chrislewis.mentorship.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("Message")
public class Message extends ParseObject{
    private final static String USER_KEY = "user";
    private final static String USER_ID_KEY = "userId";
    private final static String BODY_KEY = "body";
    private final static String RECIPIENT_KEY = "recipients";
    private final static String CREATED_AT_KEY = "createdAt";

    static ArrayList<ParseUser> recipientUsers = new ArrayList<>();


    public String getBody() {
        return getString(BODY_KEY);
    }

    public void setBody(String body) {
        put(BODY_KEY, body);
    }

    public ParseUser getUser() {
        return getParseUser(USER_KEY);
    }

    public void setUser(ParseUser user) {
        put(USER_KEY, user);
    }

    public ParseUser getRecicpent() {
        return getParseUser(RECIPIENT_KEY);
    }

    public void addRecipient(ArrayList<String> users) {
        addAllUnique(RECIPIENT_KEY, users);
    }

    public static class Query extends ParseQuery<Message> {
        public Query() {
            super(Message.class);
        }
        public Query getTop() {
            orderByDescending(CREATED_AT_KEY);
            setLimit(50);
            return this;
        }
        public Query withUser() {
            include(USER_KEY);
            return this;
        }

        public Query recipients(List<String> users) {
            try {
//                whereEqualTo(RECIPIENT_KEY, users);
                whereContainsAll(RECIPIENT_KEY, users);
                return this;
            } catch (Exception e) {
                return null;
            }
        }
    }
}
