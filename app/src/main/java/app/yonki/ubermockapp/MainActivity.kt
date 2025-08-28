package app.yonki.ubermockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.yonki.ubermockapp.ui.theme.UberMockAppTheme
import app.yonki.ubermockapp.ui.screens.UberDriverApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UberMockAppTheme {
                UberDriverApp()
            }
        }
    }
}
