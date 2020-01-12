package businesslogic.client.domain;

import businesslogic.client.domain.unit.Monster;
import comlayer.server.*;
public class User {
    private Monster monster;
    public int id;
    private ConnectionToClient connectionToClient;
    public String name;
    public String password;

    public User(Monster monster, int id, ConnectionToClient connectionToClient, String name, String password) {
        this.monster = monster;
        this.id = id;
        this.connectionToClient = connectionToClient;
        this.name = name;
        this.password = password;
    }
    public User(int id, String name, String password) {
    	this.id = id;
    	this.name = name;
    	this.password = password;
    }

    public ConnectionToClient getConnectionToClient() {
        return connectionToClient;
    }

    public void setConnectionToClient(ConnectionToClient connectionToClient) {
        this.connectionToClient = connectionToClient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
