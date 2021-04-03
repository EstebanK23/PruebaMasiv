package edu.Masiv.AgentAssigment.Controller;

import edu.Masiv.AgentAssigment.Model.Agent;
import edu.Masiv.AgentAssigment.Repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentRepository ar;

    @PostMapping
    public Agent NewAgent(@RequestBody Agent agent){
        ar.NewAgent(agent);
        return agent;
    }
    @GetMapping
    public List<Agent> list(){
        return ar.findAll();
    }

    @GetMapping("/{id}")
    public Agent getAgent(@PathVariable int id){
        return ar.findById(id);
    }

    @PutMapping("/{id}")
    public Agent ReleaseAgent(@RequestBody Agent agent,@PathVariable int id){
        ar.delete(id);
        if (!agent.isAvailability())
        agent.setAvailability(true);
        ar.ReleaseAgent(agent);
        return agent;
    }

    @DeleteMapping("/{id}")
    public int deleteAgent(@PathVariable int id){
        ar.delete(id);
        return id;
    }
}
