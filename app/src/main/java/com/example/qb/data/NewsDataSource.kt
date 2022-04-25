package com.example.qb.data

import com.prof.rssparser.Channel
import com.prof.rssparser.Parser
import java.nio.charset.Charset

class NewsDataSource {

    suspend fun getNewsFeed(url: String): Channel {
        val parser = Parser.Builder().charset(Charset.forName("UTF-8")).build()

        return try {
            parser.getChannel(url)
        } catch (e: Exception) {
            e.printStackTrace()
            Channel(null, null, null, null, null, null, mutableListOf(), null)
        }
    }
}