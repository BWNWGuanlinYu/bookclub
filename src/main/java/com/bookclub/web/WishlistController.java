package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistDao wishlistDao;

    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * It lists all the wishlist items
     *
     * @param model to be used in the view
     * @return The view name of wishlist list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist(Model model) {
//        List<WishlistItem> wishlist = wishlistDao.list();
//        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    }

    /**
     * The view for the wishlist form
     *
     * @param model to be used in the view
     * @return The view name of wishlist form
     */
    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    /**
     * The method validates the input and, if valid, redirects to the wishlist page
     *
     * @param wishlistItem The wishlist item to be added
     * @param bindingResult The result of the validation
     * @return The view name to redirect to
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult) {
        System.out.println(wishlistItem.toString());
        System.out.println(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistDao.add(wishlistItem);
        return "redirect:/wishlist";
    }
}