package com.takeaseat.converter;

import org.modelmapper.ModelMapper;


public class Converter<S, T> {

    ModelMapper mapper;

    public Converter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public T convert(S source, Class<T> targetClass) {
        return mapper.map(source, targetClass);
    }
}
