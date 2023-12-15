package com.olivyou2.hufsattend.lib.Request;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Getter
@Setter
public class HufsAccount {
    private String id;
    private String password;

    public static HufsAccount fromContext() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String authorization = request.getHeader("Authorization");

        String[] tokens = authorization.split(":");
        String id = tokens[0];
        String password = tokens[1];

        HufsAccount accountInformation = new HufsAccount();
        accountInformation.setId(id);
        accountInformation.setPassword(password);

        return accountInformation;
    }
}
