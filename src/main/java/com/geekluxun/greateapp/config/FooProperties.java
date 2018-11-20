package com.geekluxun.greateapp.config;

import com.geekluxun.greateapp.common.BaseDto;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by luxun on 2017/11/21.
 * 从yml文件中读取为foo的数据结构到此pojo中
 */
@Component
@ConfigurationProperties(prefix = "foo")
@Validated
public class FooProperties extends BaseDto {

    private boolean enabled;

    @NotNull(message = "IP地址不能为空")
    private InetAddress remoteAddress;

    @NotBlank(message = "mac地址不能为空")
    private String macAddress;

    @Valid
    private final Security security = new Security();

    public boolean getEnabled() {
        return enabled;
    }

    public InetAddress getRemoteAddress() {
        return remoteAddress;
    }


    public String getMacAddress() {
        return macAddress;
    }

    public Security getSecurity() {
        return security;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRemoteAddress(InetAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public static class Security extends BaseDto {

        @NotBlank
        private String username;

        private String password;

        private List<String> roles;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public List<String> getRoles() {
            return roles;
        }


        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }
}