//=================================================================================================
// Project:		TrainingProject
// File :       JDBCJob
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.JDBCOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import static com.mind.trainingProject.model.LoggingSample.logger;

/**
 * 
 */
public class JDBCJob
{

    public ConcurrentHashMap<String, String> getAllJobs( Connection connectionInstance )
    {

        Connection connection = connectionInstance;
        PreparedStatement prepareStatement = null;
        ResultSet result = null;
        ConcurrentHashMap<String, String> jobs = new ConcurrentHashMap<String, String>( );
        try
        {
            prepareStatement = connection.prepareStatement( "SELECT * FROM TRAIN2.JOB" );
            result = prepareStatement.executeQuery( );
            while( result.next( ) )
            {
                jobs.put( result.getString( "CODE" ), result.getString( "DESCRIPTION" ) );

            }
        }
        catch( SQLException e )

        {
            logger.error( "getJobs SQL Exception", e );
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
        return jobs;
    }

    // //NOT USED ANYMORE!
    // public String validateJobDescription( String jobDescription )
    // {
    //
    // Connection connection = OracleConnectionSingleton.getInstance( );
    // PreparedStatement prepareStatement = null;
    // ResultSet result = null;
    // try
    // {
    // prepareStatement = connection.prepareStatement( "SELECT CODE as c FROM TRAIN2.JOB WHERE DESCRIPTION = ?" );
    // prepareStatement.setString( 1, jobDescription );
    // result = prepareStatement.executeQuery( );
    // if( result.next( ) )
    // {
    // return result.getString( "c" );
    // }
    // return null;
    // }
    // catch( SQLException e )
    // {
    // logger.error( "validateJobDescription SQL Exception", e );
    // }
    // finally
    // {
    // try
    // {
    // if( prepareStatement != null )
    // prepareStatement.close( );
    // if( result != null )
    // result.close( );
    //
    // }
    // catch( SQLException e )
    // {
    // logger.error( "sql close exception", e );
    // return null;
    // }
    // }
    //
    // return null;
    // }
    //
    // //NOT USED ANYMORE
    // public String validateJobCode( String code )
    // {
    //
    // Connection connection = OracleConnectionSingleton.getInstance( );
    // PreparedStatement prepareStatement = null;
    // ResultSet result = null;
    // try
    // {
    // prepareStatement = connection.prepareStatement( "SELECT CODE as c FROM TRAIN2.JOB WHERE CODE = ?" );
    // prepareStatement.setString( 1, code );
    // result = prepareStatement.executeQuery( );
    // if( result.next( ) )
    // {
    // return result.getString( "c" );
    //
    // }
    // return null;
    // }
    // catch( SQLException e )
    //
    // {
    // logger.error( "validateJobCode SQL Exception", e );
    // }
    // finally
    // {
    // try
    // {
    // if( prepareStatement != null )
    // prepareStatement.close( );
    // if( result != null )
    // result.close( );
    //
    // }
    // catch( SQLException e )
    // {
    // logger.error( "sql close exception", e );
    // return null;
    // }
    // }
    // return null;
    //
    // }

    public static void main( String[] args )
    {
        // JDBCJob job = new JDBCJob( );
        // System.out.println( job.validateJobDescription( "Tester" ) );
        // System.out.println( job.validateJobCode( "2" ) );
        // job.saveAllJobs( );
        // System.out.println( job.validateLocalJob("alceva") );
    }

}
