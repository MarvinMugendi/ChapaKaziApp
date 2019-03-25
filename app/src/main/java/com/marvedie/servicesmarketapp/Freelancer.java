package com.marvedie.servicesmarketapp;

public class Freelancer {

    String freelancerid;
    String freelancerName;
    String freelancerGenre;
    String freelancerService;
    String freelancerChat;

    //define constructor that will be used while Reading the Values

    public Freelancer(){


    }

    //New Constructor that will initialise our Strings


    public Freelancer(String freelancerid, String freelancerName, String freelancerGenre, String freelancerService, String freelancerChat) {
        this.freelancerid = freelancerid;
        this.freelancerName = freelancerName;
        this.freelancerGenre = freelancerGenre;
        this.freelancerService = freelancerService;
        this.freelancerChat = freelancerChat;
    }

    public String getFreelancerid() {
        return freelancerid;
    }

    public String getFreelancerChat() {
        return freelancerChat;
    }

    public String getFreelancerName() {
        return freelancerName;
    }

    public String getFreelancerGenre() {
        return freelancerGenre;
    }

    public String getFreelancerService() {
        return freelancerService;
    }
}
