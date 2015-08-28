package com.ebiz.mmt.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * @author Wu,Yang
 * @version 2010-10-25 11:39:12
 */
public class StdEntpProdJoinBrandId extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;  
    
    private Long entp_id;  
    
    private Long brand_id;  
    
    private Long own_sys;  
    
    public StdEntpProdJoinBrandId() {

    } 

    public Long getId(){
        return id;
    } 
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getEntp_id(){
        return entp_id;
    } 
    
    public void setEntp_id(Long entp_id) {
        this.entp_id = entp_id;
    }
    
    public Long getBrand_id(){
        return brand_id;
    } 
    
    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }
    
    public Long getOwn_sys(){
        return own_sys;
    } 
    
    public void setOwn_sys(Long own_sys) {
        this.own_sys = own_sys;
    }
    
    @Override 
    public String toString() { 
        return ReflectionToStringBuilder.toString(this); 
    }
}