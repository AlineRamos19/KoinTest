package com.example.kointest.presentation.views.newnote

import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kointest.R
import com.example.kointest.domain.entity.Note
import com.example.kointest.domain.utils.Enums
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_new_note.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class AddNewNoteActivity : AppCompatActivity(), INewNoteContract.View {


    private val presenter by inject<INewNoteContract.Presenter> { parametersOf(this)}
    private var priority : Int = 0
    lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_note)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true )

        intent?.extras?.let {
                note = intent!!.extras!!.getSerializable("ID_NOTE") as Note
            inputValuesIntent(note)

        }

        btn_confirm_note.setOnClickListener {
            getValuesAndValidate()
        }
    }

    override fun inputValuesIntent(note: Note) {
        note.let {
            input_title.setText(it.titleNote)
            input_body.setText(it.contentNote)
            last_update.visibility = View.VISIBLE
            last_update.append(" " + it.dateNote)
            when(it.priorityNote){
                Enums.Companion.Priority.LOW.getPriority() -> radio_group_priority.check(prio_1.id)
                Enums.Companion.Priority.MEDIUM.getPriority()  -> radio_group_priority.check(prio_2.id)
                Enums.Companion.Priority.HIGH.getPriority() -> radio_group_priority.check(prio_3.id)
            }
        }

    }


    override fun getValuesAndValidate() {

        val title  = Editable.Factory.getInstance().newEditable(input_title.text).toString()
        val body = Editable.Factory.getInstance().newEditable(input_body.text).toString()
        val sdf = SimpleDateFormat("dd/MM hh:mm", Locale.getDefault())
        val dateFormat = sdf.format(Date())

        val id = radio_group_priority.checkedRadioButtonId
        when(id){
            prio_1.id -> {
                priority = Enums.Companion.Priority.LOW.getPriority()

            }
            prio_2.id -> {
                priority = Enums.Companion.Priority.MEDIUM.getPriority()
            }
            prio_3.id -> {
                priority = Enums.Companion.Priority.HIGH.getPriority()
            }
        }

        if(title.isEmpty() || body.isEmpty() || id == -1){
            showAlertEmptyInput()
        } else {
            if(::note.isInitialized){
                    note = Note(
                        note.id,
                        titleNote = title,
                        contentNote = body,
                        dateNote = dateFormat,
                        priorityNote = priority
                    )
                    presenter.updateNote(note)
                    finish()
            }else{
                note = Note(
                    titleNote = title,
                    contentNote = body,
                    dateNote = dateFormat,
                    priorityNote = priority
                )
                presenter.insert(note)

            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> confirmExit()
            R.id.action_delete -> {
               confirmDeleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun confirmDeleteNote() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.label_dialog_title))
            .setMessage("Tem certeza que deseja excluir a nota ${note.titleNote} ?")
            .setPositiveButton(getString(R.string.label_delete_note)){ _, _ ->
                presenter.deleteNote(note)
                finish()
            }.setNegativeButton(getString(R.string.btn_dialog_cancel)){ dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false).create().show()

}

    override fun confirmExit() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.label_dialog_title))
            .setMessage("Deseja realmente sair?\nCaso haja alterações, não serão salvas.")
            .setPositiveButton(getString(R.string.btn_exit_dialog)){ _, _ ->
                finish()
            }.setNegativeButton(getString(R.string.btn_dialog_cancel)){ dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false).create().show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun showAlert(msg: String) {
       Snackbar.make(constraint_new_note, msg, Snackbar.LENGTH_LONG).show()
    }

    override fun showAlertEmptyInput() {
        showAlert(getString(R.string.error_input_empty))
    }


}
