package com.example.films_otus.notification

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val intent = Intent(INTENT_FILTER)

        remoteMessage.data.forEach{ entry ->

        intent.putExtra(entry.key, entry.value)

            sendBroadcast(intent)

        }
    }

    companion object {

        const val INTENT_FILTER = "PUSH_EVENT"
        const val KEY_ACTION = "action"
        const val KEY_MESAGGE = "message"

        const val ACTION_SHOW_MESSAGE = "action_show_message"
    }
}