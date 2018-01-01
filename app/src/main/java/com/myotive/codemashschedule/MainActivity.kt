package com.myotive.codemashschedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.myotive.codemashschedule.api.CodeMashAPIBuilder
import com.myotive.codemashschedule.api.entity.SessionData
import com.myotive.codemashschedule.recyclerview.SessionAdapter
import com.myotive.codemashschedule.utilities.loadCodeMashResponseFromAssets
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

	private lateinit var sessionAdapter: SessionAdapter

	private var onClickListener: (SessionData) -> Unit = { sessionData ->

		val intent = Intent(this, SessionDetailActivity::class.java)
		intent.putExtra(SessionDetailActivity.EXTRA_SESSION, sessionData)

		startActivity(intent)
	}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

		setupUI()
    }

	private fun setupUI() {
		val sessionDataFromCache = loadCodeMashResponseFromAssets(this@MainActivity,
			CodeMashAPIBuilder.getGSON())

		sessionAdapter = SessionAdapter(sessionDataFromCache.orEmpty(), listener = onClickListener)

		rv_codemash_sessions.layoutManager = LinearLayoutManager(this)
		rv_codemash_sessions.adapter = sessionAdapter

	}
}
