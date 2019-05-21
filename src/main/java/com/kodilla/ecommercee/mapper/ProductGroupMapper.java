package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.ProductGroup;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductGroupMapper {
    public List<GroupDto> mapToGroupDtoList(List<ProductGroup> groups) {
        return null;
    }

    public ProductGroup mapToProductGroup(GroupDto groupDto) {
        return null;
    }

    public GroupDto mapToGroupDto(ProductGroup group) {
        return null;
    }
}
