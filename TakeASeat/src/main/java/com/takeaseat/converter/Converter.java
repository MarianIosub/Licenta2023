package com.takeaseat.converter;

import org.modelmapper.ModelMapper;


public class Converter<S, T> {

	private final ModelMapper mapper;

	public Converter(final ModelMapper mapper) {
		this.mapper = mapper;
	}

	public T convert(final S source, Class<T> targetClass) {
		return getMapper().map(source, targetClass);
	}

	protected ModelMapper getMapper() {
		return mapper;
	}
}
