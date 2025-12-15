package app.training.dia.service;

import org.springframework.stereotype.Service;

@Service
public class FirstService {

    public boolean validateUsername(String username){
        if(username.equalsIgnoreCase("stephen")){
            return true;
        }else{
            return false;
        }
    }
}
