package com.example.roomdatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addUser(Users users);


    @Update
    public void updateUser(Users users);

    @Delete
    public void deleteUser(Users users);


    @Query("Select * from USERS")
    public List<Users> getUser();
}

