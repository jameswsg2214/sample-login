package com.work.sploot.Entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "Tbluser")
data class other(
    @ColumnInfo(name = "userId")
    @PrimaryKey(autoGenerate = true)
    var userId: Long? =null,
    @ColumnInfo(name = "username") var username: String? =null,
    @ColumnInfo(name = "email") var email: String? =null,
    @ColumnInfo(name = "password") var password: Date? =null
): Parcelable
