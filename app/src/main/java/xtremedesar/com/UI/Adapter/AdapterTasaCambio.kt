package xtremedesar.com.UI.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import xtremedesar.com.Data.Model.TasaCambioModel
import xtremedesar.com.R
import xtremedesar.com.databinding.ActivityCardRecyclerviewItem2Binding

class AdapterTasaCambio(
    private val _Context: Context,
    private var ListaTasaCambio: List<TasaCambioModel>,
    private val _Listener: TasaCambioAdapterListener,
) : RecyclerView.Adapter<AdapterTasaCambio.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val _Binding = ActivityCardRecyclerviewItem2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(_Binding)
    }

    class ViewHolder(val _Binding: ActivityCardRecyclerviewItem2Binding) :
        RecyclerView.ViewHolder(_Binding.root) {
    }

    override fun onBindViewHolder(_Holder: ViewHolder, Posicion: Int) {
        with(_Holder){
            with(ListaTasaCambio[Posicion]){
                _Binding.tvRegistroNombre.setText(this?.TasaCambio)
                _Binding.tvRegistroPais.setText(this?.Pais)
                _Binding.lyMenu.setOnClickListener {
                    val popupMenus = PopupMenu(_Context, it)
                    popupMenus.inflate(R.menu.show_menu_2)
                    popupMenus.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.elim_registro -> {
                                _Listener.onTasaCambioEliminar(Posicion, this)
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

    interface TasaCambioAdapterListener {
        fun onTasaCambioEliminar(Posicion: Int, TasaCambio: TasaCambioModel?)
    }

    override fun getItemCount() = ListaTasaCambio.size

}