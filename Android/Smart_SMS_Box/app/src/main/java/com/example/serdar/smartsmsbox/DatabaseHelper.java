package com.example.serdar.smartsmsbox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "messages_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Message.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Message.TABLE_NAME);
        onCreate(db);
    }

    public long insertMessage (String sender, String msgBody, String date, int ID, int type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Message.COLUMN_SENDER, sender);
        values.put(Message.COLUMN_MSGBODY, msgBody);
        values.put(Message.COLUMN_DATE, date);
        values.put(Message.COLUMN_ID, ID);
        values.put(Message.COLUMN_TYPE, type);
        long id = db.insert(Message.TABLE_NAME, null, values);
        db.close();
        Log.d(TAG, "Sender: " + sender + " Message: " + msgBody + " Date: " + date);
        return id;
    }

    public Message getMessages (String sender) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Message.TABLE_NAME,
                new String[]{Message.COLUMN_SENDER, Message.COLUMN_MSGBODY, Message.COLUMN_DATE, Message.COLUMN_ID, Message.COLUMN_TYPE},
                Message.COLUMN_SENDER + "=?",
                new String[]{sender}, null, null, null, null);

        if (cursor.getCount() <= 0){
            //Log.d(TAG, "Cursor null. " + username + " bulunamadı.");
            return null;
        }
        if (cursor.moveToFirst()){
            Message message = new Message(
                    cursor.getString(cursor.getColumnIndex(Message.COLUMN_SENDER)),
                    cursor.getString(cursor.getColumnIndex(Message.COLUMN_MSGBODY)),
                    cursor.getString(cursor.getColumnIndex(Message.COLUMN_DATE)),
                    cursor.getInt(cursor.getColumnIndex(Message.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(Message.COLUMN_TYPE)));
            cursor.close();
            Log.d(TAG, sender + " var ve başarıyla bilgileri alındı.");
            return message;
        } else {
            return null;
        }
    }

    public ArrayList<Message> getAllNotes() {
        ArrayList<Message> messages = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Message.TABLE_NAME + " ORDER BY " +
                Message.COLUMN_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setSender(cursor.getString(cursor.getColumnIndex(Message.COLUMN_SENDER)));
                message.setMsgBody(cursor.getString(cursor.getColumnIndex(Message.COLUMN_MSGBODY)));
                message.setDate(cursor.getString(cursor.getColumnIndex(Message.COLUMN_DATE)));
                message.setID(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_ID)));
                message.setType(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_TYPE)));
                messages.add(message);
            } while (cursor.moveToNext());
        }
        db.close();
        return messages;
    }

    public int getMessagesCount() {
        String countQuery = "SELECT  * FROM " + Message.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    /*

    public int updateUserScore (Message message) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Message.COLUMN_SCORE, message.getScore());
        return db.update(User.TABLE_NAME, values, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }
    */

    public void deleteMessage (Message message) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Message.TABLE_NAME, Message.COLUMN_ID + " = ?",
                new String[]{String.valueOf(message.getID())});
        db.close();
    }

    public ArrayList<Message> getPersonalMessages() {
        ArrayList<Message> messages = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Message.TABLE_NAME + " ORDER BY " +
                Message.COLUMN_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setSender(cursor.getString(cursor.getColumnIndex(Message.COLUMN_SENDER)));
                message.setMsgBody(cursor.getString(cursor.getColumnIndex(Message.COLUMN_MSGBODY)));
                message.setDate(cursor.getString(cursor.getColumnIndex(Message.COLUMN_DATE)));
                message.setID(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_ID)));
                message.setType(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_TYPE)));
                messages.add(message);
            } while (cursor.moveToNext());
        }
        db.close();
        return messages;
    }

    public ArrayList<Message> getCommercialMessages() {
        ArrayList<Message> messages = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Message.TABLE_NAME + " ORDER BY " +
                Message.COLUMN_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setSender(cursor.getString(cursor.getColumnIndex(Message.COLUMN_SENDER)));
                message.setMsgBody(cursor.getString(cursor.getColumnIndex(Message.COLUMN_MSGBODY)));
                message.setDate(cursor.getString(cursor.getColumnIndex(Message.COLUMN_DATE)));
                message.setID(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_ID)));
                message.setType(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_TYPE)));
                messages.add(message);
            } while (cursor.moveToNext());
        }
        db.close();
        return messages;
    }

    public ArrayList<Message> getOTPMessages() {
        ArrayList<Message> messages = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Message.TABLE_NAME + " ORDER BY " +
                Message.COLUMN_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setSender(cursor.getString(cursor.getColumnIndex(Message.COLUMN_SENDER)));
                message.setMsgBody(cursor.getString(cursor.getColumnIndex(Message.COLUMN_MSGBODY)));
                message.setDate(cursor.getString(cursor.getColumnIndex(Message.COLUMN_DATE)));
                message.setID(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_ID)));
                message.setType(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_TYPE)));
                messages.add(message);
            } while (cursor.moveToNext());
        }
        db.close();
        return messages;
    }

    public ArrayList<Message> getSpamMessages() {
        ArrayList<Message> messages = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Message.TABLE_NAME + " ORDER BY " +
                Message.COLUMN_DATE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Message message = new Message();
                message.setSender(cursor.getString(cursor.getColumnIndex(Message.COLUMN_SENDER)));
                message.setMsgBody(cursor.getString(cursor.getColumnIndex(Message.COLUMN_MSGBODY)));
                message.setDate(cursor.getString(cursor.getColumnIndex(Message.COLUMN_DATE)));
                message.setID(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_ID)));
                message.setType(cursor.getInt(cursor.getColumnIndex(Message.COLUMN_TYPE)));
                messages.add(message);
            } while (cursor.moveToNext());
        }
        db.close();
        return messages;
    }
}
