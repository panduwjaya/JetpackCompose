package com.example.submisiondicoding.di

import com.example.submisiondicoding.data.MortyRepository


object Injection {
    fun provideRepository(): MortyRepository {
        return MortyRepository.getInstance()
    }
}