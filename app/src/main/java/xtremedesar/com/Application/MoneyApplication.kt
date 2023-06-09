package xtremedesar.com.Application

import android.app.Application
import android.content.res.Resources
import desarrollonica.com.ni.controlptypanama.Helper.FunsHelper
import io.realm.Realm
import io.realm.RealmConfiguration

class MoneyApplication : Application() {

    companion object {
        lateinit var _FunsHelper: FunsHelper
        lateinit var _RecursosAPP: Resources
    }

    override fun onCreate() {
        super.onCreate()

        _FunsHelper = FunsHelper()
        _RecursosAPP = getResources()

        // INICIAR REALMDB
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .name("MoneyConvert.db")
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(0)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(configuration)
    }
}