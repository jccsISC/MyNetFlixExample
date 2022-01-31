package com.jccsisc.appnetflixmodule.utils

object MLambdasObject {
    var changeTitle: ((title: String) -> Unit)? = null
    var isItemSelected: ((isItemMenuClicked: Boolean) -> Unit)? = null
}