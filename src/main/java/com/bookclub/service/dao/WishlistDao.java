package com.bookclub.service.dao;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.GenericCrudDao;

// This interface extends the GenericDao interface for WishlistItem objects.
public interface WishlistDao extends GenericCrudDao<WishlistItem, String> {

}
