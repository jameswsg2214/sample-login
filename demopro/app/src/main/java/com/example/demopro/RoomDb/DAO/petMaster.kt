package com.work.sploot.DAO;

import androidx.room.*
import com.work.sploot.Entity.*
import java.util.*
import java.util.function.BinaryOperator
import kotlin.collections.ArrayList


@Dao
interface petMasterDao {



    //others

    @Query("SELECT * FROM Tbluser order by userId desc")
    fun getall(): List<other>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert_other(vararg todo: other)


    @Query("SELECT * FROM Tbluser where username=:username order by userId desc")
    fun check_username(username:String): Boolean

    @Query("SELECT * FROM Tbluser where password=:password order by userId desc")
    fun check_password(password:String): Boolean





}