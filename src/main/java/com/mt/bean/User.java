package com.mt.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mt.annotaion.Pick;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User implements Serializable {
    @Pick(level = "vip1", note = "level higher, get more bonus")
    @Id
    @Column(name = "PK_USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "PERSON_ID")
    private Long personId;


    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }


    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "SITE_ID")
    private Integer siteId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(targetEntity = Order.class,
                   mappedBy = "personId",
                   cascade = CascadeType.ALL)
    private Collection<Order> orders;


    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * please not include the fields who was fetched as Lazy policy loading.
     * otherwise the Lazy fetching will be invalid because the toString() will
     * be invoked by Hibernaate
     * @return
     */
    @Override public String toString() {
        return "User{" +
            "userId=" + userId +
            ", personId=" + personId +
            ", userName='" + userName + '\'' +
            ", siteId=" + siteId +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", nickName='" + nickName + '\'' +
            ", status=" + status +
            ", orders=" + "not include orders entity in the toString() if lazy fetch" +
            '}';
    }


}
