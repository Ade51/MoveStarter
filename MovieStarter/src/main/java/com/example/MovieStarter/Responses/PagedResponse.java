package com.example.MovieStarter.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {
    private Integer page;
    private Integer pageSize;
    private Integer totalCount;
    private List<T> data;
}
