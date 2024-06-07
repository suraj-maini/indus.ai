package com.boilerplate.app.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class TokenManager @Inject constructor(
    @ApplicationContext context: Context,
    private val encryptedPrefs: EncryptedPrefs
) {

    var authToken: String?
        get() = encryptedPrefs.getString("authToken")
        set(value) = encryptedPrefs.putString("authToken", value)

    var refreshToken: String?
        get() = encryptedPrefs.getString("refreshToken")
        set(value) = encryptedPrefs.putString("refreshToken", value)

    fun clearTokens() {
        encryptedPrefs.remove("authToken")
        encryptedPrefs.remove("refreshToken")
    }
}
