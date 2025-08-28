import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.zIndex
import androidx.compose.runtime.Composable
import androidx.compose.material3.*

@Composable
fun NewRideModal(
    preco: String,
    timer: Int,
    avaliacao: Double,
    passageiro: String,
    origem: String,
    destino: String,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp)
            .padding(bottom = 40.dp) // move modal up from the bottom bar
            .zIndex(2f)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Timer as a charge bar (progress bar)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
            ) {
                val progress = timer / 8f // 8 seconds is the full bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(10.dp)
                        .background(Color(0xFF1A73E8), RoundedCornerShape(8.dp))
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            // Preço
            Text(
                text = preco,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
            // Avaliação
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("⭐", fontSize = 18.sp, color = Color(0xFFFFA500))
                Text(
                    text = String.format("%.2f", avaliacao),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // Passageiro
            Text(
                text = passageiro,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Rota
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = "Origem:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = origem,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Destino:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = destino,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Botões
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = onReject,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Recusar")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = onAccept,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
                ) {
                    Text("Aceitar", color = Color.White)
                }
            }
        }
    }
}
