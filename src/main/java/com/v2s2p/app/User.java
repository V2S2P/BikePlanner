package com.v2s2p.app;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class User {
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    private String name;
    private Integer level;
    private Integer preferredRouteLength;

    public User(String name, Integer level, Integer preferredRouteLength) {
        this.name = name;
        this.level = level;
        this.preferredRouteLength = preferredRouteLength;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getPreferredRouteLength() {
        return preferredRouteLength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setPreferredRouteLength(Integer preferredRouteLength) {
        this.preferredRouteLength = preferredRouteLength;
    }

    public void printUser() {
        logger.info("User: " + name + " Level: " + level + " Preferred Route Length: " + preferredRouteLength);
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', level=" + level + ", preferredRouteLength=" + preferredRouteLength + '}';
    }

}