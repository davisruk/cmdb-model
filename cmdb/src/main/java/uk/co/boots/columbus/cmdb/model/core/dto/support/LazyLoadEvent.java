package uk.co.boots.columbus.cmdb.model.core.dto.support;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class LazyLoadEvent {
    /**
     * First row offset.
     */
    public int first;

    /**
     * Number of rows per page.
     */
    public int rows;

    public String sortField;
    public int sortOrder;
    public Map<String,FilterMetadata> filters;

    public Pageable toPageable() {
        if (sortField != null) {
            return new PageRequest(toPageIndex(), rows, toSortDirection(), sortField);
        } else {
            return new PageRequest(toPageIndex(), rows);
        }
    }

    /**
     * Zero based page index.
     */
    public int toPageIndex() {
        return (first + rows) / rows - 1;
    }

    public Sort.Direction toSortDirection() {
        return sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
}
