package edu.Masiv.AgentAssigment.Repository;

import edu.Masiv.AgentAssigment.Model.Agent;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgentRepository {
    public static final String AGENT_KEY = "AGENT";
    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public AgentRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void NewAgent(Agent agent){
        int id = hashOperations.values(AGENT_KEY).size();
        agent.setId(id);
        hashOperations.put(AGENT_KEY,agent.getId(),agent);
    }

    public List<Agent> findAll(){
        return hashOperations.values(AGENT_KEY);
    }

    public Agent findById(int id) {
        return (Agent) hashOperations.get(AGENT_KEY, id);
    }

    public void ReleaseAgent(Agent agent){
        int id = agent.getId();
        delete(id);
        hashOperations.put(AGENT_KEY,id,agent);
    }

    public void delete(int id){
        hashOperations.delete(AGENT_KEY, id);
    }
}
