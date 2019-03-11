package com.codecool.web.service;

import com.codecool.web.model.Greeting;

import java.util.ArrayList;
import java.util.List;

public final class GreetingService {

    public List<Greeting> getGreetings() {
        List<Greeting> greetings = new ArrayList<>();
        greetings.add(new Greeting("en", "Hi!"));
        greetings.add(new Greeting("hu", "Szia!"));
        greetings.add(new Greeting("fr", "Bonjour!"));
        greetings.add(new Greeting("pl", "Cześć!"));
        return greetings;
    }
}
