//=================================================================================================
// Project:		TrainingProject
// File :       Account
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.model;

/**
 * 
 */
public class Account
{
    private int    id;
    private String firstName;
    private String secondName;
    private int    homeTelNum;
    private int    mobileTelNum;
    private String address;
    private String city;
    private String state;
    private String locale;
    private String job;

    public Account( )
    {

    }

    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param homeTelNum
     * @param mobileTelNum
     * @param address
     * @param city
     * @param state
     * @param locale
     * @param job
     */
    public Account( int id,
                    String firstName,
                    String lastName,
                    int homeTelNum,
                    int mobileTelNum,
                    String address,
                    String city,
                    String state,
                    String locale,
                    String job )
    {
        super( );
        this.id = id;
        this.firstName = firstName;
        this.secondName = lastName;
        this.homeTelNum = homeTelNum;
        this.mobileTelNum = mobileTelNum;
        this.address = address;
        this.city = city;
        this.state = state;
        this.locale = locale;
        this.job = job;
    }

    /**
     * @return the id
     */
    public int getId( )
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( int id )
    {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName( )
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    /**
     * @return the secondName
     */
    public String getLastName( )
    {
        return secondName;
    }

    /**
     * @param secondName the secondName to set
     */
    public void setLastName( String secondName )
    {
        this.secondName = secondName;
    }

    /**
     * @return the homeTelNum
     */
    public int getHomeTelNum( )
    {
        return homeTelNum;
    }

    /**
     * @param homeTelNum the homeTelNum to set
     */
    public void setHomeTelNum( int homeTelNum )
    {
        this.homeTelNum = homeTelNum;
    }

    /**
     * @return the mobileTelNum
     */
    public int getMobileTelNum( )
    {
        return mobileTelNum;
    }

    /**
     * @param mobileTelNum the mobileTelNum to set
     */
    public void setMobileTelNum( int mobileTelNum )
    {
        this.mobileTelNum = mobileTelNum;
    }

    /**
     * @return the address
     */
    public String getAddress( )
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress( String address )
    {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity( )
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity( String city )
    {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState( )
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState( String state )
    {
        this.state = state;
    }

    /**
     * @return the locale
     */
    public String getLocale( )
    {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale( String locale )
    {
        this.locale = locale;
    }

    /**
     * @return the job
     */
    public String getJob( )
    {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob( String job )
    {
        this.job = job;
    }

}
