package com.example.kenaldy.mvp_aula.App.Data.Objects.Series

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class SerieDB: RealmObject(){
    @PrimaryKey
    var id: Int = 0

    @Required
    var title: String? = null

    @Required
    var overview: String? = null

    var poster_path: String? = null
}