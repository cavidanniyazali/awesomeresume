package com.company;

import com.company.dao.impl.UserDaoImpl2;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Mock
    UserDaoImpl2 userDao;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeTestClass
    public static void setUp(){
        System.out.println("set up called");

    }

    @BeforeTestMethod
    public void before(){
        System.out.println("before called");

        MockitoAnnotations.initMocks(this);

        List<User> list = new ArrayList<>();

        User u = new User();
        u.setName("test");
        u.setSurname("test");
        u.setEmail("test@test.com");
        u.setNationality(new Country(1));

        list.add(u);

        Mockito.when(userDao.getAll(null,null,null)).thenReturn(list);
        Mockito.when(userDao.getAll("test","test",1)).thenReturn(list);
    }

    @Test(expected = NullPointerException.class)
    @Ignore
    public void showReturnAllUsers_whenNoParameterPassed(){
        List<User> list = userService.getAll(null,null,null);

        Assert.assertEquals("user size must be 1",1, list.size());

        Mockito.verify(userDao, Mockito.atLeastOnce())
                .getAll(null,null,null);
    }

    @Test
    public void testGivenAllParamsThenGetAllByFilter(){
        List<User> list = userService.getAll("test","test",1);

        Assert.isTrue((list.size()>0),"user size must be greater than 0");

        User user = list.get(0);

        Assert.doesNotContain("name wrong","test", user.getName());
        Assert.doesNotContain("surname wrong","test", user.getSurname());
//        Assert.assertEquals("nat id wrong",1L, (long) user.getNationality().getId());


        Mockito.verify(userDao, Mockito.atLeastOnce())
                .getAll("test","test",1);
    }

    @Test
    public void testGivenNullFindByEmailAndPassword(){
        User user = userService.findByEmailAndPassword(null,null);
        Assert.notNull(user,"user must be null");

        Mockito.verify(userDao, Mockito.atLeastOnce())
                .findByEmailAndPassword(null,null);
    }

}