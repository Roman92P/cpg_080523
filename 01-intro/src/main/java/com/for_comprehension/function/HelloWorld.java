package com.for_comprehension.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HelloWorld {

    public static void main(String[] args) {
        List<Integer> of = Collections.unmodifiableList(new ArrayList<>());

        of.clear();
    }

}
