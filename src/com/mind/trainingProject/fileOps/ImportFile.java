//=================================================================================================
// Project:		TrainingProject
// File :       ImportFile
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.fileOps;

import static com.mind.trainingProject.model.LoggingSample.logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.mind.trainingProject.JDBCOperations.JDBCAccount;
import com.mind.trainingProject.model.Account;
import com.mind.trainingProject.parser.CSVParser;
import com.mind.trainingProject.parser.CSVValidator;

/**
 * 
 */
public class ImportFile extends Thread
{
    private final String path;
    public ImportFile(String path){
        this.path=path;
    }
    @Override
    public void run(){
        CSVValidator validator = new CSVValidator( );
        CSVFileWriter fileWriter = new CSVFileWriter( );
        JDBCAccount JDBCaccount = new JDBCAccount( );
        CSVParser CSVparser = new CSVParser( );
        BufferedReader br = null;
        try
        {
            br = new BufferedReader( new FileReader( path ) );
            logger.info( "File successfully read from: " + path );

            String line;
            while( ( line = br.readLine( ) ) != null )
            {

                String s = line;
                String[] codes;
                logger.info( "Importing line: " + line );

                String[] splitted = CSVparser.parseLine( s, ',' );  // should I close this stream only after all lines are parsed ?

                CSVparser.deleteQuotes( splitted );

                codes = validator.validateAll( splitted );
                if( codes[1] == null )
                {
                    fileWriter.appendLine( "Import.err", line + "   ->    " + codes[0] );
                    continue;
                }

                if( JDBCaccount.insertAccountWithoutId( new Account( 0, splitted[0], splitted[1], Integer.parseInt( splitted[2] ),
                                                                     Integer.parseInt( splitted[3] ), splitted[4], splitted[5], splitted[6],
                                                                     codes[0], codes[1] ) ) == -1 )
                {
                    logger.warn( "Something went wrong with account insertion!" );
                }

            }

        }
        catch( FileNotFoundException fe )
        {
            logger.error( "File Not Found", fe );
        }
        catch( Exception e )
        {
            logger.error( "Exception", e );
        }
        finally
        {
            try
            {
                br.close( );
            }
            catch( IOException e )
            {
                logger.error( "Closing stream exception: ", e );
            }
        }
    }
    
    //NOT USED
    public void parseFile( String path )
    {
        CSVValidator validator = new CSVValidator( );
        CSVFileWriter fileWriter = new CSVFileWriter( );
        JDBCAccount JDBCaccount = new JDBCAccount( );
        CSVParser CSVparser = new CSVParser( );
        BufferedReader br = null;
        try
        {
            br = new BufferedReader( new FileReader( path ) );
            logger.info( "File successfully read from: " + path );

            String line;
            while( ( line = br.readLine( ) ) != null )
            {

                String s = line;
                String[] codes;
                logger.info( "Processing line: " + line );

                String[] splitted = CSVparser.parseLine( s, ',' );  // should I close this stream only after all lines are parsed ?

                CSVparser.deleteQuotes( splitted );

                codes = validator.validateAll( splitted );
                if( codes[1] == null )
                {
                    fileWriter.appendLine( "Import.err", line + "   ->    " + codes[0] );
                    continue;
                }

                if( JDBCaccount.insertAccountWithoutId( new Account( 0, splitted[0], splitted[1], Integer.parseInt( splitted[2] ),
                                                                     Integer.parseInt( splitted[3] ), splitted[4], splitted[5], splitted[6],
                                                                     codes[0], codes[1] ) ) == -1 )
                {
                    logger.warn( "Something went wrong with account insertion!" );
                }

            }

        }
        catch( FileNotFoundException fe )
        {
            logger.error( "File Not Found", fe );
        }
        catch( Exception e )
        {
            logger.error( "Exception", e );
        }
        finally
        {
            try
            {
                br.close( );
            }
            catch( IOException e )
            {
                logger.error( "Closing stream exception: ", e );
            }
        }
    }

    /**
     * @param args
     */
    public static void main( String[] args )
    {

        ImportFile importFile = new ImportFile( "import.csv" );

    }

}
