package xtremedesar.com.UI.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.R
import xtremedesar.com.databinding.ActivityCardRecyclerviewItemBinding

class AdapterMoneda(
    private val _Context: Context,
    private var ListaMoneda: RealmResults<MonedaModel>,
    private val _Listener: MonedaAdapterListener,
) : RecyclerView.Adapter<AdapterMoneda.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val _Binding = ActivityCardRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(_Binding)
    }

    class ViewHolder(val _Binding: ActivityCardRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(_Binding.root) {
    }

    override fun onBindViewHolder(_Holder: ViewHolder, Posicion: Int) {
        with(_Holder){
            with(ListaMoneda[Posicion]){
                _Binding.tvRegistroNombre.setText(this?.Nombre)
                _Binding.tvRegistroPais.setText(this?.Pais)
                _Binding.tvRegistroSimbolo.setText(this?.Simbolo)
                _Binding.lyMenu.setOnClickListener {
                    val popupMenus = PopupMenu(_Context, it)
                    popupMenus.inflate(R.menu.show_menu)
                    popupMenus.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.detail_registro -> {
                                _Listener.onMonedaDetalle(Posicion, this)
                                true
                            }
                            R.id.edit_registro -> {
                                _Listener.onMonedaEditar(Posicion, this)
                                true
                            }
                            R.id.elim_registro -> {
                                _Listener.onMonedaEliminar(Posicion, this)
                                true
                            }
                            else -> true
                        }
                    }

                    val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                    popup.isAccessible = true
                    val menu = popup.get(popupMenus)
                    menu.javaClass
                        .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                        .invoke(menu, true)
                    popupMenus.show()
                }
            }
        }
    }

    interface MonedaAdapterListener {
        fun onMonedaDetalle(Posicion: Int, Moneda: MonedaModel?)
        fun onMonedaEditar(Posicion: Int, Moneda: MonedaModel?)
        fun onMonedaEliminar(Posicion: Int, Moneda: MonedaModel?)
    }

    override fun getItemCount() = ListaMoneda.size

}