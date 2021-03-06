package com.cyder.android.syncpod.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.cyder.android.syncpod.model.Chat
import com.cyder.android.syncpod.repository.ChatRepository
import com.cyder.android.syncpod.view.helper.Navigator
import com.cyder.android.syncpod.viewmodel.base.FragmentViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/**
 * Created by chigichan24 on 2018/04/17.
 */

class ChatFragmentViewModel @Inject constructor(
        private val navigator: Navigator,
        private val repository: ChatRepository
) : FragmentViewModel() {
    var chatViewModels: ObservableList<ChatViewModel> = ObservableArrayList()
    private val onPauseSubject = PublishSubject.create<Unit>()

    override fun onStart() {
    }

    override fun onResume() {
        repository.observePastChat
                .observeOn(AndroidSchedulers.mainThread())
                .takeUntil(onPauseSubject.toFlowable(BackpressureStrategy.LATEST))
                .map { convertToViewModel(it) }
                .subscribe({
                    chatViewModels.clear()
                    chatViewModels.addAll(it)
                }, {

                })
        repository.observeChat
                .observeOn(AndroidSchedulers.mainThread())
                .takeUntil(onPauseSubject.toFlowable(BackpressureStrategy.LATEST))
                .map {
                    ChatViewModel(ObservableField(it))
                }
                .subscribe {
                    chatViewModels.add(it)
                }
    }

    override fun onPause() {
        onPauseSubject.onNext(INVOCATION)
    }

    override fun onStop() {
    }

    private fun convertToViewModel(chats: List<Chat>): List<ChatViewModel> {
        return chats.map { ChatViewModel(ObservableField(it)) }
    }

    companion object {
        val INVOCATION = Unit
    }

}