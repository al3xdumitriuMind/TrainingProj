//=================================================================================================
// Project:		TrainingProject
// File :       OracleConnectionSingleton
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.JDBCOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;
import static com.mind.trainingProject.model.LoggingSample.logger;

/**
 * 
 */
public class OracleConnectionSingleton
{
    private static Connection connection = null;

    private OracleConnectionSingleton( )
    {

    }

    public static Connection getInstance( )
    {
        if( connection == null )
        {
            try
            {
                DriverManager.registerDriver( new OracleDriver( ) );
                connection = DriverManager.getConnection( "jdbc:oracle:thin:@10.0.0.185:1521:BILL", "train2", "train2" );
                return connection;
            }
            catch( SQLException e )
            {
                logger.error( "The connection with oracle failed", e );
            }

        }
        else
        {
            return connection;
        }
        return connection;
    }
    
    
    public static void closeConnection(){
        try
        {
            connection.close( );
        }
        catch( SQLException e )
        {
            logger.error( "connection close exception", e );
        }
    }
}
