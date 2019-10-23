package com.jomo.gohst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jomo.gohst.data.model.Ghost
import com.jomo.gohst.data.model.Tag
import com.jomo.gohst.ui.GhostViewModel
import com.jomo.gohst.ui.GhostViewModelFactory
import com.jomo.gohst.ui.adapter.DreamsAdapter
import com.jomo.gohst.ui.adapter.TagAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var ghostViewModelFactory: GhostViewModelFactory
    lateinit var ghostViewModel: GhostViewModel
    var ghostItems: List<Ghost>? = null

    private val tagLineItemList = listOf(
        Tag("All"),
        Tag("Flowers"),
        Tag("Flying"),
        Tag("Blood"),
        Tag("Food"),
        Tag("Walking")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        ghostViewModel = ViewModelProviders.of(this, ghostViewModelFactory)
            .get(GhostViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        ghostViewModel.loadGhosts()
        ghostViewModel.ghostResult().observe(
            this,
            Observer<List<Ghost>> {
                ghostItems = it
                setUpView()
                Log.e("SUCCESS", "${it?.size}")
            })

        ghostViewModel.ghostError().observe(
            this,
            Observer<String> {
                Log.e("ERRRROR", "error ${it}")
            }
        )

        setUpTagView()
    }

    private fun setUpView() {
        if (ghostItems != null && ghostItems!!.size > 0) {
            missing_dreams.visibility = View.GONE
            dreams_recycler_view.visibility = View.VISIBLE
            dreams_recycler_view.apply {
                adapter = DreamsAdapter(
                    this@MainActivity,
                    ghostItems!!
                )
                adapter!!.notifyDataSetChanged()
            }
        } else {
            missing_dreams.visibility = View.VISIBLE
            dreams_recycler_view.visibility = View.GONE
        }
    }

    private fun setUpTagView() {
        tag_line_items_recycler_view.apply {
            adapter = TagAdapter(this@MainActivity, tagLineItemList, ghostViewModel)
        }
    }

    override fun onDestroy() {
        ghostViewModel.disposeElements()
        super.onDestroy()
    }

    fun startAddDream(view: View) {
        val intent = Intent(this@MainActivity, AddDreamActivity::class.java)
        this.startActivity(intent)
    }

}
