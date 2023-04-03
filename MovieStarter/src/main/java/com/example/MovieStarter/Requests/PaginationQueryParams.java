package com.example.MovieStarter.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationQueryParams {
    /**
     * Page is the number of the page.
     */
    private Integer page = 1;
    /**
     * PageSize is the maximum number of entries on each page.
     */
    private Integer pageSize = 10;
}
