package com.example.demo.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //it states that this class is a rest controller, so the response body is going to be in some form by default (json)
@RequestMapping("/api/runs") //all the requests in this class will have that base
public class RunController {

   /* @GetMapping("/hello") // /hello'da endpoint bu
    String home(){
        return "Hello runners!";
    } */

    private final RunRepository runRepository;

    RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }

    @GetMapping()
    List<Run> findAll(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}") //you already know that you can use {}
    Run findById(@PathVariable Integer id){ //it helps us to extract id from the annotation above
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty())
            throw new RunNotFoundException();
        return run.get();
    }

    // post -> means creating
    @ResponseStatus(HttpStatus.CREATED) //if successful, return a http status: created
    @PostMapping("") //empty because it'll get posted in api/runs
    void create(@Valid /*activate the validations*/ @RequestBody Run run){ //run will be taken from the request body, which is in json format
        // old -> runRepository.create(run);
        runRepository.save(run);
    }

    //put -> means updating
    @ResponseStatus(HttpStatus.NO_CONTENT) //this is done, no content to send back to you
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id ){
        // old -> runRepository.update(run,id);
        runRepository.save(run);
    }

    //delete -> basically deleting :D
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
       // old-> runRepository.delete(id);
        runRepository.delete(runRepository.findById(id).get()); //delete wants the entity now
    }

    @GetMapping("/location/{location}")
    @ResponseStatus(HttpStatus.FOUND)
    List<Run> findAllByLocation(@PathVariable String location){
        return runRepository.findAllByLocation(location);
    }

}
