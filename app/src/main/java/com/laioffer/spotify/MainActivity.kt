package com.laioffer.spotify
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import coil.compose.AsyncImage
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.laioffer.spotify.ui.theme.SpotifyTheme

// customized extend AppCompatActivity
class MainActivity : AppCompatActivity() {
    private val TAG = "lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        val navHostFragment =supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_graph)

        NavigationUI.setupWithNavController(navView, navController)

        // https://stackoverflow.com/questions/70703505/navigationui-not-working-correctly-with-bottom-navigation-view-implementation
//        navView.setOnItemSelectedListener{
//            NavigationUI.onNavDestinationSelected(it, navController)
//            navController.popBackStack(it.itemId, inclusive = false)
//            true
//        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "We are at onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "We are at onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "We are at onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "We are at onStop()")
    }

    override fun onDestroy() {
        Log.d(TAG, "We are at onDestroy()")
        super.onDestroy()
    }
}

@Composable
private fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp)
            .background(Color.Blue)
    ) {
        Text(text = "Hello,")
        Text(text = name)
    }
}


@Composable
fun ArtistCardBox() {
    Box {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

//@Composable
//fun ArtistCardRow() {
//    Column {
//        Text("Alfred Sisley")
//        Text("3 minutes ago")
//    }
//}

//@Preview
//@Composable
//fun PreviewArtistCardBox() {
//    SpotifyTheme {
//        Surface {
//            ArtistCardRow()
//        }
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SpotifyTheme {
//        Surface {
//            Greeting("Laioffer")
//        }
//    }
//}

@Composable
fun HelloContent() {

    // state
    var name by remember { mutableStateOf("") }

    // (state, setState) = ""

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.body2
        )
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text("Name") }
        )
    }
}

@Composable
fun StatelessHelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.body2
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}

@Preview
@Composable
fun PreviewHelloContent() {
    SpotifyTheme {
        Surface {
            HelloContent()
        }
    }
}


@Composable
fun AlbumCover() {
    Column{
        Box(modifier = Modifier.size(160.dp)) {
            // downloads in the background (async)
            AsyncImage(
                model = "https://upload.wikimedia.org/wikipedia/en/d/d1/Stillfantasy.jpg",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Still Fantasy",
                color = Color.White,
                modifier = Modifier.padding(8.dp).align(Alignment.BottomStart)
            )
        }

        Text(
            text = "Jay Chou",
            color = Color.White,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp, start = 8.dp)
        )

    }
}


@Preview
@Composable
fun PreviewHelloContent2() {
    SpotifyTheme {
        Surface {
            AlbumCover()
        }
    }
}

