package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductGroupMapper {
    public ProductGroup mapToProductGroup(GroupDto groupDto) {
        return new ProductGroup(
                groupDto.getName()
        );
    }

    public ProductGroup mapToProductGroupAllArgs(GroupDto groupDto) {
        return new ProductGroup(
                groupDto.getId(),
                groupDto.getName(),
                new ArrayList<>()
        );
    }

    public GroupDto mapToGroupDto(ProductGroup productGroup) {
        return new GroupDto(
                productGroup.getId(),
                productGroup.getDescription(),
                new ArrayList<>()
        );
    }

    public List<GroupDto> mapToProductGroupDtoList(List<ProductGroup> productGroupList) {
        return productGroupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}

