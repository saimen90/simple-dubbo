package com.simple.provider.impl;

import com.simple.service.ComputeService;

/**
 * ComputeServiceImpl 计算 实现类型
 */
public class ComputeServiceImpl implements ComputeService {

    // sum 加
    public int sum(Integer a, Integer b) {
        return a + b;
    }

    // multiply 乘
    public int multiply(Integer a, Integer b) {
        return a * b;
    }
}
