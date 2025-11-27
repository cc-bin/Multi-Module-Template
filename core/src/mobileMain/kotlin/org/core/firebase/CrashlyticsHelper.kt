package org.core.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

class CrashlyticsHelper {
    fun init(){
        Firebase.initialize()
    }
}