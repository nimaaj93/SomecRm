package com.farafarin.mata.common.security.model;

import com.farafarin.mata.common.entity.CorporateUserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by K550 VX on 11/12/2017.
 */
public class UserProfile implements Authentication,UserDetails {

    private Long authenticationId;
    private Long userId;
    private String firstNameFa;
    private String lastNameFa;
    private String firstNameEn;
    private String lastNameEn;
    private String nationalCode;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private Date expireDate;
    private Long status;

    private UserCredential userCredential;
    private List<UserAuthority> userAuthorities;
    private CorporateUserEntity corporateUserEntity;
    private boolean authenticated = false;

    public Long getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(Long authenticationId) {
        this.authenticationId = authenticationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstNameFa() {
        return firstNameFa;
    }

    public void setFirstNameFa(String firstNameFa) {
        this.firstNameFa = firstNameFa;
    }

    public String getLastNameFa() {
        return lastNameFa;
    }

    public void setLastNameFa(String lastNameFa) {
        this.lastNameFa = lastNameFa;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    public List<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(List<UserAuthority> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    public CorporateUserEntity getCorporateUserEntity() {
        return corporateUserEntity;
    }

    public void setCorporateUserEntity(CorporateUserEntity corporateUserEntity) {
        this.corporateUserEntity = corporateUserEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userAuthorities;
    }

    @Override
    public String getPassword() {
        if (this.userCredential != null) {
            return this.userCredential.getPassword();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if (this.userCredential != null) {
            return this.userCredential.getUsername();
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public UserCredential getCredentials() {
        return userCredential;
    }

    @Override
    public Object getDetails() {
        return this;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        if (this.userCredential != null) {
            return this.userCredential.getUsername();
        }
        return null;
    }


    public UserInformation getUserInformation() {
        return new UserInformation(this);
    }

}
