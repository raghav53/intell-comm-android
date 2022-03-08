package com.intell.comm.localDatabase.sharePreferenace

import android.content.SharedPreferences
import com.intell.comm.model.RegisterLoginModel
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class SharedPrefImpl(var sharedPreferences: SharedPreferences) : SharedPref() {

    override fun putUserData(value: RegisterLoginModel?) {
        sharedPreferences.edit().putString("user_data", Gson().toJson(value)).apply()
    }

    override fun getUserData(): RegisterLoginModel {
        if (sharedPreferences.getString("user_data", null) == null) {
            return RegisterLoginModel()
        }
        return try {
            Gson().fromJson(
                sharedPreferences.getString("user_data", null),
                RegisterLoginModel::class.java
            )
        } catch (e: JsonSyntaxException) {
            RegisterLoginModel()
        }
    }

    override fun putInt(key: String?, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String?, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun putFloat(key: String?, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    override fun getFloat(key: String?, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    override fun putBoolean(key: String?, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun putLong(key: String?, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun getLong(key: String?, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun putString(key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String?, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    override fun delete(key: String?) {
        sharedPreferences.edit().remove(key).apply()
    }

    override fun deleteAll() {
        sharedPreferences.edit().clear().apply()
    }

}