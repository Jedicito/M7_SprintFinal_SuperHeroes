package chl.ancud.m7_sprintfinal_superheroes.presentacion.adaptadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import chl.ancud.m7_sprintfinal_superheroes.R
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesListadoEntity
import chl.ancud.m7_sprintfinal_superheroes.databinding.ItemListadoSuperheroesBinding
import coil.load
import kotlinx.coroutines.supervisorScope

class AdapterListado: RecyclerView.Adapter<AdapterListado.ItemListadoViewHolder>() {

    lateinit var binding: ItemListadoSuperheroesBinding
    private val listaSuperheroes = mutableListOf<SuperheroesListadoEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterListado.ItemListadoViewHolder {
        binding = ItemListadoSuperheroesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemListadoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListadoViewHolder, position: Int) {
        val superHeroe = listaSuperheroes[position]
        holder.bind(superHeroe)
    }

    override fun getItemCount(): Int {
        return listaSuperheroes.size
    }

    fun setData(superHeroes: List<SuperheroesListadoEntity>) {
        this.listaSuperheroes.clear()
        this.listaSuperheroes.addAll(superHeroes)
        notifyDataSetChanged()
    }

    class ItemListadoViewHolder(val binding: ItemListadoSuperheroesBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(superHeroes: SuperheroesListadoEntity) {
            binding.txvNombre.text = superHeroes.nombre
            binding.txvOrigen.text = superHeroes.origen
            binding.txvCreacion.text = superHeroes.creacion.toString()
            binding.imgFotoSuperheroe.load(superHeroes.imagenLink)

            binding.cardViewItemListado.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", superHeroes.id)
                Navigation.findNavController(binding.root).navigate(R.id.action_listadoSuperheroesFragment_to_detalleSuperheroesFragment, bundle)
            }
        }
    }


}