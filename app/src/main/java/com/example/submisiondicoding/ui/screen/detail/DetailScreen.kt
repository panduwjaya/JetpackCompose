package com.example.submisiondicoding.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.jetreward.ui.theme.JetRewardTheme
import com.example.submisiondicoding.di.Injection
import com.example.submisiondicoding.ui.ViewModelFactory
import com.example.submisiondicoding.ui.common.UiState

@Composable
fun DetailScreen(
    rewardId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getRewardById(rewardId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.character.name,
                    data.character.species,
                    data.character.photoUrl,
                    data.character.desc,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    species: String,
    photoUrl: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(24.dp)
        ) {
            // revisi
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    AsyncImage(
                        model = photoUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = species
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Deskripsi Character :")
            Text(
                text = description
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    JetRewardTheme {
        DetailContent(
            photoUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            name = "Mock Charcoal",
            species = "Human",
            description = "Karakter Rick Sanchez pada rick and morty adalah spesies manusia berjenis kelamin pria"
        )
    }
}