package rs.raf.responses;

import java.util.ArrayList;
import java.util.List;

public class PaginationResponse<T> {
    private List<T> data;
    private int totalSize;
    private int currentPage;
    private int pages;
    private int sizePerPage;

    public PaginationResponse(List<T> data, int totalSize, int currentPage, int pages, int sizePerPage) {
        this.data = data;
        this.totalSize = totalSize;
        this.currentPage = currentPage;
        this.pages = pages;
        this.sizePerPage = sizePerPage;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public void setSizePerPage(int sizePerPage) {
        this.sizePerPage = sizePerPage;
    }

    public int getSizePerPage() {
        return sizePerPage;
    }
}
