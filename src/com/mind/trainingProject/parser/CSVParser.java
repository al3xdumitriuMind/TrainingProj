//=================================================================================================
// Project:		TrainingProject
// File :       CSVParser
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class CSVParser
{

    public String[] parseLine( String line,
                               Character separator )
    {

        List<String> splitted = new ArrayList<String>( );
        int begin = 0, i;
        boolean doubleQuoteBegan = false;
        for( i = 0; i < line.length( ); i++ )
        {
            if( line.charAt( i ) == '\"' )
            {
                if( doubleQuoteBegan )
                {
                    doubleQuoteBegan = false;
                }
                else
                {
                    doubleQuoteBegan = true;
                }
            }
            if( line.charAt( i ) == separator && !doubleQuoteBegan )
            {
                splitted.add( line.substring( begin, i ) );
                begin = i + 1;
            }

        }
        splitted.add( line.substring( begin, i ) );
        String[] splittedArray = new String[splitted.size( )];
        splittedArray = splitted.toArray( splittedArray );
        return splittedArray;
    }

    public void deleteQuotes( String[] splitted )
    {
        for( int i = 0; i < splitted.length; i++ )
        {
            if( !splitted[i].equals( "" ) && splitted[i].charAt( 0 ) == '"' )
                splitted[i] = splitted[i].substring( 1, splitted[i].length( ) - 1 );

        }
    }

}
