package com.aravindh.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aravindh.composeplayground.ui.theme.ComposePlaygroundTheme

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Scaffold() { padding ->
        Column {
            SearchBar(Modifier.padding(8.dp))
            Content(modifier = Modifier.padding(padding))
        }
    }
}


@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var searchText by rememberSaveable { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        placeholder = {
            Text("Search")
        }
    )
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    HomeSection(
        title = R.string.align_your_body,
        modifier = modifier
    ) {
        AlignBodyRow(modifier)
    }

    HomeSection(
        title = R.string.align_your_body,
        modifier = modifier
    ) {
        FavoriteCollectionGird()
    }
}

@Composable
fun AlignBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignBodyElement(item.drawable, item.text)

        }
    }
}

@Composable
fun FavoriteCollectionGird(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        modifier = modifier.height(120.dp),
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(favoriteCollectionsData) {
            FavoriteCollectionCard(drawable = it.drawable, text = it.text)
        }
    }
}

@Composable
fun AlignBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(start = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.paddingFromBaseline(top = 8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painterResource(drawable),
                contentDescription = null,
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                stringResource(text),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}


@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            stringResource(id = title).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 20.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
@Preview(showBackground = true)
fun SearchViewPreview() {
    MyApp()
}