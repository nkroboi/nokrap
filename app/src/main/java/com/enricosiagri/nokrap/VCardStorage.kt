package com.nkrolabs.nokrap

import android.content.Context
import com.google.gson.Gson
import androidx.core.content.edit
object VCardStorage {
    private const val PREFS_NAME = "vcards"

    fun save(context: Context, vCard: VCard) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val key = vCard.label
        prefs.edit {
            putString(key, Gson().toJson(vCard))
        }
    }

    fun loadAll(context: Context): List<VCard> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.all.values.mapNotNull {
            try {
                Gson().fromJson(it as String, VCard::class.java)
            } catch (_: Exception) {
                null
            }
        }
    }

    fun getByLabel(context: Context, label: String): VCard? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(label, null)?.let {
            Gson().fromJson(it, VCard::class.java)
        }
    }

    fun deleteByLabel(context: Context, label: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            remove(label)
        }
    }
}