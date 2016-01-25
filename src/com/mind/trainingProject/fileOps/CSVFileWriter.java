//=================================================================================================
// Project:		TrainingProject
// File :       CSVFileWriter
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.fileOps;

import static com.mind.trainingProject.model.LoggingSample.logger;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 */
public class CSVFileWriter
{
   
    
    public void appendLine( String path,
                            String line )
    {
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter( new FileWriter( path, true ) );
            bw.write( line + "\n" );
            bw.flush( );
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
                bw.close( );
            }
            catch( IOException e )
            {
                logger.error( "buffer close exception", e );
            }
        }
    }
}
