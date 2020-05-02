package com.pany.withrooms.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.pany.withrooms.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

@Database(entities = [Company::class, Career::class, Details::class, Developer::class], version = 15, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DeveloperRoomDatabase : RoomDatabase() {

    abstract fun developerDao(): DeveloperDao

    companion object {
        @Volatile
        private var INSTANCE: DeveloperRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): DeveloperRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DeveloperRoomDatabase::class.java,
                        "developer_database"
                )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .addCallback(
                            DeveloperDatabaseCallback(
                                context,
                                scope
                            )
                        )
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class DeveloperDatabaseCallback(
                private val context: Context,
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        val jsonObj = JsonParser().parse(
                            readJSONFromAsset(
                                context
                            )
                        ).asJsonObject

                        val companyType = object : TypeToken<Company>() {}.type
                        val company: Company = Gson().fromJson(jsonObj, companyType)

                        populateDatabase(
                            database,
                            company
                        )
                    }
                }
            }
        }

        // Populate the database from company.json file - needs to be in a new coroutine
        // If needed, you can parse the fields you want from the database and use them
        // This example shows a list by the Developer object/class

        fun populateDatabase(database: DeveloperRoomDatabase, company: Company) {
            val developerDao = database.developerDao()

            // Empty database on first load
            developerDao.deleteAll()

            val developerList = company.career?.developers
            developerList?.forEach {
                developerDao.insert(Developer(it.developer))
            }
        }

        fun readJSONFromAsset(context: Context): String {
            val json: String
            try {
                val inputStream: InputStream = context.assets.open(context.getString(
                    R.string.companyRes
                ))
                json = inputStream.bufferedReader().use {
                    it.readText()
                }
            } catch (ex: Exception) {
                ex.localizedMessage
                return  ""
            }
            return json
        }
    }
}

