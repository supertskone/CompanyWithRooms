package com.pany.withrooms.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "details_table")
data class Details constructor(
        @PrimaryKey
        @ColumnInfo(name = "technologies")
        @SerializedName("technologies")
        @Expose
        var technologies: List<String>,
        @SerializedName("employeeLevels")
        @Expose
        @ColumnInfo(name = "employeeLevels")
        var employeeLevels: List<String>
)