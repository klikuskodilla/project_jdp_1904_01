package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.service.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    ProductGroupMapper productGroupMapper;

    @Autowired
    ProductGroupService productGroupService;

    @RequestMapping(method = RequestMethod.GET,value ="getGroups")
    public List<GroupDto> getGroups() {
        return productGroupMapper.mapToProductGroupDtoList(productGroupService.getAllGroups());
    }

    @RequestMapping(method = RequestMethod.POST,value ="createGroup")
    public ProductGroup createGroup(GroupDto groupDto){
        return productGroupService.saveProductGroup(productGroupMapper.mapToProductGroup(groupDto));
    }

    @RequestMapping(method = RequestMethod.GET,value ="getGroup")
    public GroupDto getGroup(Long id) {
        return productGroupMapper.mapToGroupDto(productGroupService.getGroup(id).orElse(new ProductGroup()));
    }

    @RequestMapping(method = RequestMethod.POST,value ="updateGroup")
    public GroupDto updateGroup(GroupDto groupDto) {
        return productGroupMapper.mapToGroupDto(productGroupService.saveProductGroup(productGroupMapper.mapToProductGroup(groupDto)));
    }
}


