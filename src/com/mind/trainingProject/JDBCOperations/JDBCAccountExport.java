//=================================================================================================
// Project:		TrainingProject
// File :       JDBCAccountExport
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

import com.mind.trainingProject.fileOps.CSVFileWriter;

/**
 * 
 */
public class JDBCAccountExport extends Thread
{
    private final String path;

    public JDBCAccountExport( String path )
    {
        this.path = path;
    }
    
    @Override
    public void run(){
        CSVFileWriter CSVfileWriter = new CSVFileWriter( );
        Connection connection = OracleConnectionSingleton.getInstance( );
        PreparedStatement prepareStatement = null;
        ResultSet result = null;
        String line;
        try
        {

            prepareStatement = connection.prepareStatement( "SELECT *  FROM TRAIN2.ACCOUNT ORDER BY ID" );
            result = prepareStatement.executeQuery( );
            while( result.next( ) )
            {
                line = result.getInt( "id" ) + "," + result.getString( "first_name" ) + "," + result.getString( "last_name" ) + ","
                       + result.getInt( "home_tel_num" ) + "," + result.getInt( "mobile_tel_num" ) + "," + result.getString( "address" )
                       + "," + result.getString( "city" ) + "," + result.getString( "state" ) + "," + result.getString( "job" ) + ","
                       + result.getString( "locale" );
                logger.info( "Exporting line:"+line );
                CSVfileWriter.appendLine( path, line );

            }

        }
        catch( SQLException e )

        {
            logger.error( "exportToCSV SQL Exception", e );
            
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

        

    }

   
}
