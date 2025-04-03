package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

import java.util.ArrayList;
import java.util.List;

public class MemWishlistDao implements WishlistDao {
    private List<WishlistItem> wishlist;

    // Constructor
    public MemWishlistDao() {
        wishlist = new ArrayList<>();

        this.wishlist.add(new WishlistItem("9780345339683", "The Hobbit or There and Back Again"));
        this.wishlist.add(new WishlistItem("9780261103573", "The Fellowship of the Ring"));
        this.wishlist.add(new WishlistItem("9780261102361", "The Two Towers"));
        this.wishlist.add(new WishlistItem("9780261102378", "The Return of the King"));
    }

    /*
        * List() method
        * @param item
     */
    @Override
    public List<WishlistItem> list() {
        return this.wishlist;
    }

    /*
        * find() method
        * @param item
     */
    @Override
    public WishlistItem find(String key) {
        for (WishlistItem item : this.wishlist) {
            if (item.getIsbn().equals(key)) {
                return item;
            }
        }
        return new WishlistItem();
    }
}
