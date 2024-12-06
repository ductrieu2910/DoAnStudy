package com.example.doanstudy.service.impl;

import org.aspectj.apache.bcel.classfile.SourceFile;

import java.sql.SQLOutput;

public class demo {



    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
        int pageSize = 5 ;
        int page =0 ;
        for(int i = 0; i < nums.length; i++ ){
            page = i%pageSize == 0?page+1:page;
            if(i == (page-1)*pageSize){
                System.out.println("<div>");
            }
            System.out.println(nums[i]);
            if((i+1)%5 == 0 || i == nums.length-1){
                System.out.println("</div>");
            }
        }

    }
}
