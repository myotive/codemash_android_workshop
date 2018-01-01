package com.myotive.codemashschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.myotive.codemashschedule.api.entity.SessionData
import com.myotive.codemashschedule.utilities.toShortDate
import com.myotive.codemashschedule.utilities.toTime
import kotlinx.android.synthetic.main.activity_speaker_detail.*


class SessionDetailActivity : AppCompatActivity() {

	companion object {
		const val EXTRA_SESSION = "extra_session"
	}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

		val session : SessionData? = intent?.getParcelableExtra(EXTRA_SESSION)

		if(session != null) {
			loadSession(session)
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


	override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }
}
