package com.example.utsrefaldi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubRes {
    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<Items> items;

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<Items> getItems() {
        return items;
    }
}
