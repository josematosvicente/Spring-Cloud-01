package com.bootcamp.springwebflux.msvcproduct.mapper;

import com.bootcamp.springwebflux.msvcproduct.models.documents.Product;
import com.msvc.specification.api.dto.NewProductDto;
import com.msvc.specification.api.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product value);

    Product toModel(NewProductDto value);
}
