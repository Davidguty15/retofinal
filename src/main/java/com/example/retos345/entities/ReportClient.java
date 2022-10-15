package com.example.retos345.entities;

public class ReportClient{
    private int total;
    private Client client;

    public ReportClient(Client client){
        this.client = client;
        this.total = client.getReservations().size();
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}
