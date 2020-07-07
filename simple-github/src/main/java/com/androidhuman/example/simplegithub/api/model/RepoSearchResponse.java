package com.androidhuman.example.simplegithub.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoSearchResponse {

    @SerializedName("total_count")
    public final int totalCount;

    // GithubRepo 형태의 리스트를 표현합니다.
    public final List<GithubRepo> items;

    public RepoSearchResponse(int totalCount, List<GithubRepo> items) {
        this.totalCount = totalCount;
        this.items = items;
    }
}
