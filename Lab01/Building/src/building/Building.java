/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package building;

import java.util.Scanner;

/**
 *
 * @author florence
 */
public class Building {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
String [] myarray=new String[]{"Kiambere","Pate Cafe","Kilimanjaro","Menengai","Suswa"};
int [] rooms=new int[]{12,15,71,51,11};
String [] colour=new String[]{"blue","red","brown","black","grey"};
for(int i=0; i<5; i++)
{
System.out.println(myarray[i]+" is a "+ colour[i] +" building and has "+rooms[i]+" rooms");
}

    }
    
}
