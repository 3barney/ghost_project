package com.jomo.gohst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jomo.gohst.data.model.Ghost
import com.jomo.gohst.ui.GhostViewModel
import com.jomo.gohst.ui.GhostViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_add_dream.*
import java.util.*
import javax.inject.Inject

class AddDreamActivity : AppCompatActivity() {

    @Inject
    lateinit var ghostViewModelFactory: GhostViewModelFactory
    lateinit var ghostViewModel: GhostViewModel

    lateinit var tagLine: TextInputLayout
    lateinit var description: TextInputLayout

    lateinit var t: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dream)
        AndroidInjection.inject(this)

        ghostViewModel = ViewModelProviders.of(this, ghostViewModelFactory)
            .get(GhostViewModel::class.java)

        tagLine = findViewById<TextInputLayout>(R.id.tagLine)
        description = findViewById<TextInputLayout>(R.id.description)

    }

    fun saveDream(view: View) {
        var tagValue = tagLine.editText!!.text.toString()
        var descriptionValue = description.editText!!.text.toString()

        if (tagValue != null && descriptionValue != null) {
            val ghost: Ghost = Ghost(tag = tagValue, description = descriptionValue, Night = Date())
            ghostViewModel.addGhost(ghost)

            t = Toast.makeText(this@AddDreamActivity, "Dream Added", Toast.LENGTH_SHORT)
            t. show()
        }
    }

}
