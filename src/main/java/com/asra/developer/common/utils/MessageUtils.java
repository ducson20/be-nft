package com.asra.developer.common.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MessageUtils implements InitializingBean {

    private static MessageUtils instance = null;

    @Autowired
    MessageSource messageSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        if(Objects.isNull(instance)){
            instance = this;
        }
    }

    public static String getMessageFromCode(String code, Object... args){
        List<String> argsStr = new ArrayList<>();

        Arrays.stream(args).forEach(arg -> {
            System.out.println("ARGS: " + Objects.isNull(arg));
            argsStr.add(Objects.isNull(arg) ? null : arg.toString());
        });
        System.out.println("2: "  + argsStr.toArray() + " " + " " +  Locale.getDefault() + " " + code);

        System.out.println("Message: " +  instance.messageSource.getMessage(code, argsStr.toArray(), Locale.getDefault()));
        return instance.messageSource.getMessage(code, argsStr.toArray(), Locale.getDefault());

    }
}
