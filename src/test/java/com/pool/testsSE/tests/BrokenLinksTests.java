package com.pool.testsSE.tests;

import com.pool.pagesSE.HomePage;
import org.testng.annotations.Test;

public class BrokenLinksTests extends TestBaseSE{

    @Test
    public void checkBrokenLinksTest(){
        new HomePage(driver).checkBrokenLinks();


    }
}



