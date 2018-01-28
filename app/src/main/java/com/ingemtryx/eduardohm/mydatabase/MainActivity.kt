package com.ingemtryx.eduardohm.mydatabase

import android.content.ContentValues
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.ingemtryx.eduardohm.mydatabase.DataBase.FeedReaderContract.FeedEntry
import com.ingemtryx.eduardohm.mydatabase.DataBase.FeedReaderDbHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    ///h

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        val mDbHelper = FeedReaderDbHelper(this)

        buttonCT.setOnClickListener {
            CreateTable(mDbHelper)
        }
        buttonSD.setOnClickListener {
            textViewSD.setText(ShowData(mDbHelper))
        }

    }

    fun CreateTable(mDbHelper: FeedReaderDbHelper) {

        val db = mDbHelper.writableDatabase
        val values = ContentValues()
        values.put(FeedEntry.COLUMN_NAME_TITLE, "My")
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE, "Sub")
        values.put(FeedEntry._ID, "2")

        val newRowId = db.insert(FeedEntry.TABLE_NAME, null, values)

    }

    fun ShowData(mDbHelper: FeedReaderDbHelper): String {
        val db = mDbHelper.getReadableDatabase()

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        val projection = arrayOf(FeedEntry._ID, FeedEntry.COLUMN_NAME_TITLE, FeedEntry.COLUMN_NAME_SUBTITLE)

// Filter results WHERE "title" = 'My Title'
        val selection = FeedEntry.COLUMN_NAME_TITLE + " = ?"
        val selectionArgs = arrayOf("My")

// How you want the results sorted in the resulting Cursor
        val sortOrder = FeedEntry.COLUMN_NAME_SUBTITLE + " DESC"

        val c = db.query(
                FeedEntry.TABLE_NAME, // The table to query
                projection, // The columns to return
                selection, // The columns for the WHERE clause
                selectionArgs, // don't group the rows
                null, null, // don't filter by row groups
                sortOrder                                 // The sort order
        )// The values for the WHERE clause

        val Todo = "Null";
        while (c.moveToNext()) {
            val itemId = c.getLong(c.getColumnIndexOrThrow(FeedEntry._ID))
            val name = c.getString(c.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE))
            val title = c.getString(c.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE))
            Todo.contains("Data :" + itemId.toString() + " , " + name + title)
            Todo.contains(",")
        }

        return Todo


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
