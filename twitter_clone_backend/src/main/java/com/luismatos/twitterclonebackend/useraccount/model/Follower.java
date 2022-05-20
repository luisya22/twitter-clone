package com.luismatos.twitterclonebackend.useraccount.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Follower {

    @EmbeddedId
    private FollowerId id;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private User follower;
}
