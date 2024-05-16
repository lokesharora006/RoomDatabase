package com.example.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Users.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {


    public abstract MyDao myDao();
}
