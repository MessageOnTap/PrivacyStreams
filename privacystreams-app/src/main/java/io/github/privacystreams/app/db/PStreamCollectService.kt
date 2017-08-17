package io.github.privacystreams.app.db

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import io.github.privacystreams.app.Config
import io.github.privacystreams.app.NavActivity
import io.github.privacystreams.app.R

/**
 * The PrivacyStreams always-on service for collecting historic data.
 */

class PStreamCollectService : Service() {
    companion object {
        val START_TABLE_KEY = "start_table_name"
        val STOP_TABLE_KEY = "stop_table_name"
        val ALL_TABLES = "all_tables"
        val LAST_TABLES = "last_tables"
        private val ONGOING_NOTIFICATION_ID = 1

        private fun startService(context: Context, extraKey: String, extraValue: String) {
            val collectServiceIntent = Intent(context, PStreamCollectService::class.java)
            collectServiceIntent.putExtra(extraKey, extraValue)
            context.startService(collectServiceIntent)
        }

        fun startAllTables(context: Context) = startService(context, START_TABLE_KEY, ALL_TABLES)
        fun stopAllTables(context: Context) = startService(context, STOP_TABLE_KEY, ALL_TABLES)
        fun startTable(context: Context, tableName: String) = startService(context, START_TABLE_KEY, tableName)
        fun stopTable(context: Context, tableName: String) = startService(context, STOP_TABLE_KEY, tableName)

        fun start(context: Context) = context.startService(Intent(context, PStreamCollectService::class.java))
    }

    internal lateinit var dbHelper: PStreamDBHelper
    internal lateinit var dbTables: List<PStreamTable>
    internal lateinit var activeTables: Set<String>

    override fun onCreate() {
        dbHelper = PStreamDBHelper.getInstance(this)
        dbTables = dbHelper.tables

        val notificationIntent = Intent(this, NavActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(NavActivity.NAV_ID_KEY, R.id.nav_data)
        notificationIntent.putExtras(bundle)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = Notification.Builder(this)
                .setContentTitle(getText(R.string.collect_notification_title))
                .setContentText(getText(R.string.collect_notification_text))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setTicker(getText(R.string.collect_notification_title))
                .build()

        startForeground(ONGOING_NOTIFICATION_ID, notification)

        val pref = applicationContext.getSharedPreferences(Config.APP_NAME, Context.MODE_PRIVATE)
        activeTables = pref.getStringSet(LAST_TABLES, HashSet<String>())
        Log.d(Config.APP_NAME, "Loaded last active tables: " + activeTables)
    }

    override fun onDestroy() {
        dbTables.forEach { it.stopCollecting() }
        stopForeground(true)
        dbHelper.close()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val newActiveTables = HashSet<String>(activeTables)

        val stopTableName = intent.getStringExtra(STOP_TABLE_KEY)
        if (stopTableName == ALL_TABLES) {
            newActiveTables.clear()
        } else if (stopTableName != null) {
            newActiveTables.remove(stopTableName)
        }

        val startTableName = intent.getStringExtra(START_TABLE_KEY)
        if (startTableName == ALL_TABLES) {
            dbTables.forEach { newActiveTables.add(it.tableName) }
        } else if (startTableName != null) {
            newActiveTables.add(startTableName)
        }

        if (newActiveTables != activeTables) {
            activeTables = newActiveTables
            val pref = applicationContext.getSharedPreferences(Config.APP_NAME, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putStringSet(LAST_TABLES, activeTables)
            editor.apply()
            Log.d(Config.APP_NAME, "Saved active tables: " + activeTables)
        }

        dbTables.forEach { if (it.tableName in activeTables) it.startCollecting() else it.stopCollecting() }
        if (activeTables.isEmpty()) this.stopSelf()

        return Service.START_STICKY
    }

}
