package com.intell.comm.localDatabase.sharePreferenace

import com.intell.comm.model.RegisterLoginModel

abstract class SharedPref {

    /**
     *
     * @param value setUser profile bean
     */
    abstract fun putUserData(value: RegisterLoginModel?)
    /**
     *
     * @return user profile bean
     */
    abstract fun getUserData(): RegisterLoginModel
    /**
     * Put [Int]
     *
     * @param key unique key
     * @param value integer value
     */
    abstract fun putInt(key: String?, value: Int)
    /**
     * Get [Int]
     *
     * @param key
     * @param defaultValue
     * @return
     */
    abstract fun getInt(key: String?, defaultValue: Int): Int
    /**
     * Put [Float]
     *
     * @param key
     * @param value
     */
    abstract fun putFloat(key: String?, value: Float)
    /**
     * Get [Float]
     *
     * @param key
     * @param defaultValue
     * @return
     */
    abstract fun getFloat(key: String?, defaultValue: Float): Float
    /**
     * Put [Boolean]
     *
     * @param key
     * @param value
     */
    abstract fun putBoolean(key: String?, value: Boolean)
    /**
     * Get [Boolean]
     *
     * @param key
     * @param defaultValue
     * @return
     */
    abstract fun getBoolean(key: String?, defaultValue: Boolean): Boolean
    /**
     * Put [Long]
     *
     * @param key
     * @param value
     */
    abstract fun putLong(key: String?, value: Long)
    /**
     * Get [Long]
     *
     * @param key
     * @param defaultValue
     * @return
     */
    abstract fun getLong(key: String?, defaultValue: Long): Long
    /**
     * Put [String]
     *
     * @param key
     * @param value
     */
    abstract fun putString(key: String?, value: String?)
    /**
     * Get [String]
     *
     * @param key
     * @param defaultValue
     * @return
     */
    abstract fun getString(key: String?, defaultValue: String?): String?
    /**
     * Delete data specified by key
     *
     * @param key
     */
    abstract fun delete(key: String?)
    /**
     * Delete all data from shared preferences
     */
    abstract fun deleteAll()
}