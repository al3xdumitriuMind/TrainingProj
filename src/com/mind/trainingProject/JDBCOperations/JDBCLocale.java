//=================================================================================================
// Project:		TrainingProject
// File :       JDBCLocale
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.JDBCOperations;

import static com.mind.trainingProject.model.LoggingSample.logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 */
public class JDBCLocale
{
    // NOT USED ANYMORE
    public String validateLocaleDescription( String localeDescription )
    {

        Connection connection = OracleConnectionSingleton.getInstance( );
        PreparedStatement prepareStatement = null;
        ResultSet result = null;
        try
        {
            prepareStatement = connection.prepareStatement( "SELECT CODE as c FROM TRAIN2.LOCALE WHERE DESCRIPTION = ?" );
            prepareStatement.setString( 1, localeDescription );
            result = prepareStatement.executeQuery( );
            if( result.next( ) )
            {
                return result.getString( "c" );
            }
            return null;
        }
        catch( SQLException e )
        {
            logger.error( "validateLocaleDescription SQL Exception", e );
        }
        finally
        {
            try
            {
                if( prepareStatement != null )
                    prepareStatement.close( );
                if( result != null )
                    result.close( );

            }
            catch( SQLException e )
            {
                logger.error( "sql close exception", e );
                return null;
            }
        }

        return null;
    }

    // NOT USED ANYMORE
    public String validateLocaleCode( String code )
    {

        Connection connection = OracleConnectionSingleton.getInstance( );
        PreparedStatement prepareStatement = null;
        ResultSet result = null;
        try
        {
            prepareStatement = connection.prepareStatement( "SELECT CODE as c FROM TRAIN2.LOCALE WHERE CODE = ?" );
            prepareStatement.setString( 1, code );
            result = prepareStatement.executeQuery( );
            if( result.next( ) )
            {
                return result.getString( "c" );

            }
            return null;
        }
        catch( SQLException e )

        {
            logger.error( "validateLocaleCode SQL Exception", e );
        }
        finally
        {
            try
            {
                if( prepareStatement != null )
                    prepareStatement.close( );
                if( result != null )
                    result.close( );

            }
            catch( SQLException e )
            {
                logger.error( "sql close exception", e );
                return null;
            }
        }

        return null;

    }

    public ConcurrentHashMap<String, String> saveAllLocales( )
    {

        Connection connection = OracleConnectionSingleton.getInstance( );
        PreparedStatement prepareStatement = null;
        ResultSet result = null;
        ConcurrentHashMap<String, String> locales = new ConcurrentHashMap<String, String>( );
        try
        {
            prepareStatement = connection.prepareStatement( "SELECT * FROM TRAIN2.LOCALE" );
            result = prepareStatement.executeQuery( );
            while( result.next( ) )
            {
                locales.put( result.getString( "CODE" ), result.getString( "DESCRIPTION" ) );

            }
        }
        catch( SQLException e )

        {
            logger.error( "getLocales SQL Exception", e );
            return null;
        }
        finally
        {
            try
            {
                if( prepareStatement != null )
                    prepareStatement.close( );
                if( result != null )
                    result.close( );

            }
            catch( SQLException e )
            {
                logger.error( "sql close exception", e );
            }

        }
        return locales;

    }

    public static void main( String[] args )
    {
        //JDBCLocale JDBClocale = new JDBCLocale( );
        // System.out.println( JDBClocale.validateLocaleDescription( "Suceava,ROMANIA" ) );
        // System.out.println( validateLocaleCode( "AAbsda" ) );
    }
}
