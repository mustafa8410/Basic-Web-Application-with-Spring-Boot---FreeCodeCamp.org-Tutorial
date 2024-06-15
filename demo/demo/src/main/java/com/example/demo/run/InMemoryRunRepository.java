package com.example.demo.run;

import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRunRepository {

       private List<Run> runs = new ArrayList<>();

    List<Run> findAll(){
        return runs;
    }

    void create(Run run){
        runs.add(run);
    }

    void update(Run run, Integer  id){
        Optional<Run> existingRun = findById(id);
        if(existingRun.isPresent()){
            runs.set(runs.indexOf(existingRun.get()), run);
        }
    }

    void delete(Integer id){
        runs.removeIf(run -> run.id().equals(id));
    }

    //daha database'e geçmediğin için dümenden init
    @PostConstruct //used in the initialization of the class
    private void init(){
        runs.add(new Run(1, "Morning Sunday Run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,Location.INDOOR,0));
        runs.add(new Run(2,"Wednesday Evening Run",LocalDateTime.now(),LocalDateTime.now().plus(60,ChronoUnit.MINUTES),
                6,Location.INDOOR,0));
    }

    public Optional<Run> findById(Integer id) {
        return Optional.ofNullable(runs.stream()
                .filter(run -> run.id() == id)
                .findFirst()
                .orElseThrow(RunNotFoundException::new));
    }
}
