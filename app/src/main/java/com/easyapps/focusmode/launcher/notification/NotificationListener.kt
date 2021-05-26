package com.easyapps.focusmode.launcher.notification

import android.os.Handler
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.easyapps.focusmode.launcher.utils.Utils

class NotificationListener : NotificationListenerService() {

    private var connected: Boolean = false
    private val handler = Handler()

    override fun onListenerConnected() {
        super.onListenerConnected()
        connected = true
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if (!connected || Utils.getFocusProgress(this) >= 100 || !Utils.getDndOption(this)) {
            return
        }
        handler.removeCallbacksAndMessages(null)
        handler.post(runnable)
    }

    private val runnable = Runnable {
        if (!connected) {
            return@Runnable
        }
        val packagesSelected = Utils.getExhaustiveSelectedApps(this).map {
            it.appInfo.packageName
        }
        val activeNonSelectedNotification = activeNotifications.filter {
            !packagesSelected.contains(it.packageName) &&
                    Utils.hasLauncherIntent(context = this, packageName = it.packageName)
        }
        val keys = activeNonSelectedNotification.map {
            it.key
        }

        if (keys.count() > 0) {
            cancelNotifications(keys.toTypedArray())
        } else {
            // no notification cancelled
        }
    }

    override fun onNotificationRankingUpdate(rankingMap: RankingMap?) {
        super.onNotificationRankingUpdate(rankingMap)
        if (!connected) {
            return
        }
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed(runnable, 1000)
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.i("mayank", " listener disconnected")
        connected = false
    }

}