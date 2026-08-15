package com.kundutechstudio.database.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import android.content.Context


actual fun createDataStore(context: Any?): DataStore<Preferences> {

    return createDataStorePref {
        (context as Context)
            .filesDir
            .resolve(dataStoreFileName)
            .absolutePath
    }

}