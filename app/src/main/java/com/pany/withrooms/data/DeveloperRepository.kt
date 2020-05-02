package com.pany.withrooms.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pany.withrooms.data.Developer
import com.pany.withrooms.data.DeveloperDao

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class DeveloperRepository(private val developerDao: DeveloperDao) {

    // LiveData observer will notify when the data has changed
    // Room executes all the queries on a separate threads
    val allDevelopers: LiveData<List<Developer>> = developerDao.getDevelopers()

    //Insertion has to be on a non-UI thread - in opposite applicaiton will crash. So we're making this a
    // suspend function so the caller methods know this.

    // For insertion here we're using "suspend" keyword for Coroutines
    // This way the caller methods are aware of using of non-UI threads

    // AsyncTask is also one of alternatives

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(developer: Developer) {
        developerDao.insert(developer)
    }
}
