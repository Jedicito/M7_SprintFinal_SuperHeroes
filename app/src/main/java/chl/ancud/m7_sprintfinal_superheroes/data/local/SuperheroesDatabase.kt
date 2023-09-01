package chl.ancud.m7_sprintfinal_superheroes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [SuperheroesListadoEntity::class], version = 1)
abstract class SuperheroesDatabase: RoomDatabase() {

    abstract fun getISuperheroesDao(): SuperheroesDao

    companion object {
        @Volatile
        private var INSTANCIA: SuperheroesDatabase? = null

        fun getDatabase(context: Context): SuperheroesDatabase {
            val tempInstancia = INSTANCIA
            if (tempInstancia!=null) {
                return tempInstancia
            }
            synchronized(this) {
                val instancia = databaseBuilder(
                    context.applicationContext,
                    SuperheroesDatabase::class.java,
                    "superheroes_database"
                ).build()

                INSTANCIA = instancia
                return instancia
            }
        }
    }

}