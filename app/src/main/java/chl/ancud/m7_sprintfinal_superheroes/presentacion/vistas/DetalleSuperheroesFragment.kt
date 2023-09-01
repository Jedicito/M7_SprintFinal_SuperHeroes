package chl.ancud.m7_sprintfinal_superheroes.presentacion.vistas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import chl.ancud.m7_sprintfinal_superheroes.R
import chl.ancud.m7_sprintfinal_superheroes.databinding.FragmentDetalleSuperheroesBinding
import chl.ancud.m7_sprintfinal_superheroes.presentacion.SuperheroesViewModel
import coil.load

class DetalleSuperheroesFragment : Fragment() {
    private var paramId: Int? = null
    lateinit var binding: FragmentDetalleSuperheroesBinding
    private val superheroesViewModel: SuperheroesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramId = it.getInt("id")
            //Log.d("Detalle OnCreate", paramId.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleSuperheroesBinding.inflate(layoutInflater)
        setComponentes(paramId!!)
        return binding.root
    }

    private fun setComponentes(paramId: Int) {

        //var traducido: String

        superheroesViewModel.getSuperheroesDetalle(paramId)
        superheroesViewModel.superheroesDetalleLiveData(paramId).observe(viewLifecycleOwner){

            if (it != null) {
                val nombre = it.nombre
                binding.txvDetalleNombre.text = nombre
                binding.imgDetalleFoto.load(it.imagenLink)
                binding.txvDetalleOrigen.text = it.origen
                binding.txvDetalleCreacion.text = it.creacion.toString()
                binding.txvDetallePoderes.text = it.poder
                binding.txvDetalleColor.text = it.color

                binding.txvDetalleTraduccion.text =
                    if(it.traduccion) getString(R.string.txv_Traducido)
                    else getString(R.string.txv_NoTraducido)


                binding.flbDetalleCorreo.setOnClickListener {
                    val intentCorreo = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("Comicconchile@hotmail.com"))
                        putExtra(Intent.EXTRA_SUBJECT, getString(R.string.correoAsunto, nombre))
                        putExtra(Intent.EXTRA_TEXT, getString(R.string.correoMensaje, nombre))
                    }
                    startActivity(intentCorreo)
                }

            }

        }

        binding.flbDetalleVolver.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_detalleSuperheroesFragment_to_listadoSuperheroesFragment)
        }

    }


}