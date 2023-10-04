package com.example.submisiondicoding.data

import com.example.submisiondicoding.model.MortyDataSource
import com.example.submisiondicoding.model.OrderReward
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MortyRepository {

    private val orderRewards = mutableListOf<OrderReward>()

    init {
        if (orderRewards.isEmpty()) {
            MortyDataSource.charData.forEach { it ->
                orderRewards.add(OrderReward(it))
            }
        }
    }

    fun getAllRewards(): Flow<List<OrderReward>> {
        return flowOf(orderRewards)
    }

    fun getOrderRewardById(rewardId: Long): OrderReward {
        return orderRewards.first {
            it.character.id == rewardId
        }
    }


    companion object {
        @Volatile
        private var instance: MortyRepository? = null

        fun getInstance(): MortyRepository =
            instance ?: synchronized(this) {
                MortyRepository().apply {
                    instance = this
                }
            }
    }
}