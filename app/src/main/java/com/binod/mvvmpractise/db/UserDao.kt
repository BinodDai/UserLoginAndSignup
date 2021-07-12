package com.binod.mvvmpractise.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binod.mvvmpractise.db.entities.Current_user_id
import com.binod.mvvmpractise.db.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:User):Long

    @Query("select *from user where uid= $Current_user_id")
    fun getUser():LiveData<User>
}