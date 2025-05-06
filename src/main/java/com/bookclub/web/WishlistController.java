package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        System.out.println(wishlistItem.toString());
        System.out.println(bindingResult.getAllErrors());

        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistItem.setUsername(authentication.getName());
        wishlistDao.add(wishlistItem);
        return "redirect:/wishlist";
    }

    /**
     * Show a single wishlist item for viewing/editing
     *
     * @param id The id of the wishlist item
     * @param model The model to be used in the view
     * @return The view name
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showWishlistItem(@PathVariable("id") String id, Model model) {
        WishlistItem wishlistItem = wishlistDao.find(id);
        model.addAttribute("wishlistItem", wishlistItem);
        return "wishlist/view";
    }

    /**
     * Update a wishlist item
     *
     * @param wishlistItem The wishlist item to be updated
     * @param bindingResult The result of the validation
     * @param authentication The authentication object
     * @return The view name to redirect to
     */
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String updateWishlistItem(@RequestParam("id") String id, @RequestParam("isbn") String isbn, @RequestParam("title") String title, Authentication authentication) {

        System.out.println("Update request received with ID: " + id);

        if (id == null || id.isEmpty()) {
            System.out.println("Error: ID is null or empty!");
            return "redirect:/wishlist";
        }

        WishlistItem wishlistItem = wishlistDao.find(id);

        if (wishlistItem == null) {
            System.out.println("Error: Item not found with ID: " + id);
            return "redirect:/wishlist";
        }

        // Update the fields
        wishlistItem.setIsbn(isbn);
        wishlistItem.setTitle(title);
        wishlistItem.setUsername(authentication.getName());

        System.out.println("Updating wishlist item: " + wishlistItem);

        wishlistDao.update(wishlistItem);
        return "redirect:/wishlist";
    }

    /**
     * Remove a wishlist item
     *
     * @param id The id of the wishlist item to be removed
     * @return The view name to redirect to
     */
    @RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
    public String removeWishlistItem(@PathVariable("id") String id) {
        wishlistDao.remove(id);
        return "redirect:/wishlist";
    }
}