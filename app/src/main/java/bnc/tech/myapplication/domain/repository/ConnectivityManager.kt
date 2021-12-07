package bnc.tech.myapplication.domain.repository

interface ConnectivityManager {
    fun hasNetwork(): Boolean?
}