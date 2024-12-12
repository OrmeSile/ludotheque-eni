package dev.orme.ludotheque.helpers;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class PaginationHelper {
    private static final String currentUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .build()
            .toUriString();
    public static String getNextPageUrl(int totalPages, int page, int pageSize) {
        if((page * pageSize) >= totalPages){
            return "";
        }
        return String.format("%s/game?page=%d&size=%d", PaginationHelper.currentUri, page + 1, pageSize);
    }

    public static String getPreviousPageUrl(int totalCount, int page, int pageSize) {
        if(page == 1){
            return PaginationHelper.currentUri + "/game/";
        }
        return String.format("%s/game?page=%d&size=%d", PaginationHelper.currentUri, page - 1, pageSize);
    }
}
