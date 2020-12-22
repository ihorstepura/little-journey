package org.vector.littlejourney.dal.constant.matcher;

import org.springframework.data.domain.ExampleMatcher;

public class MatcherConst {

    private MatcherConst() {
    }

    public static final ExampleMatcher matcherIgnoreCase = ExampleMatcher.matchingAll().withIgnoreCase();

    public static final ExampleMatcher matcherEndsWith = ExampleMatcher.matchingAll()
            .withStringMatcher(ExampleMatcher.StringMatcher.ENDING);
}
