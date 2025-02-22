package com.example.castvideoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManagerListener
import com.google.android.gms.cast.framework.media.RemoteMediaClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status

class MainActivity : AppCompatActivity() {
    private lateinit var castContext: CastContext
    private lateinit var sessionManagerListener: SessionManagerListener<CastSession>
    private var remoteMediaClient: RemoteMediaClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize CastContext
        castContext = CastContext.getSharedInstance(this)

        // Set up MediaRouteButton
        val mediaRouteButton = findViewById<MediaRouteButton>(R.id.mediaRouteButton)
        CastButtonFactory.setUpMediaRouteButton(this, mediaRouteButton)

        Log.d("CastApp", "hello world")

        // Set up session manager listener
        sessionManagerListener = object : SessionManagerListener<CastSession> {
            override fun onSessionStarting(session: CastSession) {
                Log.d("CastApp", "Session starting")
            }

            override fun onSessionStarted(session: CastSession, sessionId: String) {
                Log.d("CastApp", "Session started")
                remoteMediaClient = session.remoteMediaClient
                remoteMediaClient?.let { loadMedia(it) }
            }

            override fun onSessionEnding(session: CastSession) {
                Log.d("CastApp", "Session ending")
            }

            override fun onSessionResumed(session: CastSession, wasSuspended: Boolean) {
                Log.d("CastApp", "Session resumed")
                remoteMediaClient = session.remoteMediaClient
            }

            override fun onSessionResumeFailed(session: CastSession, error: Int) {
                Log.e("CastApp", "Session resume failed")
            }

            override fun onSessionEnded(session: CastSession, error: Int) {
                Log.d("CastApp", "Session ended")
                remoteMediaClient = null
            }

            override fun onSessionStartFailed(session: CastSession, error: Int) {
                Log.e("CastApp", "Session start failed")
            }

            override fun onSessionSuspended(session: CastSession, reason: Int) {
                Log.d("CastApp", "Session suspended")
            }

            override fun onSessionResuming(session: CastSession, sessionId: String) {
                Log.d("CastApp", "Session resuming")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        castContext.sessionManager.addSessionManagerListener(
            sessionManagerListener,
            CastSession::class.java
        )

        // Check for active Chromecast session
        val currentSession = castContext.sessionManager.currentCastSession
        if (currentSession == null || !currentSession.isConnected) {
            Log.d("CastApp", "No active Chromecast session.")
        } else {
            Log.d("CastApp", "Active Chromecast session found.")
        }
    }

    override fun onPause() {
        super.onPause()
        castContext.sessionManager.removeSessionManagerListener(
            sessionManagerListener,
            CastSession::class.java
        )
    }

    private fun loadMedia(remoteMediaClient: RemoteMediaClient) {
        val mediaInfo = com.google.android.gms.cast.MediaInfo.Builder("https://videolink-test.mycdn.me/?pct=1&sig=6QNOvp0y3BE&ct=0&clientType=45&mid=193241622673&type=5")
            .setContentType("video/mp4")
            .setStreamType(com.google.android.gms.cast.MediaInfo.STREAM_TYPE_BUFFERED)
            .build()

        remoteMediaClient.load(mediaInfo, true).setResultCallback(object : ResultCallback<RemoteMediaClient.MediaChannelResult> {
            override fun onResult(result: RemoteMediaClient.MediaChannelResult) {
                val status: Status = result.status
                if (status.isSuccess) {
                    Log.d("CastApp", "Media loaded successfully")
                } else {
                    Log.e("CastApp", "Failed to load media: ${status.statusMessage}")
                }
            }
        })
    }
}