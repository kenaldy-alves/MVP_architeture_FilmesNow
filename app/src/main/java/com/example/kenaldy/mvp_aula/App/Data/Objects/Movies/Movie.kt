package com.example.kenaldy.mvp_aula.App.Data.Objects.Movies

import java.io.Serializable

class Movie(
        val id: Int,
        val title: String,
        val overview: String,
        val poster_path : String
): Serializable
