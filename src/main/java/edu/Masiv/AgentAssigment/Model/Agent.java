package edu.Masiv.AgentAssigment.Model;

import java.io.Serializable;

public class Agent implements Serializable {
    private int Id;
    private String AgentName;
    private String AgentCC;
    private int PriorityRank;
    private boolean Availability = true;

    public Agent(String AgentName,String AgentCC,int PriorityRank){
        this.AgentName = AgentName;
        this.AgentCC = AgentCC;
        this.PriorityRank = PriorityRank;
    }

    public int getId() { return Id; }

    public void setId(int id) { Id = id; }

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String AgentName) {
        this.AgentName = AgentName;
    }

    public String getAgentCC() {
        return AgentCC;
    }

    public void setAgentCC(String AgentCC) {
        this.AgentCC = AgentCC;
    }

    public int getPriorityRank() {
        return PriorityRank;
    }

    public void setPriorityRank(int PriorityRank) {
        this.PriorityRank = PriorityRank;
    }

    public boolean isAvailability() {
        return Availability;
    }

    public void setAvailability(boolean Availability) {
        this.Availability = Availability;
    }
}
