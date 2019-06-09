package com.example.serdar.smartsmsbox;

import java.util.Date;

public class Message {

    String sender;
    String msgBody;
    String date;
    int ID;
    int type;

    public static final String TABLE_NAME = "messages";
    public static final String COLUMN_SENDER = "Sender";
    public static final String COLUMN_MSGBODY = "msgBody";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_ID = "ID";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_SENDER + " TEXT,"
                    + COLUMN_MSGBODY + " TEXT,"
                    + COLUMN_DATE + " DATE,"
                    + COLUMN_TYPE + " TYPE,"
                    + COLUMN_ID + " INTEGER,"
                    + ")";

    public Message(String sender, String msgBody, String date, int ID, int type){
        this.sender = sender;
        this.msgBody = msgBody;
        this.date = date;
        this.ID = ID;
        this.type = type;
    }
    public Message(){
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public String getDate() {
        return date;
    }

    public int getID() {
        return ID;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
