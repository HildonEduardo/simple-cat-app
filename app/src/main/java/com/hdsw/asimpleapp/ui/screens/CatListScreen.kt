package com.hdsw.asimpleapp.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hdsw.asimpleapp.R
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.ui.components.CatImage
import com.hdsw.asimpleapp.ui.utils.DateUtils
import com.hdsw.asimpleapp.ui.viewmodels.CatListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    onCatClicked: (String) -> Unit,
    orientation: Int? = LocalConfiguration.current.orientation,
    viewModel: CatListViewModel = hiltViewModel()
) {
    val cats by viewModel.cats.collectAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "List of Cats") }
            )
        }
    ) { innerPadding ->
        if (orientation == ORIENTATION_PORTRAIT) {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .testTag("cats_list"),

                ) {
                items(cats) { cat ->
                    CatListItem(orientation, onCatClicked, cat = cat)
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 300.dp),
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .testTag("cats_grid")
            ) {
                items(cats) { cat ->
                    CatListItem(orientation, onCatClicked, cat = cat)
                }
            }
        }

    }
}

@Composable
fun CatListItem(orientation: Int?, onCatClicked: (String) -> Unit, cat: Cat) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        if (orientation == ORIENTATION_PORTRAIT) {
            Row(
                modifier = Modifier
                    .clickable { onCatClicked(cat.id) }
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                CatImage(cat)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    CatTextInfo(cat)
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .clickable { onCatClicked(cat.id) }
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    CatImage(cat)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        CatTextInfo(cat)
                    }
                }
            }
        }
    }
}

@Composable
fun CatTextInfo(cat: Cat) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val owner = if ("null" == cat.owner || cat.owner.isEmpty()) {
            stringResource(id = R.string.owner_unknown)
        } else cat.owner

        Text(
            text = stringResource(R.string.owner_label, owner),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = stringResource(R.string.tags, cat.tags.joinToString()),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = stringResource(R.string.created_at, DateUtils.formatDate(cat.createdAt)),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = stringResource(R.string.updated_at, DateUtils.formatDate(cat.updatedAt)),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp),
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

