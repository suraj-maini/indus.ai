package com.boilerplate.app.base

import androidx.lifecycle.ViewModel
import com.boilerplate.app.domain.utils.SingleLiveEvent

open class BaseViewModel : ViewModel() {
// Test change for git
  var dataLoadingEvent: SingleLiveEvent<Int> = SingleLiveEvent()
}