package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.stereotype.Component;

@Component
public class ProductGroupMapper {
    public ProductGroup mapToProductGroup(GroupDto groupDto) {
        return new ProductGroup(
                groupDto.getId(),
                groupDto.getName()
        );
    }

    public GroupDto mapToGroupDto(ProductGroup productGroup) {
        return new GroupDto(
                productGroup.getId(),
                productGroup.getDescription()
        );
    }
}
