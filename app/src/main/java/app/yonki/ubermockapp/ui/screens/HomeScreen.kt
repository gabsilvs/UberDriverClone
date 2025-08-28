package app.yonki.ubermockapp.ui.screens

import NewRideModal
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import app.yonki.ubermockapp.ui.components.UberBottomBar
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.asImageBitmap
import java.io.InputStream
import android.graphics.BitmapFactory
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var isOnline by remember { mutableStateOf(false) }
    var showNewRideDialog by remember { mutableStateOf(false) }
    var rideRequestId by remember { mutableStateOf(0) }
    var rideTimer by remember { mutableStateOf(8) }

    // Simulated ride data
    val rideData = remember(rideRequestId) {
        listOf(
            Triple(
                "João Silva", "Av. Paulista, 1000", "Av. Faria Lima, 500"
            ) to ("R$ 18,90" to 4.89),
            Triple(
                "Maria Souza", "Rua das Flores, 200", "Shopping Center, 300"
            ) to ("R$ 12,50" to 4.95),
            Triple("Carlos Lima", "Praça Central, 50", "Rodoviária, 1200") to ("R$ 22,30" to 4.80)
        ).random()
    }
    val (passenger, origin, destination) = rideData.first
    val (price, rating) = rideData.second

    // Show new ride every 10s while online
    LaunchedEffect(isOnline) {
        while (isOnline) {
            delay(10000)
            if (isOnline) {
                showNewRideDialog = true
                rideRequestId++
                rideTimer = 8
            }
        }
    }

    // Ride modal countdown
    LaunchedEffect(showNewRideDialog, rideRequestId) {
        if (showNewRideDialog) {
            rideTimer = 8
            while (rideTimer > 0 && showNewRideDialog) {
                delay(1000)
                rideTimer--
            }
            if (showNewRideDialog) {
                showNewRideDialog = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Gray),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                "Olá, Motorista!",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Text(
                                if (isOnline) "Você está online" else "Você está offline",
                                color = if (isOnline) Color.Green else Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = { UberBottomBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Background de imagem do mapa
            val context = LocalContext.current
            val assetManager = context.assets
            val bitmap = remember {
                val inputStream: InputStream = assetManager.open("leafletimage.png")
                BitmapFactory.decodeStream(inputStream)
            }
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Mapa Estático",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(0f)
            )


            // Status Card when offline
            if (!isOnline) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "Você está offline",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Toque no botão abaixo para começar a receber corridas",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = {
                                    isOnline = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Black
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    "Ficar Online",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            } else {
                // Online state - show earnings and status
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        "Ganhos de hoje",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        "R$ 127,50",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        "Corridas",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        "8",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    isOnline = false
                                    showNewRideDialog = false
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Ficar Offline", color = Color.White)
                            }
                        }
                    }
                }
            }

            // New Ride modal usando seu componente existente
            if (showNewRideDialog && isOnline) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .zIndex(2f),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    NewRideModal(
                        preco = price,
                        timer = rideTimer,
                        avaliacao = rating,
                        passageiro = passenger,
                        origem = origin,
                        destino = destination,
                        onAccept = {
                            showNewRideDialog = false
                            navController.navigate("corrida")
                        },
                        onReject = { showNewRideDialog = false }
                    )
                }
            }
        }
    }
}