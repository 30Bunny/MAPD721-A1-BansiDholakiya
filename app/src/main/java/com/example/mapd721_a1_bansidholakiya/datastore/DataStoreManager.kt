package com.example.mapd721_a1_bansidholakiya.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class DataStoreManager(private val context: Context) {

    // Companion object to create a single instance of DataStore for username, email and id
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserEmail")

        // Keys to uniquely identify username, email and id in DataStore
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    suspend fun getEmail(): String {
        val preferences = context.dataStore.data.first()
        return preferences[USER_EMAIL_KEY] ?: ""
    }

    suspend fun getID(): String {
        val preferences = context.dataStore.data.first()
        return preferences[USER_ID_KEY] ?: ""
    }

    suspend fun getUserName(): String {
        val preferences = context.dataStore.data.first()
        return preferences[USER_NAME_KEY] ?: ""
    }

    // Function to save user email and password in DataStore
    suspend fun saveUserData(email: String, id: String, username: String) {
        // Use the DataStore's edit function to make changes to the stored preferences
        context.dataStore.edit { preferences ->
            // Update the username, email and id values in the preferences
            preferences[USER_EMAIL_KEY] = email
            preferences[USER_ID_KEY] = id
            preferences[USER_NAME_KEY] = username
        }
    }

    suspend fun clearUserDetail() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
