/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.poe.part2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RC_Student_Lab
 */
public class PoePART2Test {
    
    public PoePART2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class PoePART2.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String administrator = "";
        String password = "";
        boolean expResult = true;
        boolean result = PoePART2.login(administrator, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of sendMessage method, of class PoePART2.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        PoePART2.sendMessage();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of saveMessagesToJSON method, of class PoePART2.
     */
    @Test
    public void testSaveMessagesToJSON() {
        System.out.println("saveMessagesToJSON");
        PoePART2.saveMessagesToJSON();
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of showRecentlySentMessages method, of class PoePART2.
     */
    @Test
    public void testShowRecentlySentMessages() {
        System.out.println("showRecentlySentMessages");
        PoePART2.showRecentlySentMessages();
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of main method, of class PoePART2.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        PoePART2.main(args);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
