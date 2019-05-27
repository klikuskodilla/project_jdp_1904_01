package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.service.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    ProductGroupMapper productGroupMapper;

    @Autowired
    ProductGroupService productGroupService;

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups() {
        return productGroupMapper.mapToProductGroupDtoList(productGroupService.getAllGroups());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup")
    public ProductGroup createGroup(@RequestBody GroupDto groupDto) {
        return productGroupService.saveProductGroup(productGroupMapper.mapToProductGroup(groupDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long id) throws GroupNotFoundException {
        return productGroupMapper.mapToGroupDto(productGroupService.getGroup(id));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return productGroupMapper.mapToGroupDto(productGroupService.saveProductGroup(productGroupMapper.mapToProductGroupAllArgs(groupDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteGroup")
    public void deleteGroup(@RequestParam Long id) {
        productGroupService.deleteProductGroup(id);
    }
}