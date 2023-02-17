package com.hibernate.hibernatePlayground.common;

import java.util.List;

public interface AbstractMapper<E,D> {
    E toEntity(D d);

    D toDTO(E e);

    List<E> toEntity(List<D> d);

    List <D> toDTO(List<E> e);
}
