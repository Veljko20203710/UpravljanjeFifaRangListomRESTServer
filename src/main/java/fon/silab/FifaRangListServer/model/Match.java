/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Veljko
 */

@Entity()
@Table(name = "MATCH_TABLE")
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "MATCH_DATE")
    @NotNull()
    private Date date;

    @OneToOne()
    @JoinColumn(name = "MATCH_HOST")
    @NotNull()
    private Selection host;

    @OneToOne()
    @JoinColumn(name = "MATCH_AWAY")
    @NotNull()
    private Selection away;

    @PositiveOrZero
    private int hostGoals;
    
    @PositiveOrZero
    private int awayGoals;

    @ManyToOne()
    @NotNull()
    private MatchType matchType;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    @NotNull()
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Selection getHost() {
        return host;
    }

    public void setHost(Selection host) {
        this.host = host;
    }

    public Selection getAway() {
        return away;
    }

    public void setAway(Selection away) {
        this.away = away;
    }

    public int getHostGoals() {
        return hostGoals;
    }

    public void setHostGoals(int hostGoals) {
        this.hostGoals = hostGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", date=" + date + ", host=" + host + ", away=" + away + ", hostGoals=" + hostGoals + ", awayGoals=" + awayGoals + ", matchType=" + matchType + ", user=" + user + '}';
    }

    
    
   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.date);
        hash = 59 * hash + Objects.hashCode(this.host);
        hash = 59 * hash + Objects.hashCode(this.away);
        hash = 59 * hash + this.hostGoals;
        hash = 59 * hash + this.awayGoals;
        hash = 59 * hash + Objects.hashCode(this.matchType);
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
        final Match other = (Match) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

    


    
    
    

}
