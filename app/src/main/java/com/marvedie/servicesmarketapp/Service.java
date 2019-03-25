package com.marvedie.servicesmarketapp;

public class Service  {

    private String serviceId;
    private String serviceName;
    private String freelancerCategory;


    public Service() {


    }

    public Service(String serviceId, String serviceName, String freelancerCategory) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.freelancerCategory = freelancerCategory;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getFreelancerCategory() {
        return freelancerCategory;
    }
}

