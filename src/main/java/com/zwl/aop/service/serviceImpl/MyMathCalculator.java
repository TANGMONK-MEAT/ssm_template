package com.zwl.aop.service.serviceImpl;


import com.zwl.aop.service.Calculator;
import org.springframework.stereotype.Service;

/**
 * 目标类
 */
@Service
public class MyMathCalculator implements Calculator {

	@Override
	public int add(int i, int j) {
		return i + j;
	}

	@Override
	public int sub(int i, int j) {
		return i - j;
	}

	@Override
	public int mul(int i, int j) {
		//方法的兼容性；
		return i * j;
	}

	@Override
	public int div(int i, int j) {
		return i / j;
	}

}
