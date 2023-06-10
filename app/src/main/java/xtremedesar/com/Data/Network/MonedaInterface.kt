package www.sanju.todonotes.Interface

import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmResults
import xtremedesar.com.Data.Model.MonedaModel
import java.util.ArrayList

interface MonedaInterface {

    fun getAllMonedas(_Realm: Realm): List<MonedaModel>

    fun addMoneda(_Realm: Realm, _Nombre: String, _Pais: String, _Simbolo: String)

    fun updateMoneda(_Realm: Realm, _Id: String, _Nombre: String, _Pais: String, _Simbolo: String)

    fun deleteMoneda(_Realm: Realm, _Id: String)

    fun deleteAllMonedas(_Realm: Realm)
}