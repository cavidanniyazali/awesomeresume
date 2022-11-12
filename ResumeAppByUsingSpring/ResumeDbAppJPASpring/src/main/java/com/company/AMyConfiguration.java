//package com.company;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//import java.util.UUID;
//
//@Configuration
//@Scope("prototype")
//@ComponentScan(basePackages = "outer")
//public class AMyConfiguration {
////    @Bean("obj")
////    Object getObject() {
////        System.out.println("salam obyekti yaratdim");
////        return new Object();
////    }
//
//    private String uid= UUID.randomUUID().toString();
//
//    public AMyConfiguration(){
//        System.out.println("AMyConfiguration");
//    }
//    @Bean("alma")
//    @Scope("prototype")
//    public Object getObject(){
//        System.out.println("uid="+uid);;
//        System.out.println("object created in AMyConfiguration");
//        return new Object();
//    }
//}
