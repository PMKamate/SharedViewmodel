package com.practicaltest.sharedviewmodel

class Movie(var name: String, var rating: Double) {

    override fun toString(): String {
        return "Movie{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}'
    }
}