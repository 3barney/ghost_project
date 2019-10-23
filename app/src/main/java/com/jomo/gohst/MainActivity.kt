package com.jomo.gohst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jomo.gohst.data.model.Ghost
import com.jomo.gohst.ui.GhostViewModel
import com.jomo.gohst.ui.GhostViewModelFactory
import com.jomo.gohst.ui.adapter.DreamsAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var ghostViewModelFactory: GhostViewModelFactory
    lateinit var ghostViewModel: GhostViewModel
    var ghostItems: List<Ghost>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        ghostViewModel = ViewModelProviders.of(this, ghostViewModelFactory)
            .get(GhostViewModel::class.java)


        ghostViewModel.loadGhosts()
        ghostViewModel.ghostResult().observe(
            this,
            Observer<List<Ghost>> {
                ghostItems = it
                setUpView()
                Log.e("ARRRRRRR", "Hello ${it?.size}")
            })

        ghostViewModel.ghostError().observe(
            this,
            Observer<String> {
                Log.e("ERRRROR", "Hello error ${it}")
            }
        )


    }

    fun setUpView() {
        if (ghostItems != null) {
            dreams_recycler_view.apply {
                adapter = DreamsAdapter(
                    this@MainActivity,
                    ghostItems!!
                )
                adapter!!.notifyDataSetChanged()
            }
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
