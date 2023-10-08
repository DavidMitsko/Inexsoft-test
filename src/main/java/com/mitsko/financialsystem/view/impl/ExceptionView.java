package com.mitsko.financialsystem.view.impl;

import com.mitsko.financialsystem.view.View;

public class ExceptionView implements View<RuntimeException> {

    @Override
    public void printResult(RuntimeException e) {
        System.out.println(e.getMessage());
    }
}
