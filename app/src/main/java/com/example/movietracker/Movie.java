package com.example.movietracker;

import java.util.List;

public class Movie {
    String name;

    public Movie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLess(Movie movie) {
        if(name.charAt(0) > movie.name.charAt(0)){
            return true;
        }
        else if (name.charAt(0) == movie.name.charAt(0)){
            if (name.charAt(1) > movie.name.charAt(1)){
                return true;
            }
            else {
                return false;
            }
        }
        else
            return false;
    }
}