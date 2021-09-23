package com.example.mobileapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tickets.db";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TicketsMaster.Tickets.TABLE_NAME + " (" +
                TicketsMaster.Tickets.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TicketsMaster.Tickets.COLUMN_NAME_TEXT + " TEXT," +
                TicketsMaster.Tickets.COLUMN_NAME_STATUS + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addInfo(String text, String status){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TicketsMaster.Tickets.COLUMN_NAME_TEXT, text);
        values.put(TicketsMaster.Tickets.COLUMN_NAME_STATUS, status);

        long newRowId = db.insert(TicketsMaster.Tickets.TABLE_NAME, null, values);
    }

    public List fetchTicketsForType(String status){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                TicketsMaster.Tickets.COLUMN_NAME_ID,
                TicketsMaster.Tickets.COLUMN_NAME_STATUS,
                TicketsMaster.Tickets.COLUMN_NAME_TEXT
        };
        String selection = TicketsMaster.Tickets.COLUMN_NAME_STATUS + " = '" + status + "'";
        String sortOrder = TicketsMaster.Tickets.COLUMN_NAME_ID + " DESC";

        Cursor cursor = db.query(TicketsMaster.Tickets.TABLE_NAME, projection, selection, null, null, null, sortOrder);

        List<Ticket> listOfTickets = new ArrayList<>();

        while(cursor.moveToNext()){
            Ticket ticketObj = new Ticket();

            ticketObj.Id = cursor.getInt(cursor.getColumnIndexOrThrow(TicketsMaster.Tickets.COLUMN_NAME_ID));
            ticketObj.Text = cursor.getString(cursor.getColumnIndexOrThrow(TicketsMaster.Tickets.COLUMN_NAME_TEXT));
            ticketObj.Status = cursor.getString(cursor.getColumnIndexOrThrow(TicketsMaster.Tickets.COLUMN_NAME_STATUS));

            listOfTickets.add(ticketObj);
        }

        cursor.close();

        return  listOfTickets;
    }


}
