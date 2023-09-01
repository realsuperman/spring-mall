package com.example.shopping.util;

public enum PageSize {
    SIZE(16L);

    private final long pageSize;

    PageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long size() {
        return pageSize;
    }
}