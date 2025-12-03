package com.example.cleanarchitecture_jetpackcompose_mvi.data.di

import com.example.cleanarchitecture_jetpackcompose_mvi.data.gateway.RemoteGateway
import com.example.cleanarchitecture_jetpackcompose_mvi.data.gateway.RemoteGatewayImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GatewayModule {
    @Binds
    abstract fun bindsRemoteGateway(remoteGatewayImpl: RemoteGatewayImpl): RemoteGateway
}