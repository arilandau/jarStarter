package com.feith.jar.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args ) throws IOException
    {
        String delimiter = args[0];
        String fileToReadPath = args[1];
        String printToFilePath = args[2];

        File fileToRead = new File( fileToReadPath );
        File fileToWrite = new File( printToFilePath );

        try ( Scanner scannerToRead = new Scanner( fileToRead );
                PrintStream printToFile = new PrintStream( fileToWrite ) )

        {
            String oneString = scannerToRead.useDelimiter( delimiter ).next();
            System.out.println( "oneString" + oneString );
            String[] splitString = oneString.split( "\\n" );

            for ( int i = 0; i < splitString.length; i++ )
            {

                if ( splitString[i].startsWith( "CASEID: " ) || splitString[i].startsWith( "VISIT TYPE: " )
                        || splitString[i].startsWith( "FACILITY: " ) )

                {
                    int startString = splitString[i].indexOf( ": " ) + 2;
                    String fieldValue = splitString[i].substring( startString, splitString[i].length() );
                    printToFile.println( "field00" + i + ": " + fieldValue );
                }

                if ( splitString[i].startsWith( "VISIT DATES: " ) )
                {
                    System.out.println( "dateString:" + splitString[i] );
                    String[] dateString = splitString[i].split( " - " );
                    String fromDateString = dateString[0].substring( 13, dateString[0].length() );
                    String toDateString = dateString[1].substring( 0, dateString[1].length() );

                    try
                    {
                        String pattern = "MM/dd/yyyy";
                        SimpleDateFormat dateFormat = new SimpleDateFormat( pattern );

                        Date rawFromDate = new SimpleDateFormat( "dd MMM yyyy" ).parse( fromDateString );
                        String formattedFromDate = dateFormat.format( rawFromDate );

                        Date rawToDate = new SimpleDateFormat( "dd MMM yyyy" ).parse( toDateString );
                        String formattedToDate = dateFormat.format( rawToDate );

                        printToFile.println( "field004: " + formattedFromDate );
                        printToFile.println( "field005: " + formattedToDate );
                    } catch ( ParseException e )
                    {
                        e.printStackTrace();
                    }
                }

                if ( splitString[i].startsWith( "KNOWLEDGEABLE PERSON PHONE: " ) )
                {
                    int startPOC = splitString[i].indexOf( "VISIT POINT OF CONTACT: " ) + 24;
                    int endPOC = splitString[i].indexOf( "VISIT POINT OF CONTACT PHONE: " );

                    String poc = splitString[i].substring( startPOC, endPOC );
                    printToFile.println( "field006: " + poc );

                    int startPOCPhone = splitString[i].indexOf( "VISIT POINT OF CONTACT PHONE: " ) + 30;

                    String pocPhone = splitString[i].substring( startPOCPhone );
                    printToFile.println( "field007: " + pocPhone );

                }

                if ( splitString[i].startsWith( "CITY" ) )
                {
                    int startCity = 5;
                    int endCity = splitString[i].indexOf( "STATE: " ) - 1;

                    String city = splitString[i].substring( startCity, endCity );
                    
                    int startState = city.length();
                    int endState = splitString[]
                }
            }
        } catch ( FileNotFoundException e )
        {
            System.out.println( e );
        }
    }
}

//"field000: "   10,     "List_Date"
//"field001: "   30,     "Visit_Number"
//"field002: "   5,      "Amended"
//"field003: "   5,      "Cancelled"
//"field004: "   12,     "Visit_Arrival_Date"
//"field005: "   12,     "Visit_Departure_Date"
//"field006: "   50,     "Visit_Point_of_Contact"
//"field007: "   120,    "Visit_Point_of_Contact_Phone"
//"field008: "   20,     "Visit_Type"
//"field009: "   100,    "Visit_Location"
//"field010: "   50,     "City"
//"field011: "   2,      "State"
//"field012: "   10,     "ZIP_Code"
//"field013: "   4000,   "Purpose_of_Visit"
//"field014: "   10,     "Total_Visitors"
//"field015: "   20,     "Visitor_Number"
//"field016: "   50,     "Visitor_Name"
//"field017: "   50,     "Company"
//"field018: "   50,     "Position_Rank"
//"field019: "   10,     "DOB"
//"field020: "   50,     "POB"
//"field021: "   50,     "Nationality"
//"field022: "   20,     "Passport_ID"
