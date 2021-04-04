package edu.Masiv.AgentAssigment.Repository;

import edu.Masiv.AgentAssigment.Model.Agent;

import java.util.List;

public interface IAgentRepository {
    void NewAgent(Agent agent);
    List<Agent> getAgents();
    int AssignAgents(int PriorityRank);
    void ReleaseAgents(int id);
}
