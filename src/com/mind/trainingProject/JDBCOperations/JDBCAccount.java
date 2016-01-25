//=================================================================================================
// Project:		TrainingProject
// File :       JDBCAccount
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.JDBCOperations;

import static com.mind.trainingProject.model.LoggingSample.logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mind.trainingProject.model.Account;

/**
 * 
 */
public class JDBCAccount 
{
    public int insertAccountWithoutId( Account account, Connection connectionInstance )
    {

        Connection connection = connectionInstance;
        PreparedStatement prepareStatement = null;
        try
        {
            prepareStatement = connection.prepareStatement( "INSERT INTO TRAIN2.ACCOUNT"
                                                            + "(ID, FIRST_NAME, LAST_NAME, HOME_TEL_NUM, MOBILE_TEL_NUM, ADDRESS, CITY, STATE, LOCALE, JOB)  VALUES"
                                                            + "(account_id_seq.nextval,?,?,?,?,?,?,?,?,?)" );

            prepareStatement.setString( 1, account.getFirstName( ) );
            prepareStatement.setString( 2, account.getLastName( ) );
            prepareStatement.setInt( 3, account.getHomeTelNum( ) );
            prepareStatement.setInt( 4, account.getMobileTelNum( ) );
            prepareStatement.setString( 5, account.getAddress( ) );
            prepareStatement.setString( 6, account.getCity( ) );
            prepareStatement.setString( 7, account.getState( ) );
            prepareStatement.setString( 8, account.getLocale( ) );
            prepareStatement.setString( 9, account.getJob( ) );

            int result = prepareStatement.executeUpdate( );
            return result;
        }
        catch( SQLException e )
        {
            logger.error( "getMaxId SQL Exception", e );
        }
        finally
        {
            try
            {
                prepareStatement.close( );
            }
            catch( SQLException e )
            {
                logger.error( "closing prepareStatement exception", e );
            }
        }

        return -1;

    }
//    //NOT USED
//    public int exportToCSV( String path )
//    {
//        CSVFileWriter CSVfileWriter = new CSVFileWriter( );
//        Connection connection = OracleConnectionSingleton.getInstance( );
//        PreparedStatement prepareStatement = null;
//        ResultSet result = null;
//        String line;
//        try
//        {
//
//            prepareStatement = connection.prepareStatement( "SELECT *  FROM TRAIN2.ACCOUNT ORDER BY ID" );
//            result = prepareStatement.executeQuery( );
//            while( result.next( ) )
//            {
//                line = result.getInt( "id" ) + "," + result.getString( "first_name" ) + "," + result.getString( "last_name" ) + ","
//                       + result.getInt( "home_tel_num" ) + "," + result.getInt( "mobile_tel_num" ) + "," + result.getString( "address" )
//                       + "," + result.getString( "city" ) + "," + result.getString( "state" ) + "," + result.getString( "job" ) + ","
//                       + result.getString( "locale" ) ;
//                CSVfileWriter.appendLine( path, line );
//
//            }
//
//        }
//        catch( SQLException e )
//
//        {
//            logger.error( "exportToCSV SQL Exception", e );
//            return -1;
//        }
//        finally
//        {
//            try
//            {
//                if( prepareStatement != null )
//                    prepareStatement.close( );
//                if( result != null )
//                    result.close( );
//
//            }
//            catch( SQLException e )
//            {
//                logger.error( "sql close exception", e );
//                return -1;
//            }
//        }
//
//        return 1;
//
//    }
//
//    //NOT USED
//    public int getMaxId( )
//    {
//
//        Connection connection = OracleConnectionSingleton.getInstance( );
//        PreparedStatement prepareStatement = null;
//        ResultSet result = null;
//        try
//        {
//            prepareStatement = connection.prepareStatement( "SELECT MAX(ID) as max FROM TRAIN2.ACCOUNT" );
//            result = prepareStatement.executeQuery( );
//            if( result.next( ) )
//            {
//                return result.getInt( "max" );
//
//            }
//            return -1;
//        }
//        catch( SQLException e )
//
//        {
//            logger.error( "getMaxId SQL Exception", e );
//        }
//        finally
//        {
//            try
//            {
//                if( prepareStatement != null )
//                    prepareStatement.close( );
//                if( result != null )
//                    result.close( );
//
//            }
//            catch( SQLException e )
//            {
//                logger.error( "sql close exception", e );
//                return -1;
//            }
//        }
//
//        return -1;
//
//    }
//
//    //NOT USED
//    public int insertAccount( Account account )
//    {
//        Connection connection = OracleConnectionSingleton.getInstance( );
//        PreparedStatement prepareStatement = null;
//
//        try
//        {
//            prepareStatement = connection.prepareStatement( "INSERT INTO TRAIN2.ACCOUNT"
//                                                            + "(ID, FIRST_NAME, LAST_NAME, HOME_TEL_NUM, MOBILE_TEL_NUM, ADDRESS, CITY, STATE, LOCALE, JOB)  VALUES"
//                                                            + "(?,?,?,?,?,?,?,?,?,?)" );
//
//            prepareStatement.setInt( 1, account.getId( ) );
//            prepareStatement.setString( 2, account.getFirstName( ) );
//            prepareStatement.setString( 3, account.getLastName( ) );
//            prepareStatement.setInt( 4, account.getHomeTelNum( ) );
//            prepareStatement.setInt( 5, account.getMobileTelNum( ) );
//            prepareStatement.setString( 6, account.getAddress( ) );
//            prepareStatement.setString( 7, account.getCity( ) );
//            prepareStatement.setString( 8, account.getState( ) );
//            prepareStatement.setString( 9, account.getLocale( ) );
//            prepareStatement.setString( 10, account.getJob( ) );
//
//            int result = prepareStatement.executeUpdate( );
//            return result;
//        }
//        catch( SQLException e )
//
//        {
//            logger.error( "getMaxId SQL Exception", e );
//        }
//        finally
//        {
//            try
//            {
//                if( prepareStatement != null )
//                    prepareStatement.close( );
//
//            }
//            catch( SQLException e )
//            {
//                logger.error( "sql close exception", e );
//                return -1;
//            }
//        }
//
//        return -1;
//    }

    

    public static void main( String[] args )
    {
        // JDBCAccount JDBCaccount = new JDBCAccount( );
        // System.out.println( JDBCaccount.getMaxId( ) );
        // System.out.println( JDBCaccount.validateJobCode( "7" ) );
        //
        // Account account = new Account( 4, "Alexx", "Dumitriu", 0230571242, 0230571242, "strada FIerbinte", "iasi",
        // "iasi", "AAa", "2" );
        // System.out.println( JDBCaccount.insertAccountWithoutId( account ) );

        // exportToCSV( "export.csv" );
    }
}
