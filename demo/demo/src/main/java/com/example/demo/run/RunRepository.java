package com.example.demo.run;

import  org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

public interface RunRepository extends ListCrudRepository<Run, Integer> { //reminder to go to the source of ListCrudRepository

    //@Query("SELECT * FROM run WHERE location = :location ") you can write your own queries like that too
    public List<Run> findAllByLocation(String location);
}

// ------------------------------------- JDBC Client ----------------------------------------------------------
//@Repository
//public class RunRepository {

//    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
//    private final JdbcClient jdbcClient; //needed to connect to the database (h2)
//
//    public RunRepository(JdbcClient jdbcClient) {
//        this.jdbcClient = jdbcClient;
//    }
//
//    List<Run> findAll(){
//       return jdbcClient.sql("SELECT * FROM Run").query(Run.class).list();
//    }
//
//    Optional<Run> findById(Integer id){
//        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id = :id")
//                .param("id",id)
//                .query(Run.class).
//                optional();
//    }
//
//    public void create(Run run){
//        var updated = jdbcClient.sql("INSERT INTO Run (id,title,started_on,completed_on,miles,location) VALUES (?,?,?,?,?,?) ")
//                .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString()))
//                .update();
//        Assert.state(updated == 1, "Failed to create run: " + run.title()); //we assert that updated is 1, if not we return a message
//    }
//
//    public void update(Run run, Integer id){
//        var updated = jdbcClient.sql("UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
//                .params(List.of(run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString(),id))
//                .update();
//        Assert.state(updated == 1, "Failed to update run: "+ run.title());
//    }
//
//    public void delete(Integer id){
//        var updated = jdbcClient.sql("DELETE FROM run WHERE id = :id").param("id",id).update();
//        Assert.state(updated == 1, "Failed to delete run with id: " + id);
//    }
//
//    public int count(){
//        return jdbcClient.sql("SELECT * FROM run").query().listOfRows().size();
//    }
//
//    public void saveAll(List<Run> runs){
//        runs.stream().forEach(this::create); //execute create for every each of the List provided
//    }
//
//    public List<Run> findByLocation(String location){
//        return jdbcClient.sql("SELECT * FROM run WHERE location = :location").param("location",location)
//                .query(Run.class)
//                .list();
//    }

// ----------------------------------------------- Built-in Local Database ---------------------------------------------

    //   private List<Run> runs = new ArrayList<>();

//    List<Run> findAll(){
//        return runs;
//    }
//
//    void create(Run run){
//        runs.add(run);
//    }
//
//    void update(Run run, Integer  id){
//        Optional<Run> existingRun = findById(id);
//        if(existingRun.isPresent()){
//            runs.set(runs.indexOf(existingRun.get()), run);
//        }
//    }
//
//    void delete(Integer id){
//        runs.removeIf(run -> run.id().equals(id));
//    }
//
//    //daha database'e geçmediğin için dümenden init
//    @PostConstruct //used in the initialization of the class
//    private void init(){
//        runs.add(new Run(1, "Morning Sunday Run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
//                3,Location.INDOOR));
//        runs.add(new Run(2,"Wednesday Evening Run",LocalDateTime.now(),LocalDateTime.now().plus(60,ChronoUnit.MINUTES),
//                6,Location.INDOOR));
//    }
//
//    Optional<Run> findById(Integer id){ //optional is a container object which may or may not contain a null value
//       return runs.stream().filter(run -> run.id() == id).findFirst();
//    }


//}
