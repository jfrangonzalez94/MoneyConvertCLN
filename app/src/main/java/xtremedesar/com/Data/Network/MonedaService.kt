package www.sanju.todonotes.Interface

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.deleteFromRealm
import xtremedesar.com.Data.Model.MonedaModel
import java.util.ArrayList
import java.util.UUID

class MonedaService : MonedaInterface {

    override fun getAllMonedas(_Realm: Realm): RealmResults<MonedaModel> {
        val _ListModeda = _Realm.where(MonedaModel::class.java).findAll()
        return _ListModeda
    }

    override fun addMoneda(_Realm: Realm, _Nombre: String, _Pais: String, _Simbolo: String) {
        _Realm.executeTransaction { r: Realm ->
            val _Modeda = r.createObject(MonedaModel::class.java, UUID.randomUUID().toString())
            _Modeda.Nombre = _Nombre
            _Modeda.Pais = _Pais
            _Modeda.Simbolo = _Simbolo

            _Realm.insertOrUpdate(_Modeda)
        }
    }

    override fun updateMoneda(
        _Realm: Realm,
        _Id: String,
        _Nombre: String,
        _Pais: String,
        _Simbolo: String
    ) {
        val _Modeda = _Realm.where(MonedaModel::class.java)
            .equalTo("ID", _Id)
            .findFirst()

        _Realm.executeTransaction {
            _Modeda?.Nombre = _Nombre
            _Modeda?.Pais = _Pais
            _Modeda?.Simbolo = _Simbolo
            _Realm.insertOrUpdate(_Modeda)
        }
    }

    override fun deleteMoneda(_Realm: Realm, _Id: String) {
        val _Nota = _Realm.where(MonedaModel::class.java)
            .equalTo("ID", _Id)
            .findFirst()

        _Realm.executeTransaction {
            _Nota!!.deleteFromRealm()
        }
    }

    override fun deleteAllMonedas(_Realm: Realm) {
        _Realm.executeTransaction { r: Realm ->
            r.delete(MonedaModel::class.java)
        }
    }
}