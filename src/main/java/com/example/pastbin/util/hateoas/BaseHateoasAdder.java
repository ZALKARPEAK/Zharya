package com.example.pastbin.util.hateoas;

import com.example.pastbin.util.additional.Mappable;

public interface BaseHateoasAdder<T extends Mappable> {
    void addLinksToEntity(T entity);
}