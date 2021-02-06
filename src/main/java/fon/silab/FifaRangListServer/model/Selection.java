/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
/**
 *
 * @author Veljko
 */
@Entity
public class Selection implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne()
    @NotNull()
    @JoinColumn()
    private Confederation confederation;
    
    @Size(min = 3)
    @Column(unique = true)
    @NotEmpty()
    private String name;
    
    @PositiveOrZero
    private int rang;
    
    @PositiveOrZero
    private int points;
    
    private boolean active;
    
    @ManyToOne()
    @JoinColumn(name="USER_ID")
    @NotNull()
    User user;
    
    @JsonIgnore
    @OneToMany(mappedBy = "away", orphanRemoval = true, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Match> awayMatches;
    
    @JsonIgnore
    @OneToMany(mappedBy = "host", orphanRemoval = true, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Match> hostMatches;
 

    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public Confederation getConfederation() {
        return confederation;
    }

    public void setConfederation(Confederation confederation) {
        this.confederation = confederation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Match> getAwayMatches() {
        return awayMatches;
    }

    public void setAwayMatches(List<Match> awayMatches) {
        this.awayMatches = awayMatches;
    }

    public List<Match> getHostMatches() {
        return hostMatches;
    }

    public void setHostMatches(List<Match> hostMatches) {
        this.hostMatches = hostMatches;
    }
    

    @Override
    public String toString() {
        return "Selection{" + "id=" + id + ", confederation=" + confederation + ", name=" + name + ", rang=" + rang + ", points=" + points + ", active=" + active + ", user=" + user + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Selection other = (Selection) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
