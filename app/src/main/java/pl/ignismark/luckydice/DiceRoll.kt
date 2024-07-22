package pl.ignismark.luckydice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
            FloatingActionButton(
                onClick = { result = DiceRepository.diceSixSides.diceRoll() },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_roll),
                    contentDescription = null
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
                .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.small_padding))
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.small_component_size))

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_two),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = R.dimen.micro_padding))
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.small_component_size))

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_six),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = R.dimen.micro_padding))
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier.size(dimensionResource(id = R.dimen.small_component_size))

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_twenty),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = R.dimen.micro_padding))
                    )
                }
            }
            Image(
                painter = painterResource(id = result.graphic),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.centering_padding))
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceRollTopBar(
    navController: NavController
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(RollHistoryScreen)
            }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_menu),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.small_component_size))
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
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
