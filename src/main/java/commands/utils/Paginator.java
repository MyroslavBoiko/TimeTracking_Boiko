package commands.utils;

import java.util.List;

public class Paginator<T> {
    private List<T> items;

    private int pagesCount;
    private int currentPage;
    private int itemsPerPage;

    public Paginator(List<T> items, int itemsPerPage) {
        this.items = items;
        this.itemsPerPage = itemsPerPage;
        pagesCount = calculatePagesCount();
        currentPage = 1;
    }

    private int calculatePagesCount() {
        int itemsCount = items.size();
        int pagesCount = itemsCount / itemsPerPage;
        if(pagesCount == 0){
             return pagesCount = 1;
        }
        return isNecessaryIncompletePage(itemsCount, pagesCount) ? pagesCount + 1 : pagesCount;
    }

    private boolean isNecessaryIncompletePage(int itemsCount, int pagesCount) {
        return itemsCount % pagesCount == 0;
    }

    public List<T> getItemsForCurrentPage() {
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = startIndex + itemsPerPage;
        if (endIndex > items.size()) {
            endIndex = items.size();
        }
        return items.subList(startIndex, endIndex);
    }


    public int getPagesCount() {
        return pagesCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
