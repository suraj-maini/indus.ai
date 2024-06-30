package com.boilerplate.app.utils

import android.content.Context
import android.content.SharedPreferences
import com.boilerplate.app.BuildConfig
import com.boilerplate.app.data.models.auth.model.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "app_prefs"
    }

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun saveInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun saveUser(user: LoginResponse): LoginResponse {
        user.id?.let { saveInt("id", it) }
        user.firstName?.let { saveString("first_name", it) }
        user.lastName?.let { saveString("last_name", it) }
        user.name?.let { saveString("name", it) }
        user.email?.let { saveString("email", it) }
        user.avatarUrl?.let { saveString("avatar_url", it) }
        user.isAllow?.let { saveBoolean("is_allow", it) }
        user.isBlocked?.let { saveInt("is_blocked", it) }
        user.join?.let { saveString("join", it) }
        user.plan?.let { saveString("plan", it) }
        saveBoolean("isLoggedIn", user.isLoggedIn)
        return getUser()
    }

    fun getUser(): LoginResponse {
        return LoginResponse(
            id = getInt("id"),
            firstName = getString("first_name"),
            lastName = getString("last_name"),
            name = getString("name"),
            email = getString("email"),
            avatarUrl = getString("avatar_url"),
            isAllow = getBoolean("is_allow"),
            isBlocked = getInt("is_blocked"),
            join = getString("join"),
            plan = getString("plan"),
            isLoggedIn = getBoolean("isLoggedIn")
        )
    }

}
