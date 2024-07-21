package pl.ignismark.luckydice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import pl.ignismark.luckydice.data.DiceRepository
import pl.ignismark.luckydice.data.Result
import pl.ignismark.luckydice.ui.theme.LuckyDiceTheme

class DiceRoll : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LuckyDiceTheme {

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = DiceRollScreen
                    ) {
                        composable<DiceRollScreen> {
                            LuckyDiceApp(navController = navController)
                        }
                        composable<RollHistoryScreen> {
                            RollHistoryApp(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LuckyDiceApp(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var result by remember { mutableStateOf(DiceRepository.diceSixSides.diceRoll()) }

    Scaffold(
        topBar = {
            DiceRollTopBar(
                navController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { result = DiceRepository.diceSixSides.diceRoll() }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_roll),
                    contentDescription = "twenty"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        modifier = modifier
    ) { innerPadding ->
        DiceRollScreen(innerPadding, result)
    }
}

@Composable
fun DiceRollScreen(
    paddingValues: PaddingValues,
    result: Result,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(paddingValues)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_two),
                        contentDescription = "two",
                        modifier = Modifier.size(48.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_six),
                        contentDescription = "six",
                        modifier = Modifier.size(48.dp)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_twenty),
                        contentDescription = "twenty",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            Image(
                painter = painterResource(id = result.graphic),
                contentDescription = "dice one of six"
            )
            Spacer(modifier = modifier.height(100.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceRollTopBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    Text(
                        text = "Lucky Dice",
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(RollHistoryScreen)
            }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_menu),
                    contentDescription = "menu"
                )
            }
        }
    )
}

@Serializable
object DiceRollScreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RollScreenPreview() {
    LuckyDiceTheme {
        LuckyDiceApp(navController = rememberNavController())
    }
}
