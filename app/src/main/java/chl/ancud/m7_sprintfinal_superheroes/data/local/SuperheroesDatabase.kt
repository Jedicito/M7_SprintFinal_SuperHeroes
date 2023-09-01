package chl.ancud.m7_sprintfinal_superheroes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SuperheroesListadoEntity::class], version = 1)
abstract class SuperheroesDatabase: RoomDatabase() {


}