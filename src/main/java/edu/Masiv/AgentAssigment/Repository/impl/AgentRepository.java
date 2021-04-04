package edu.Masiv.AgentAssigment.Repository.impl;

import edu.Masiv.AgentAssigment.Model.Agent;
import edu.Masiv.AgentAssigment.Repository.IAgentRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class AgentRepository implements IAgentRepository {
    public static final String AGENT_KEY = "AGENT";
    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public AgentRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    @Override
    public void NewAgent(Agent agent){
        int id = hashOperations.values(AGENT_KEY).size();
        agent.setId(id);
        hashOperations.put(AGENT_KEY,agent.getId(),agent);
    }


    @Override
    public List<Agent> getAgents() {
        return hashOperations.values(AGENT_KEY);
    }

    public void orderList(List<Agent> agents){
        Collections.sort(agents, new Comparator<Agent>() {
            @Override
            public int compare(Agent p1, Agent p2) {
                return new Integer(p1.getPriorityRank()).compareTo(new Integer(p2.getPriorityRank()));
            }
        });
    }

    @Override
    public int AssignAgents(int PriorityRank) {
        List<Agent> agents = hashOperations.values(AGENT_KEY);
        orderList(agents);
        Agent result = null;
        for(Agent x: agents){
            if(x.getPriorityRank()>= PriorityRank && x.isAvailability()){
                x.setAvailability(false);
                result = x;
                break;
            }
            else{
                return -1;
            }
        }
        hashOperations.put(AGENT_KEY,result.getId(),result);
        return result.getId();
    }

    @Override
    public void ReleaseAgents(int id){
        Agent result = null;
        List<Agent> agents = hashOperations.values(AGENT_KEY);
        for(Agent x: agents){
            if(x.getId()==id){
                x.setAvailability(true);
                result = x;
                break;
            }
        }
        hashOperations.put(AGENT_KEY,result.getId(),result);
    }
}
