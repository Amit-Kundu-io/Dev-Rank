/**
 * DRDataStore.kt
 *
 * Author      : Amit Kundu
 * Created On  : 15/08/2026
 *
 * Description :
 * Part of the project codebase. This file contributes to the overall
 * functionality and follows standard coding practices and architecture.
 *
 * Notes :
 * Ensure changes are consistent with project guidelines and maintain
 * code readability and quality.
 */

package com.kundutechstudio.database.datastore


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map


class DRDataStore (
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        private val USER_ID = stringPreferencesKey("userId")
        private val TOKEN = stringPreferencesKey("token")
    }


    val userId = dataStore.data.map { preferences -> preferences[USER_ID] }
    suspend fun setUserId(id: String) { dataStore.edit { preferences -> preferences[USER_ID] = id }}


    val token = dataStore.data.map { preferences -> preferences[TOKEN] }
    suspend fun setToken(id: String) { dataStore.edit { preferences -> preferences[TOKEN] = id }}



}