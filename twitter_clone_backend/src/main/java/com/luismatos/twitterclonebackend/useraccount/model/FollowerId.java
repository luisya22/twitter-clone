package com.luismatos.twitterclonebackend.useraccount.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
public class FollowerId implements Serializable {

    @Column(name="user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name="follower_id", nullable = false, updatable = false)
    private Long followerId;

    public boolean equals(Object o){
        if(o == null){
            return false;
        }

        if(!(o instanceof FollowerId)){
            return false;
        }

        FollowerId other = (FollowerId) o;

        if(!(other.getUserId().equals(getUserId()))){
            return false;
        }

        if(!(other.getFollowerId().equals(getFollowerId()))){
            return false;
        }

        return true;
    }
}
