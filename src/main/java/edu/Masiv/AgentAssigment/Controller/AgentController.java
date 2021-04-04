package edu.Masiv.AgentAssigment.Controller;

import edu.Masiv.AgentAssigment.Model.Agent;
import edu.Masiv.AgentAssigment.Repository.IAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private IAgentRepository ar;

    @PostMapping("/newAgent")
    public ResponseEntity<?> NewAgent(@RequestBody Agent agent){
        try{
            ar.NewAgent(agent);
            return new ResponseEntity<>(agent,HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAgents(){
        try{
            return new ResponseEntity<>(ar.getAgents(),HttpStatus.ACCEPTED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/assign/{PriorityRank}")
    public ResponseEntity<?> AssignAgent(@PathVariable int PriorityRank){
        try{
            int Agent = ar.AssignAgents(PriorityRank);
            if(Agent==-1){
                return new ResponseEntity<>("No agents available",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(Agent,HttpStatus.ACCEPTED);
            }
        }
        catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/release/{id}")
    public ResponseEntity<?> ReleaseAgent(@PathVariable int id){
        try{
            ar.ReleaseAgents(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
