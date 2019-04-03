package com.example.kointest.presentation.views.newnote

import android.util.Log
import com.example.kointest.domain.entity.Note
import com.example.kointest.infra.repository.NoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class AddNewNotePresenter(private val view: INewNoteContract.View) : INewNoteContract.Presenter, KoinComponent {


    private val mRepo: NoteRepository by inject()
    private val classNameLog = AddNewNotePresenter::class.java.simpleName

    override fun insert(note: Note) {
       mRepo.insert(note).subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({
               view.showAlert("Nota salva.")
           }, {
               view.showAlert("Erro ao incluir nota.")
               Log.e(classNameLog, it.message)
           }).dispose()
    }

    override fun updateNote(note: Note) {
        mRepo.insert(note).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showAlert("Nota atualizada.")
            }, {
                view.showAlert("Erro ao atualizar nota.")
                Log.e(classNameLog, it.message)
            }).dispose()

    }

    override fun deleteNote(note: Note) {
        mRepo.insert(note).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showAlert("nota excluída.")
            }, {
                view.showAlert("Erro ao excluir nota.")
                Log.e(classNameLog, it.message)
            }).dispose()

    }






}

