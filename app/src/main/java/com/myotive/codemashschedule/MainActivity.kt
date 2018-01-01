package com.myotive.codemashschedule

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.myotive.codemashschedule.api.entity.SessionData
import com.myotive.codemashschedule.api.entity.Speaker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

		btn_next_activity.setOnClickListener {
			val sessionData = getSession()
			val intent = Intent(this, SessionDetailActivity::class.java)
			intent.putExtra(SessionDetailActivity.EXTRA_SESSION, sessionData)
			startActivity(intent)
		}
    }

	private fun getSession(): SessionData {
		return SessionData(id = 7740,
			sessionStartTime = Date(),
			sessionEndTime = Date(),
			title = "Build a Natural Language Slack Bot for your Dev Team",
			abstract = "Many project teams use Slack as a means of communication with one another. Why not also use it to communicate with your infrastructure? Invite a helper into your conversation that can perform routine tasks tirelessly, conversationally, and interactively.\\r\\n\\r\\nIn this 4 hour workshop, you will build a Slack bot that understands natural language and integrates with your DevOps pipeline. You will use the Slack Events API to receive messages, and the Slack Web API to send them. You will use LUIS to interpret language and extract intent. You will execute actions against the Visual Studio Team Services Web API in response to user requests, and subscribe to Webhooks to notify your team of important events. In the end, you will have a new member of your team who can help you with your build and release pipeline.\\r\\n\\r\\nWorkshop outline:\\r\\n\\r\\nSlack API\\r\\n* \\tAuthorization - OAuth and API key verification\\r\\n* \\tEvents API - respond to posts\\r\\n* \\tWeb API - post messages\\r\\n* \\tIdentify users and channels\\r\\nLUIS\\r\\n* \\tSpecify intents and entities\\r\\n* \\tTrain your app\\r\\n* \\tEvaluate utterances\\r\\n* \\tCall LUIS from your Slack bot\\r\\nVSTS\\r\\n* \\tWeb API - interrogate source repository and build status\\r\\n* \\tWebhooks - respond to checkins and build completions\\r\\n* \\tCall services asynchronously to keep the conversation going\\r\\n* \\tRespond to the requester when events happen\\r\\n",
			rooms = listOf("Cypress"),
			speakers = listOf(Speaker(id = "1434dfae-bc8d-4238-b34c-2afb3edab4b3", firstName = "Michael", lastName = "Perry"))
		)
	}
}
