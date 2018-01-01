package com.myotive.codemashschedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.myotive.codemashschedule.api.CodeMashAPI
import com.myotive.codemashschedule.api.CodeMashAPIBuilder
import com.myotive.codemashschedule.api.entity.SessionData
import com.myotive.codemashschedule.recyclerview.SessionAdapter
import com.myotive.codemashschedule.utilities.enqueue
import com.myotive.codemashschedule.utilities.loadCodeMashResponseFromAssets
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

	private lateinit var codeMashAPI: CodeMashAPI

	private lateinit var sessionAdapter: SessionAdapter

	private var onClickListener: (SessionData) -> Unit = { sessionData ->

		val intent = Intent(this, SessionDetailActivity::class.java)
		intent.putExtra(SessionDetailActivity.EXTRA_SESSION, sessionData)

		startActivity(intent)
	}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

		codeMashAPI = CodeMashAPIBuilder.build()

		setupUI()

		// On initial load, start refreshing the swipe container and fetch the codemash api data
		srl_container.isRefreshing = true
		getCodeMashData()
    }

	private fun setupUI() {

		srl_container.setOnRefreshListener { getCodeMashData() }

		rv_codemash_sessions.layoutManager = LinearLayoutManager(this)

		sessionAdapter = SessionAdapter(listener = onClickListener)
		rv_codemash_sessions.adapter = sessionAdapter
	}

	private fun getCodeMashData() {
		codeMashAPI.SessionData().enqueue(
			success = {
				// "it" == implicit object for success call.
				// "it" is Response<List<SessionData>>
				if (it.isSuccessful && it.body() != null) {
					sessionAdapter.swapData(it.body().orEmpty())
				}

				srl_container.isRefreshing = false
			},
			failure = {

				// "it" in this case is a throwable
				Log.e("MainActivity", "Error calling codemash api", it)

				Toast.makeText(applicationContext,
					"Oops... Something went wrong. Loading codemash data from resources...",
					Toast.LENGTH_LONG)
					.show()

				val sessionDataFromCache = loadCodeMashResponseFromAssets(this@MainActivity,
					CodeMashAPIBuilder.getGSON())
				sessionAdapter.swapData(sessionDataFromCache.orEmpty())

				srl_container.isRefreshing = false
			}
		)
	}
}
