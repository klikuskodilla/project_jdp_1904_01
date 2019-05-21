package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.ProductGroupDao;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.mapper.ProductGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductGroupService {
    @Autowired
    private ProductGroupMapper groupMapper;
    @Autowired
    private ProductGroupDao groupDao;

    public List<GroupDto> getAllGroups() {
        return groupMapper.mapToGroupDtoList(groupDao.findAll());
    }

    public GroupDto getCart(Long id) {
        return groupMapper.mapToGroupDto(groupDao.findById(id).orElse(new ProductGroup()));
    }

    public ProductGroup save(GroupDto groupDto) {
        return groupDao.save(groupMapper.mapToProductGroup(groupDto));
    }
}
