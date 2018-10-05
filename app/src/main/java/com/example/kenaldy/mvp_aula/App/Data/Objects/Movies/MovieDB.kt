package com.example.kenaldy.mvp_aula.App.Data.Objects.Movies

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class MovieDB: RealmObject(){
    @PrimaryKey
    var id: Int = 0

    @Required
    var title: String? = " "

    @Required
    var overview: String? = " "

    var poster_path: String? = " "
}