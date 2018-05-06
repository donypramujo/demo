package com.jaxi.service;

public interface ConfigService {
    String get(String key);

    String get(String key,String default1);
}
