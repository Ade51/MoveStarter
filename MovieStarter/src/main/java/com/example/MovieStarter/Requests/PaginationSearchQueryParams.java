package com.example.MovieStarter.Requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationSearchQueryParams extends PaginationQueryParams {
    private String search;
}
