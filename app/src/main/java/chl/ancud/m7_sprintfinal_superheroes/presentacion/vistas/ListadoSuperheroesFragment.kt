package chl.ancud.m7_sprintfinal_superheroes.presentacion.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import chl.ancud.m7_sprintfinal_superheroes.R
import chl.ancud.m7_sprintfinal_superheroes.databinding.FragmentListadoSuperheroesBinding
import chl.ancud.m7_sprintfinal_superheroes.presentacion.SuperheroesViewModel
import chl.ancud.m7_sprintfinal_superheroes.presentacion.adaptadores.AdapterListado


class ListadoSuperheroesFragment : Fragment() {
    lateinit var binding: FragmentListadoSuperheroesBinding
    private val superheroesViewModel: SuperheroesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListadoSuperheroesBinding.inflate(layoutInflater)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterListado()
        superheroesViewModel.getAllSuperheroes()
        binding.rvListadoSuperheroes.adapter = adapter
        superheroesViewModel.superheroesLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

}