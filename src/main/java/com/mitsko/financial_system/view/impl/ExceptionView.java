package com.mitsko.financial_system.view.impl;

import com.mitsko.financial_system.view.View;

public class ExceptionView implements View<RuntimeException> {

    @Override
    public void printResult(RuntimeException e) {
        System.out.println(e.getMessage());
    }
}
