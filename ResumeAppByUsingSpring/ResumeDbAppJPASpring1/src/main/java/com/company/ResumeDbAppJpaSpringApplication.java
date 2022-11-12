package com.company;

import com.company.dao.inter.UserRepository;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class ResumeDbAppJpaSpringApplication {
//    public ResumeDbAppJpaSpringApplication(){
//        System.out.println("ResumeDbAppJpaSpringApplication");
//    }

//    @Autowired
//    @Qualifier("obj")
//    private Object object;

//    @Autowired
//    private MyComponent component;

//    @Autowired
//    @Qualifier("alma")
//    private Object obj;
//
//    @Autowired
//    @Qualifier("alma")
//    private Object obj2;
//
//    @Autowired
//    @Qualifier("alma")
//    private Object obj3;
//
//    @Autowired
//    MyComponent component;
//
//    @Autowired
//    private AMyConfiguration conf;
//
//    @Autowired
//    private AMyConfiguration conf2;

	public static void main(String[] args) {
		SpringApplication.run(ResumeDbAppJpaSpringApplication.class, args);
	}

//    @Bean(name = "userDao1")
//        UserDaoInter getUserDao(){
//        return new UserDaoImpl();
//    }

//    @Bean
//    public CommandLineRunner run(){
//        CommandLineRunner clr= new CommandLineRunner(){
//
//            @Override
//            public void run(String... args) throws Exception {
////                System.out.println("obj="+obj);
////                System.out.println("obj2="+obj2);
////                System.out.println("obj="+object);
//                List<User> list=userDao.getAll(null,null,null);
//                System.out.println(list);
//            }
//        };
//        return clr;
//    }

    @Autowired
//    @Qualifier("userDao1")
    private UserServiceInter userService;
//
//    @Bean
//    public CommandLineRunner run(){
//        CommandLineRunner clr= new CommandLineRunner(){
//
//            @Override
//            public void run(String... args) throws Exception {
//                List<User> list=userDao.getAll(null,null,null);
//
//                User u=list.get(0);
//                u.setName(u.getName().toLowerCase());
//                userDao.updateUser(u);
////                userDao.updateUser(null);
//            }
//        };
//        return clr;
//    }

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner run(){
        CommandLineRunner clr= new CommandLineRunner(){

            @Override
            public void run(String... args) throws Exception {
//                List<User> list=userRepository.findAll();
//                System.out.println(list);
//                System.out.println("------");
//                list=userRepository.findAll(Sort.by(Sort.Order.desc("id")));
//                System.out.println(list);

//                User u=userRepository.findById(1).get();
//                System.out.println(u);

//                User u=userRepository.findByName("javidan");
//                User u2=userRepository.findByNameAndSurname("javidan","Niyazali");
//                System.out.println(u);
//                System.out.println(u2);

//                User u=userRepository.findById(1);
//                System.out.println(u);

//                User u=userRepository.findByEmail("cavidan.niyazali@gmail.com");
//                System.out.println(u);

//                List<User> u=userRepository.getAll(null,null,null);
//                System.out.println(u);
                for (int i=0;i<10;i++){
                    userService.getAll(null,null,null);
                }
                userService.getById(1);
            }
        };
        return clr;
    }
}
