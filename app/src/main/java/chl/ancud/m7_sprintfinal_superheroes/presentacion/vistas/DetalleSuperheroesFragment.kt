package chl.ancud.m7_sprintfinal_superheroes.presentacion.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import chl.ancud.m7_sprintfinal_superheroes.R
import chl.ancud.m7_sprintfinal_superheroes.databinding.FragmentDetalleSuperheroesBinding
import chl.ancud.m7_sprintfinal_superheroes.presentacion.SuperheroesViewModel

class DetalleSuperheroesFragment : Fragment() {
    private var paramId: Int? = null
    lateinit var binding: FragmentDetalleSuperheroesBinding
    private val superheroesViewModel: SuperheroesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramId = it.getInt("id")
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

        superheroesViewModel.getSuperheroesDetalle(paramId)
        superheroesViewModel.superheroesDetalleLiveData(id).observe(viewLifecycleOwner){

        }

    }


}