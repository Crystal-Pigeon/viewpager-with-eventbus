package com.example.viewpagerwitheventbus

/**
 * Class for Event Bus event to notify fragments inside viewpager about search request
 */
class SearchEvent(var query: String, var pageTitle: CharSequence?)
