package com.farafarin.mata.common.model;

import javax.validation.constraints.Size;

/**
 * Created by K550 VX on 1/19/2018.
 */
public class SearchQuery {

    @Size(max = 50)
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQueryLike() {
        if (query == null) {
            return "%%";
        }
        return "%" + query + "%";
    }

}
