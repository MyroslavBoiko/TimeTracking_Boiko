package commands.utils;

/**
 * Class for pagination of records.
 *
 * @author Mirosha
 */

public class Paginator {

    private int pagesCount;
    private int currentPage;
    private int itemsPerPage;
    private int numberOfRows;

    public Paginator(int numberOfRows, int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        this.numberOfRows = numberOfRows;
        pagesCount = calculatePagesCount();
        currentPage = 1;
    }

    private int calculatePagesCount() {
        int pagesCount = numberOfRows / itemsPerPage;
        if (pagesCount % itemsPerPage > 0) {
            pagesCount++;
        }
        return pagesCount;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }
}
