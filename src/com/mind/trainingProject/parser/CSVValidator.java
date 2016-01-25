//=================================================================================================
// Project:		TrainingProject
// File :       CSVValidator
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.parser;

import static com.mind.trainingProject.model.LoggingSample.logger;

import java.sql.Connection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.mind.trainingProject.JDBCOperations.JDBCJob;
import com.mind.trainingProject.JDBCOperations.JDBCLocale;

/**
 * 
 */
public class CSVValidator
{
    private final static int                  NUMBER_OF_TOKENS          = 9;

    private final static int                  FIRST_NAME_MAX_LENGTH     = 30;
    private final static int                  LAST_NAME_MAX_LENGTH      = 30;
    private final static int                  HOME_TEL_NUM_MAX_LENGTH   = 10;
    private final static int                  MOBILE_TEL_NUM_MAX_LENGTH = 10;
    private final static int                  ADDRESS_MAX_LENGTH        = 30;
    private final static int                  CITY_MAX_LENGTH           = 15;
    private final static int                  STATE_MAX_LENGTH          = 15;
    private final static int                  LOCALE_MAX_LENGTH         = 30;
    private final static int                  JOB_MAX_LENGTH            = 30;

    private final static int                  FIRST_NAME_INDEX          = 0;
    private final static int                  LAST_NAME_INDEX           = 1;
    private final static int                  HOME_TEL_NUM_INDEX        = 2;
    private final static int                  MOBILE_TEL_NUM_INDEX      = 3;
    private final static int                  ADDRESS_INDEX             = 4;
    private final static int                  CITY_NAME_INDEX           = 5;
    private final static int                  STATE_NAME_INDEX          = 6;
    private final static int                  JOB_CODE_INDEX            = 7;
    private final static int                  LOCALE_CODE_INDEX         = 8;

    private ConcurrentHashMap<String, String> jobs;
    private ConcurrentHashMap<String, String> locales;

    public String[] validateAll( String[] splitted,
                                 Connection connection )
    {

        String[] codes = new String[2];

        String jobCode, localeCode;
        if( splitted.length != NUMBER_OF_TOKENS )
        {
            codes[0] = "Incorrect number of tokens";
            logger.warn( "Incorrect number of tokens" );
        }

        else if( !validateFirstName( splitted[FIRST_NAME_INDEX] ) )
        {
            codes[0] = "Invalid first name";
            logger.warn( "Invalid first name" );
        }
        else if( !validateLastName( splitted[LAST_NAME_INDEX] ) )
        {
            codes[0] = "Invalid last name";
            logger.warn( "Invalid last name" );
        }
        else if( !validateHomeTelNumber( splitted[HOME_TEL_NUM_INDEX] ) )
        {
            codes[0] = "Invalid home telefone number";
            logger.warn( "Invalid home telefone number" );
        }
        else if( !validateMobileTelNumber( splitted[MOBILE_TEL_NUM_INDEX] ) )
        {
            codes[0] = "Invalid mobile phone";
            logger.warn( "Invalid mobile phone" );
        }
        else if( splitted[ADDRESS_INDEX].length( ) > ADDRESS_MAX_LENGTH )
        {
            codes[0] = "Invalid address(over 30 characters)";
            logger.warn( "Invalid address(over 30 characters)" );
        }
        else if( splitted[CITY_NAME_INDEX].length( ) > CITY_MAX_LENGTH )
        {
            codes[0] = "Invalid city(over 15 characters)";
            logger.warn( "Invalid city(over 15 characters)" );
        }
        else if( splitted[STATE_NAME_INDEX].length( ) > STATE_MAX_LENGTH )
        {
            codes[0] = "Invalid state(over 15 characters)";
            logger.warn( "Invalid state(over 15 characters)" );
        }
        else if( ( jobCode = validateJob( splitted[JOB_CODE_INDEX], connection) ) == null )
        {
            codes[0] = "Invalid job";
        }
        else if( ( localeCode = validateLocale( splitted[LOCALE_CODE_INDEX], connection ) ) == null )
        {
            codes[0] = "Invalid locale";
        }
        else
        {
            codes[0] = localeCode;
            codes[1] = jobCode;
        }
        return codes;
    }

    private boolean validateFirstName( String firstName )
    {
        if( firstName.length( ) > FIRST_NAME_MAX_LENGTH )
        {
            return false;
        }
        if( firstName.trim( ).isEmpty( ) )
        {
            return false;
        }
        return true;
    }

    private boolean validateLastName( String lastName )
    {
        if( lastName.length( ) > LAST_NAME_MAX_LENGTH )
        {
            return false;
        }
        if( lastName.trim( ).isEmpty( ) )
        {
            return false;
        }
        return true;
    }

    private boolean validateMobileTelNumber( String mobile )
    {

        if( mobile.length( ) > MOBILE_TEL_NUM_MAX_LENGTH )
        {
            return false;
        }

        try
        {
            Integer.parseInt( mobile );
        }
        catch( NumberFormatException e )
        {
            return false;
        }

        return true;
    }

    private boolean validateHomeTelNumber( String homeNumber )
    {

        if( homeNumber.length( ) > HOME_TEL_NUM_MAX_LENGTH )
        {
            return false;
        }

        try
        {
            Integer.parseInt( homeNumber );
        }
        catch( NumberFormatException e )
        {
            return false;
        }

        return true;
    }

    private String validateJob( String job,
                                Connection connection )
    {
        String code = null;
        JDBCJob JDBCjob = new JDBCJob( );
        if( job.trim( ).isEmpty( ) || job.length( ) > JOB_MAX_LENGTH )
        {
            logger.warn( "Invalid job " );
            return code;
        }

        if( jobs == null )
        {
            if( ( jobs = JDBCjob.getAllJobs( connection ) ) == null )
            {
                logger.error( "Couldn't save jobs into local variable" );
            }
        }

        if( ( code = validateLocalJob( job ) ) != null )
        {
            return code;
        }
        else
        {
            logger.warn( "Job not in the database! " );
            return code;
        }

    }

    public String validateLocalJob( String codeOrDescription )
    {

        Set<String> codes = jobs.keySet( );

        if( codes.contains( codeOrDescription ) )
        {
            return codeOrDescription;
        }

        Iterator<String> it = codes.iterator( );

        while( it.hasNext( ) )
        {
            String currentKey = it.next( );
            if( jobs.get( currentKey ).equals( codeOrDescription ) )
                return currentKey;
        }

        return null;
    }

    private String validateLocale( String locale,
                                   Connection connection )
    {
        String code = null;
        JDBCLocale JDBClocale = new JDBCLocale( );
        if( locale.trim( ).isEmpty( ) || locale.length( ) > LOCALE_MAX_LENGTH )
        {
            logger.warn( "Invalid locale " );
            return code;
        }

        if( locales == null )
        {
            if( ( locales = JDBClocale.saveAllLocales( connection ) ) == null )
            {
                logger.error( "Couldn't save jobs into local variable" );
            }
        }

        if( ( code = validateLocalLocale( locale ) ) != null )
        {
            return code;
        }

        else
        {
            logger.warn( "Locale not in the database! " );
            return code;
        }

    }

    public String validateLocalLocale( String codeOrDescription )
    {

        Set<String> codes = locales.keySet( );

        if( codes.contains( codeOrDescription ) )
        {
            return codeOrDescription;
        }

        Iterator<String> it = codes.iterator( );

        while( it.hasNext( ) )
        {
            String currentKey = it.next( );
            if( locales.get( currentKey ).equals( codeOrDescription ) )
                return currentKey;
        }

        return null;
    }

}
