package com.moe.apigw.security;

public interface ApiKeyChecker {
    boolean isAuthorized(String key, String application);
}
