package com.pany.withrooms.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "careers_table")
data class Career constructor(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")
        @Expose
        var careerId: Int?,
        @SerializedName("developers")
        @Expose
        @NonNull
        var developers: List<Developer>,
        @SerializedName("details")
        @Expose
        @NonNull
        var details: Details
)