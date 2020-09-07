package com.afterapps.wett.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

private const val FILE_NAME = "secure_shared_prefs"
private const val IS_UNIT_METRIC_KEY = "unit_key"

class PreferencesHelper(context: Context) {

    private val securePrefs: SharedPreferences by lazy {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return@lazy EncryptedSharedPreferences.create(
            FILE_NAME,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun isUnitMetric() = securePrefs.getBoolean(IS_UNIT_METRIC_KEY, true)
    fun changeUnit() = securePrefs.edit().putBoolean(IS_UNIT_METRIC_KEY, !isUnitMetric()).apply()
}