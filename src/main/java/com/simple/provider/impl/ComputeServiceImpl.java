package com.simple.provider.impl;

import com.simple.provider.service.ComputeService;

/**
 * ComputeServiceImpl 计算 实现类型
 */
public class ComputeServiceImpl implements ComputeService {

    // sum 加
    public int sum(int a, int b) {
        return a + b;
    }

    // multiply 乘
    public int multiply(int a, int b) {
        return a * b;
    }
}
