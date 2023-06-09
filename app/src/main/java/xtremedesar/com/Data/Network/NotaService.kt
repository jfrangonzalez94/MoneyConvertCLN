package www.sanju.todonotes.Interface

import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.kotlin.deleteFromRealm
import xtremedesar.com.Data.Model.NotaModel
import java.util.UUID

interface NotaService {

    private fun getAllNotas(_Realm: Realm): MutableLiveData<List<NotaModel>> {
        val list = MutableLiveData<List<NotaModel>>()
        val notes = _Realm.where(NotaModel::class.java).findAll()
        list.value = notes?.subList(0, notes.size)
        return list
    }

    fun addNotas(_Realm: Realm, _Titulo: String, _Descripcion: String) {
        _Realm.executeTransaction { r: Realm ->
            val _Nota = r.createObject(NotaModel::class.java, UUID.randomUUID().toString())
            _Nota._Titulo = _Titulo
            _Nota._Descripcion = _Descripcion

            _Realm.insertOrUpdate(_Nota)
        }
    }

    fun updateNota(_Realm: Realm, _Id: String, _Titulo: String, _Descripcion: String) {
        val _Nota = _Realm.where(NotaModel::class.java)
            .equalTo("id", _Id)
            .findFirst()

        _Realm.executeTransaction {
            _Nota?._Titulo = _Titulo
            _Nota?._Descripcion = _Descripcion
            _Realm.insertOrUpdate(_Nota)
        }
    }

    fun deleteNota(_Realm: Realm, _Id: String) {
        val _Nota = _Realm.where(NotaModel::class.java)
            .equalTo("id", _Id)
            .findFirst()

        _Realm.executeTransaction {
            _Nota!!.deleteFromRealm()
        }
    }
}