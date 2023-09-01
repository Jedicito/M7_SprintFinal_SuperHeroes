package chl.ancud.m7_sprintfinal_superheroes

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesDao
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesDatabase
import chl.ancud.m7_sprintfinal_superheroes.data.local.SuperheroesListadoEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class DatabaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var superheroesDao: SuperheroesDao
    private lateinit var db: SuperheroesDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SuperheroesDatabase::class.java).build()
        superheroesDao = db.getISuperheroesDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertSuperheroes_empty() = runBlocking {
        // Given
        val superheroesLista = listOf<SuperheroesListadoEntity>()

        // When
        superheroesDao.insertSuperheroes(superheroesLista)

        // Then
        superheroesDao.selectSuperheroes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertSuperheroe_1() = runBlocking {
        // Given
        val superheroesLista = listOf(SuperheroesListadoEntity(1,"Nombre1", "Origen1","Link1","Poder1",1111))

        // When
        superheroesDao.insertSuperheroes(superheroesLista)

        // Then
        superheroesDao.selectSuperheroes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertSuperheroe_3() = runBlocking {
        // Given
        val superheroeLista = listOf(
            SuperheroesListadoEntity(1,"Nombre1", "Origen1","Link1","Poder1",1111),
            SuperheroesListadoEntity(2,"Nombre2", "Origen2","Link2","Poder2",2222),
            SuperheroesListadoEntity(3,"Nombre3", "Origen3","Link3","Poder3",3333)
        )

        // When
        superheroesDao.insertSuperheroes(superheroeLista)

        // Then
        superheroesDao.selectSuperheroes().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}