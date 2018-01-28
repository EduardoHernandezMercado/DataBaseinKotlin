package com.ingemtryx.eduardohm.mydatabase.DataBase;

import android.provider.BaseColumns

object FeedReaderContract {

    /* Inner class that defines the table contents */
    class FeedEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "entry"
            val COLUMN_NAME_TITLE = "title"
            val COLUMN_NAME_SUBTITLE = "subtitle"
            var _ID = "1"
        }
    }
}// To prevent someone from accidentally instantiating the contract class,
// make the constructor private.