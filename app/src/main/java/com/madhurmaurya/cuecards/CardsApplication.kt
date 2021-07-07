package com.madhurmaurya.cuecards

import android.app.Application
import com.madhurmaurya.cuecards.data.AppDatabase

class CardsApplication: Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}