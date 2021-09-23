package com.example.mobileapplication;
import android.provider.BaseColumns;

public final class TicketsMaster {
    private  TicketsMaster() {};



    public static class Tickets implements  BaseColumns{
        public static  final  String TABLE_NAME = "tickets";
        public static  final  String COLUMN_NAME_ID = "Id";
        public static  final  String COLUMN_NAME_TEXT = "Text";
        public static  final  String COLUMN_NAME_STATUS = "Status";
    }
}
