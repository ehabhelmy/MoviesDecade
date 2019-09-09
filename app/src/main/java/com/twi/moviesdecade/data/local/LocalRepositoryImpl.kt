package com.twi.moviesdecade.data.local


import javax.inject.Inject

class LocalRepositoryImpl @Inject
constructor(private var preferencesManager: PreferencesManager, internal var databaseManager: DatabaseManager) :
    LocalRepository
