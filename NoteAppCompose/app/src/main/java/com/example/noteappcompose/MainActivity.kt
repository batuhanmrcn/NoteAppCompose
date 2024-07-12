package com.example.noteappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.noteappcompose.data.NotesDatabase
import com.example.noteappcompose.login.LoginScreen
import com.example.noteappcompose.presentation.AddNoteScreen
import com.example.noteappcompose.presentation.NotesScreen
import com.example.noteappcompose.presentation.NotesViewModel
import com.example.noteappcompose.ui.theme.NoteAppComposeTheme

class MainActivity : ComponentActivity() {


    //veri tabanını aktarma ,bağlama ve isim vs oluşturma işlemi
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java,
            "notes.db"
        ).build()
    }
    //viewmodel bağlama işlemi
    private val viewModel by viewModels<NotesViewModel> (
        factoryProducer = {        //viewmodel oluşturmak için ve nasıl oluşturulacağını söylemek için
            object : ViewModelProvider.Factory {
                override fun<T: ViewModel> create(modelClass: Class<T>): T {
                    return NotesViewModel(database.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val state by viewModel.state.collectAsState()    //state'i UI'ye bağlamak için
                    val navController = rememberNavController()
                    // Ekranlar arası geçiş için navigation
                    NavHost(navController= navController, startDestination = "LoginScreen") {
                       composable("LoginScreen"){
                           LoginScreen(onLoginClick = {
                               navController.navigate("NotesScreen")
                           }) {

                           }
                       }

                        composable("NotesScreen") {
                            NotesScreen(
                                state = state,
                                navController = navController,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable("AddNoteScreen") {
                            AddNoteScreen(
                                state = state,
                                navController = navController,
                                onEvent = viewModel::onEvent
                            )
                        }
                    }

                }

            }
        }
    }
}
