package com.twi.moviesdecade.data.local


import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class PreferencesManager(private val sharedPreferences: SharedPreferences) {
    private val editor: SharedPreferences.Editor = this.sharedPreferences.edit()

    fun getString(key: String): String? {
        return this.getString(key, null)
    }

    fun getString(key: String, defaultValue: String?): String? {
        return this.sharedPreferences.getString(key, defaultValue)
    }

    fun saveString(key: String, value: String) {
        this.editor.putString(key, value).commit()
    }

    fun getInt(key: String, defaultVal: Int): Int {
        return this.sharedPreferences.getInt(key, defaultVal)
    }

    fun saveInt(key: String, value: Int) {
        this.editor.putInt(key, value).commit()
    }

    fun getBoolean(key: String): Boolean {
        return this.getBoolean(key, false)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return this.sharedPreferences.getBoolean(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        this.editor.putBoolean(key, value).commit()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return this.sharedPreferences.getLong(key, defaultValue)
    }

    fun getLong(key: String): Long {
        return this.sharedPreferences.getLong(key, 0L)
    }

    operator fun contains(key: String): Boolean {
        return this.sharedPreferences.contains(key)
    }

    fun removeEntry(key: String) {
        this.editor.remove(key).commit()
    }

    fun clearSharedPreferences() {
        this.sharedPreferences.edit().clear().apply()
    }

    fun saveObject(key: String, model: Any) {
        editor.putString(key, Gson().toJson(model))
        editor.commit()
    }

    fun clearEntry(key : String) {
        editor.remove(key)
        editor.commit()
    }

    fun <Model> getObject(key: String, modelClass: Class<Model>): Model  {
        val json = this.sharedPreferences.getString(key, null)
        val gson = Gson()
        return gson.fromJson(json, modelClass)
    }

    fun <T> setList(key: String, list: List<T>) {
        this.editor.putString(key, Gson().toJson(list))
        editor.commit()
    }

    fun <T> getList(key: String, cls: Class<T>): List<T>? {
        var list: List<T> = ArrayList()
        val json = this.getString(key, null)
        try {
            list = Gson().fromJson(json, object : TypeToken<List<T>>() {

            }.type)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return list
    }

    fun <T> getList(key: String, type: Type): List<T>? {
        return if (this.getString(key, null) != null) {
            Gson().fromJson<List<T>>(this.getString(key, null), type)
        } else {
            null
        }
    }
}

