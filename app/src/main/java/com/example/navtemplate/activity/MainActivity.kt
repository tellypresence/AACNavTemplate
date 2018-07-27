package com.example.navtemplate.activity

import android.net.Uri
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.example.navtemplate.R
import com.example.navtemplate.fragment.dummy.DummyItemDetailFrag
import com.example.navtemplate.fragment.dummy.DummyListFrag
import com.example.navtemplate.fragment.dummy.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(),
        DummyListFrag.OnDummyListFragInteractionListener,
        DummyItemDetailFrag.OnDummyDetailFragInteractionListener {

    private val TAG = MainActivity::class.java.simpleName

    lateinit var navController: NavController
    val navigationView by lazy { nav_view }

    override fun onDummyListFragInteraction(item: DummyContent.DummyItem?) {
        Log.d(TAG, "onDummyListFragInteraction(): $item")
    }

    override fun onDummyDetailFragInteraction(uri: Uri) {
        Log.d(TAG, "onDummyDetailFragInteraction(): $uri")
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d(TAG, "onSupportNavigateUp()")
        // Ref: https://developer.android.com/reference/androidx/navigation/ui/NavigationUI
        // This _should_ be correct for case w/nav drawer
        return NavigationUI.navigateUp(drawer_layout, navController)
    }

    /**
     * Set up nav drawer menu items to use AAC "Navigation" component
     *
     * NOTE: based on T 'n E guesswork, docs woefully inadequate
     *
     * @precondition MUST CALL AFTER ToolBar (nee action/app bar) setup
     *
     * Refs:
     *      Ref: https://developer.android.com/reference/androidx/navigation/ui/NavigationUI
     *
     * Steve Madsen, 9 Jun 2018
     */
    private fun setupNavGraph() {
        navController = findNavController(R.id.frag_my_nav_graph)
        // Calls onNavDestinationSelected(MenuItem, NavController) when menu item selected
        NavigationUI.setupWithNavController(navigationView, navController)
        // Changes title, animates hamburger to back/up icon
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        setupNavGraph()
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
            // Update for AAC/Jetpack "Navigation" component
            //      Ref: https://issuetracker.google.com/issues/110507712
            else -> return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }
}
