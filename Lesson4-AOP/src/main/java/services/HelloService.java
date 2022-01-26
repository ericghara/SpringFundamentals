package services;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

//    public void hello(String name) {
//        System.out.printf("Hello, %s!%n", name );
////        throw new RuntimeException("boo"); // After throwing
//    }

    public String hello(String name) {
        var msg = String.format("Hello, %s!", name);
        System.out.println(msg);
        return msg;
    }
}
