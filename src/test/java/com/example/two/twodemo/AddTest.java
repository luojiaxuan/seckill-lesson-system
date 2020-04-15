package com.example.two.twodemo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class AddTest {

    static Scanner scn  =new Scanner(System.in);
    @Test
    public void testAdd(){
        /*
        float a=scn.nextFloat();
        float b = scn.nextFloat();
        */
        float a = (float) 0.2;
        float b = (float) 0.3;
        int aa,bb;
        if(a-(int)a<1e-5&&b-(int)b<1e-5){//整数
            aa=(int) a;
            bb=(int) b;
            Assert.assertEquals(aa+bb,add(aa,bb));
        }
        else if(!(a-(int)a<1e-5&&b-(int)b<1e-5)){//均为float
            Assert.assertEquals(a+b,add(a,b),1e-5);
        }
        else{
            Assert.assertEquals((float)a+b,add(a,b),1e-5);
        }
    }

    long add(long  a,long b){
        return a+b;
    }
    float add(float a,long b){
        return a+b;
    }
    float add(long a, float b){
        return a+b;
    }
    float add(float a, float b){
        return a+b;
    }
}
