package dev.orme.ludotheque.helpers;

public class PaginationHelper {
    public static String getNextPageUrl(String baseRequestUrl, int totalPages, int page, int pageSize) {
        page++;
        if((page * pageSize) >= totalPages){
            return "";
        }
        return String.format("%s/?page=%d&pageSize=%d", baseRequestUrl, page + 1, pageSize);
    }

    public static String getPreviousPageUrl(String baseRequestUrl, int totalCount, int page, int pageSize) {
        page++;
        if(page == 1){
            return "";
        }
        return String.format("%s/?page=%d&pageSize=%d", baseRequestUrl, page - 1, pageSize);
    }
}
