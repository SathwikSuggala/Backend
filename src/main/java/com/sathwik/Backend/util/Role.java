package com.sathwik.Backend.util;


public enum Role {
    CUSTOMER;

    @Override
    public String toString() {
        return "ROLE_" + name();
    }
}
