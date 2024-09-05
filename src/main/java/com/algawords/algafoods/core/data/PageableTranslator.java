package com.algawords.algafoods.core.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class PageableTranslator {

    public static Pageable translate (Pageable pageable, Map<String, String> fieldsMapping) {
        var orders = pageable.getSort().stream().filter(order -> fieldsMapping.containsKey(order.getProperty()))
                .map(oreder -> new Sort.Order(oreder.getDirection(),
                        fieldsMapping.get(oreder.getProperty())))
                .toList();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
    }
}
