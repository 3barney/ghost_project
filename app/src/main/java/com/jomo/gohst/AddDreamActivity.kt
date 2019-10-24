package com.jomo.gohst

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import android.widget.DatePicker
import android.text.InputType
import android.widget.TextView
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.text.SimpleDateFormat


class AddDreamActivity : AppCompatActivity() {

    @Inject
    lateinit var ghostViewModelFactory: GhostViewModelFactory
    lateinit var ghostViewModel: GhostViewModel

    lateinit var tagLine: TextInputLayout
    lateinit var description: TextInputLayout
    lateinit var datePicker: EditText
    lateinit var picker: DatePickerDialog

    lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_dream)
        AndroidInjection.inject(this)

        ghostViewModel = ViewModelProviders.of(this, ghostViewModelFactory)
            .get(GhostViewModel::class.java)

        tagLine = findViewById<TextInputLayout>(R.id.tagLine)
        description = findViewById<TextInputLayout>(R.id.description)
        datePicker = findViewById<EditText>(R.id.dateItem)

        datePicker.setOnClickListener {
            val calendar: Calendar= Calendar.getInstance()
            var day: Int = calendar.get(Calendar.DAY_OF_MONTH)
            var month: Int = calendar.get(Calendar.MONTH)
            var year: Int = calendar.get(Calendar.YEAR)

            picker = DatePickerDialog(
                this@AddDreamActivity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    datePicker.setText(dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                }, year, month, day)
            picker.show()
        }
    }

    fun saveDream(view: View) {
        var tagValue = tagLine.editText!!.text.toString()
        var descriptionValue = description.editText!!.text.toString()
        var dateValue = datePicker!!.text.toString()

        if (tagValue.isEmpty()) {
            tagLine.error = "Tag is required"
            return
        }

        if (descriptionValue.isEmpty()) {
            description.error = "Description is required"
            return
        }

        if (dateValue.isEmpty() || dateValue.length < 2) {
            datePicker.error = "Date is required"
            return
        }

        val ghost: Ghost = Ghost(tag = tagValue, description = descriptionValue, Night = SimpleDateFormat("dd/MM/yyyy").parse(dateValue))
        ghostViewModel.addGhost(ghost)

        toast = Toast.makeText(this@AddDreamActivity, "Dream Added", Toast.LENGTH_SHORT)
        toast.show()

        this.finish()
    }
}
