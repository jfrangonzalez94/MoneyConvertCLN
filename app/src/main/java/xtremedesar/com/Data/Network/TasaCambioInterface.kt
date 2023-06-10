package www.sanju.todonotes.Interface

import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmResults
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.Data.Model.TasaCambioModel
import java.util.ArrayList

interface TasaCambioInterface {

    fun getAllTasaCambios(_Realm: Realm, _IdMoneda: String): List<TasaCambioModel>

    fun addTasaCambio(_Realm: Realm, _TasaCambio: String, _Pais: String, _IdMoneda: String)

    fun updateTasaCambio(
        _Realm: Realm,
        _Id: String,
        _TasaCambio: String,
        _Pais: String,
        _IdMoneda: String
    )

    fun deleteTasaCambio(_Realm: Realm, _Id: String)

    fun deleteAllTasaCambios(_Realm: Realm)
}