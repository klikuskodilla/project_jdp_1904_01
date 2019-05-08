package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/group")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET,value ="getGroups")
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST,value ="createGroup")
    public GroupDto createGroup(GroupDto groupDto){
        return new GroupDto(1L,"Ubrania");
    }

    @RequestMapping(method = RequestMethod.GET,value ="getGroup")
    public GroupDto getGroup(Long id) {
        return new GroupDto(1L,"Ubrania");
    }

    @RequestMapping(method = RequestMethod.POST,value ="updateGroup")
    public GroupDto updateGroup(GroupDto groupDto) {
        return new GroupDto(1L,"Ubrania zimowe");
    }

}


