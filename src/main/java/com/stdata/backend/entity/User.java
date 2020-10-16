package com.stdata.backend.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity(name = "User")
@Table(name = "t_user")
public class User {

    @Id
    private String username;
    @Column
    @NotEmpty
    private String password;
    @Column
    private String nickName;
    @Column(unique = true)
    private String email;
    @Column
    private boolean enable = true;
    @Column
    private boolean admin = false;
    @Column
    private long createTimestamp;
    @Column
    private long activeTimestamp;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public long getActiveTimestamp() {
        return activeTimestamp;
    }

    public void setActiveTimestamp(long activeTimestamp) {
        this.activeTimestamp = activeTimestamp;
    }

    @PrePersist
    private void prePersist() {
        this.createTimestamp = new Date().getTime();
    }
}
