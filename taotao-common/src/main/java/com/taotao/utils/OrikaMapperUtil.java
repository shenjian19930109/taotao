package com.taotao.utils;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Created by apple on 18/3/4.
 */
public class OrikaMapperUtil {

    public static final MapperFactory newDefaultInstance() {
        return new DefaultMapperFactory.Builder().build();
    }
}
