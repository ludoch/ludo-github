package com.google.ludo.appengine.jpademo.javasejpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ludo@google.com
 */
@Entity
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @NotNull
    private int id;
    @NotNull
    //@Size(max = 10)
    private String name;

    public Player() {
    }

    public Player(String name) {
        this.setName(name);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
    
    
    
        public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}