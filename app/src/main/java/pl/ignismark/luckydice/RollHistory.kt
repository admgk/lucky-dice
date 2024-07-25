package pl.ignismark.luckydice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import pl.ignismark.luckydice.data.Result
import pl.ignismark.luckydice.data.ResultRepository
import pl.ignismark.luckydice.ui.theme.LuckyDiceTheme
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun RollHistoryApp(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            RollHistoryTopBar(navController = navController)
        },
        modifier = modifier
    ) { innerPadding ->
        RollHistoryScreen(paddingValues = innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RollHistoryTopBar(
    navController: NavController
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.title_activity_roll_history),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(DiceRollScreen)
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

@Composable
fun RollHistoryScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(ResultRepository.results) { result ->
                RollResultItem(
                    result = result,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.micro_padding))
                )
            }
        }
    }
}

@Composable
fun RollResultItem(
    result: Result,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.micro_padding)
        ),
        border = BorderStroke(
            dimensionResource(id = R.dimen.border_width),
            MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = R.dimen.medium_padding),
                        top = dimensionResource(id = R.dimen.medium_padding),
                        end = dimensionResource(id = R.dimen.medium_padding),
                        bottom = if (expanded)
                            dimensionResource(id = R.dimen.zero_padding)
                            else dimensionResource(id = R.dimen.medium_padding)
                    )
            ) {
                Text(
                    text = "${stringResource(id = R.string.dice_label)} ${result.diceName}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${stringResource(id = R.string.value_label)} ${result.value}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.medium_padding))
                )
                Image(
                    painter = painterResource(id = result.graphic),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.small_component_size))
                )
            }
            if (expanded) {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = dimensionResource(id = R.dimen.medium_padding)
                        )
                ) {
                    Text(
                        text = result.time.format(
                            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT,FormatStyle.MEDIUM)
                        ),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Serializable
object RollHistoryScreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RollHistoryPreview() {
    LuckyDiceTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            RollHistoryApp(navController = rememberNavController())
        }
    }
}
