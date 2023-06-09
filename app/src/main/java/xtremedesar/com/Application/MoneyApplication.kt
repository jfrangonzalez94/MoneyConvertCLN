package xtremedesar.com.Application

import android.app.Application
import android.content.res.Resources
import desarrollonica.com.ni.controlptypanama.Helper.FunsHelper

class MoneyApplication : Application() {

    companion object {
        lateinit var _FunsHelper: FunsHelper
        lateinit var _RecursosAPP: Resources
    }

    override fun onCreate() {
        super.onCreate()
        _FunsHelper = FunsHelper()
        _RecursosAPP = getResources()
    }
}