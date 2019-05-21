package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupDto {

    private Long id;
    private String name;
    private List<ProductDto> productDtoList;

    public GroupDto(String name){
        this.name = name;
    }
}
