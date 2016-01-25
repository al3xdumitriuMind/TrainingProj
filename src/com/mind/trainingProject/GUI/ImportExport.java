//=================================================================================================
// Project:		TrainingProject
// File :       ButtonDemo
//
// Created by:	alexandru.dumitriu, 2016
//-------------------------------------------------------------------------------------------------
// Copyright:   MIND CTI Ltd.
//=================================================================================================

package com.mind.trainingProject.GUI;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mind.trainingProject.JDBCOperations.JDBCAccountExport;
import com.mind.trainingProject.fileOps.ImportFile;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImportExport extends JPanel implements ActionListener
{
    /**
     * 
     */
    private static final long serialVersionUID = -5360873708045489441L;
    protected JButton    importButton, exportButton;
    protected JTextField importTextField, exportTextField;

    public ImportExport( )
    {
        this.setLayout( new BorderLayout( ) );

        JPanel importPannel = new JPanel( );
        importPannel.setLayout( new BorderLayout( ) );

        importButton = new JButton( "Import" );
        importButton.setVerticalTextPosition( AbstractButton.CENTER );
        importButton.setHorizontalTextPosition( AbstractButton.LEADING );
        importButton.setActionCommand( "import" );
        importButton.addActionListener( this );
        importButton.setToolTipText( "Click this button to disable the middle button." );

        importTextField = new JTextField( 50 );
        importPannel.add( importTextField, BorderLayout.WEST );
        importPannel.add( importButton, BorderLayout.EAST );

        JPanel exportPannel = new JPanel( );
        exportPannel.setLayout( new BorderLayout( ) );

        exportButton = new JButton( "Export" );
        exportButton.setActionCommand( "export" );
        exportButton.addActionListener( this );
        exportButton.setToolTipText( "Click this button to enable the middle button." );

        exportTextField = new JTextField( 50 );

        exportPannel.add( exportTextField, BorderLayout.WEST );
        exportPannel.add( exportButton, BorderLayout.EAST );

        add( importPannel, BorderLayout.NORTH );
        add( exportPannel, BorderLayout.SOUTH );
    }

    public void actionPerformed( ActionEvent e )
    {
        if( "import".equals( e.getActionCommand( ) ) )
        {
            ImportFile importFile = new ImportFile( importTextField.getText( ) );
            importFile.start( );
        }
        else
        {
            JDBCAccountExport export = new JDBCAccountExport(exportTextField.getText( ) );
            export.start(  );
        }
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI( )
    {

        // Create and set up the window.
        JFrame frame = new JFrame( "CSV: Import Export " );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Create and set up the content pane.
        ImportExport newContentPane = new ImportExport( );
        newContentPane.setOpaque( true ); // content panes must be opaque
        frame.setContentPane( newContentPane );

        // Display the window.
        frame.pack( );
        frame.setVisible( true );
    }

    public static void main( String[] args )
    {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater( new Runnable( )
        {
            public void run( )
            {
                createAndShowGUI( );
            }
        } );
    }
}