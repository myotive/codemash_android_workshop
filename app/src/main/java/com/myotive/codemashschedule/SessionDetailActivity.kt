package com.myotive.codemashschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.myotive.codemashschedule.api.entity.SessionData
import com.myotive.codemashschedule.recyclerview.SpeakerImageAdapter
import com.myotive.codemashschedule.utilities.toShortDate
import com.myotive.codemashschedule.utilities.toTime
import kotlinx.android.synthetic.main.activity_speaker_detail.*


class SessionDetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_SESSION = "extra_session"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var session : SessionData? = intent?.getParcelableExtra(EXTRA_SESSION)

        if(session != null) {
            loadSession(session)
            loadSpeakerImages(session)
        }
    }

    private fun loadSession(session: SessionData) {
        session_title.text = session.title ?: "No title"
        session_abstract.text = session.abstract ?: "No abstract"
        session_location.text = session.rooms.joinToString(", ")

        speakers.text = session.speakers.joinToString(", ") {
            speaker  -> speaker.firstName + " " + speaker.lastName
        }

        if(session.sessionStartTime != null){
            val dateTimeString = session.sessionStartTime?.toShortDate(this) + " " + session.sessionStartTime?.toTime(this)
            session_time.text = dateTimeString
        }
    }

    private fun loadSpeakerImages(session: SessionData) {

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dividerItemDecoration = DividerItemDecoration(this,  layoutManager.orientation)

        rv_speaker_images.layoutManager = layoutManager
        rv_speaker_images.addItemDecoration(dividerItemDecoration)
        rv_speaker_images.adapter = SpeakerImageAdapter(session.speakers)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }
}
