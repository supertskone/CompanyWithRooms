package com.pany.withrooms.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "company_table")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class Company(
        @PrimaryKey
        @SerializedName("id")
        @Expose
        var id: String,
        @SerializedName("email")
        @Expose
        var email: String?,
        @SerializedName("career")
        @Expose
        @Embedded
        var career: Career? = null
)