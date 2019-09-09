package com.twi.moviesdecade.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.util.Patterns
import com.twi.moviesdecade.presentation.base.BaseActivity
import com.twi.moviesdecade.utils.constants.PrefrenceConstants
import java.util.*


object AppUtil {

    private const val LANGUAGE = "language"

    fun getLanguage(context: Context): String {
        return if (isArabic(context)) {
            LogUtils.d("shit--> app lang is %s", "arabic")
            "ar"
        } else {
            LogUtils.d("shit--> app lang is %s", "english")
            "en"
        }
    }

    fun setLanguage(language: String, context: Context) {
        val editor =
            context.getSharedPreferences(PrefrenceConstants.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE).edit()
        editor.putString(LANGUAGE, language)
        editor.commit()
        LogUtils.d("shit--> set app lang is %s", language)
    }

    fun updateCurrentLanguage(context: Context): Context {
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
        val language = preferenceManager.getString(LANGUAGE, "en")

        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
        return context
    }

    fun isArabic(context: Context): Boolean {
        val prefs =  context.getSharedPreferences(PrefrenceConstants.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
        val locale = prefs.getString(LANGUAGE, "en")
        return locale!!.contains("ar") || locale.equals("ar", ignoreCase = true)
    }

    fun isLanguageSelectedBefore(context: Context): Boolean {
        val prefs =  context.getSharedPreferences(PrefrenceConstants.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
        val locale = prefs.getString(LANGUAGE, null)
        return locale != null
    }


    private fun getLocaleLanguage(context: Context): String {
        val locale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales.get(0)
        } else {
            context.resources.configuration.locale
        }
        return locale.toString()
    }


    fun getUriPath(uri: Uri, mContext: Context): String {

        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = mContext.contentResolver.query(uri, projection, null, null, null)

        var columnIndex: Int
        return if (cursor != null) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            val path = cursor.getString(columnIndex)
            cursor.close()
            path
        } else {
            ""
        }

    }


    fun isEmail(s: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(s).matches()

    }

    fun isMobile(s: String): Boolean {
        return Patterns.PHONE.matcher(s).matches()

    }

    fun openGallery(context: BaseActivity<*>, requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivityForResult(intent, requestCode)
        }
    }

    fun dial(mobileNumber: String, mContext: Context) {
        val number = Uri.parse("tel:$mobileNumber")
        val callIntent = Intent(Intent.ACTION_DIAL, number)
        mContext.startActivity(callIntent)
    }

    fun openMap(latitude: Double, longitude: Double, mContext: Context) {
        val uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        mContext.startActivity(intent)
    }

    fun openURL(link: String, mContext: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        mContext.startActivity(intent)
    }

    fun openMail(mail: String, feedback: String, mContext: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_EMAIL, arrayOf(mail))
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, feedback)
        mContext.startActivity(intent)
    }


    fun share(text: String, mContext: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        mContext.startActivity(intent)
    }
}
