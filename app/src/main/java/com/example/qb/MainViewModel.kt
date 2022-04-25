package com.example.qb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qb.data.NewsDataSource
import com.prof.rssparser.Article
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles


    fun fetchFeed() {
        viewModelScope.launch {
            try {
                val articles: MutableList<Article> = mutableListOf()
                articles.addAll(0,NewsDataSource().getNewsFeed("https://www.newsbtc.com/feed/").articles)
                articles.addAll(articles.size, NewsDataSource().getNewsFeed("https://minergate.com/blog/feed/").articles)
                articles.addAll(articles.size, NewsDataSource().getNewsFeed("https://news.bitcoin.com/feed/").articles)
                articles.addAll(articles.size, NewsDataSource().getNewsFeed("https://www.coindesk.com/arc/outboundfeeds/rss/?outputType=xml").articles)
                articles.addAll(articles.size, NewsDataSource().getNewsFeed("https://cointelegraph.com/rss").articles)
                val articles1 = articles.distinctBy { it.link }.toMutableList()
                val format: DateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
                val sortedArticles = articles1.sortedByDescending { format.parse(it.pubDate?: ZERO_DATE) }
                _articles.postValue(sortedArticles.subList(0,9))
            } catch (e: Exception) {
                e.printStackTrace()
                _articles.postValue(listOf())
            }
        }
    }

    companion object {
        private const val ZERO_DATE = "Thu, 01 Jan 1970 00:00:00 +0000"
    }
}