package com.dametweet.dametweet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RelativeLayout
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.TweetUtils
import com.twitter.sdk.android.tweetui.TweetView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Twitter.initialize(TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig("consumerKey", "consumerSecret"))
                .debug(true)
                .build())
        setContentView(R.layout.activity_main)

        val myLayout = findViewById(R.id.dame_tweet) as RelativeLayout

        val tweetId = 510908133917487104L
        TweetUtils.loadTweet(tweetId, object : Callback<Tweet>() {
            override fun success(result: Result<Tweet>) {
                myLayout.addView(TweetView(this@MainActivity, result.data))
            }

            override fun failure(exception: TwitterException) {
                // Toast.makeText(...).show();
            }
        })
    }
}
