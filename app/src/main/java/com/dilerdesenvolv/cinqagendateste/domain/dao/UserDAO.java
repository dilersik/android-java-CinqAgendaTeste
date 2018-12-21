package com.dilerdesenvolv.cinqagendateste.domain.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dilerdesenvolv.cinqagendateste.domain.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM users ORDER BY id DESC")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE email = :email AND senha = :senha")
    User getAuth(String email, String senha);

    @Query("SELECT * FROM users WHERE id = :id")
    User getById(Integer id);

    @Delete
    void delete(User user);

}
