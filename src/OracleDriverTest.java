import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

//=================================================================================================
// Project:		TrainingProject
// File :       OracleDriverTest
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

/**
 * 
 */
public class OracleDriverTest
{

    public static void main( String[] args )
    {
         Connection connection=null;
         PreparedStatement prepareStatement=null;
         try
         {
         DriverManager.registerDriver( new OracleDriver( ) );
         connection = DriverManager.getConnection( "jdbc:oracle:thin:@10.0.0.185:1521:BILL", "train2", "train2" );
         prepareStatement = connection.prepareStatement( "SELECT SYSDATE as s FROM DUAL" );
         ResultSet result = prepareStatement.executeQuery( );
         if( result.next( ) )
         {
         System.out.println( result.getString( "s" ) );
         }
         }
         catch( SQLException e )
         {
        
         e.printStackTrace();
         }


    }

}
