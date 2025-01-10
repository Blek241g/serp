package org.scalke.userservice.mappers;

import java.util.List;

public interface MyMapper<D, E> {
    D entityToDto(E entity);
    E dtoToEntity(D dto);
    List<D> map(List<E> entities);
}
