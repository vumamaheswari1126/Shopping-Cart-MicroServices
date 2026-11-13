package com.example.apigateway.dto;


public class AuthenticatedUserResponse {
	private int customerId;
    private String role;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
