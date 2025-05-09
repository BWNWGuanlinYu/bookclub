package com.bookclub;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.web.WishlistController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishlistController.class)
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WishlistDao wishlistDao;

    @Test
    @WithMockUser
    // Wishlist display test
    public void showWishlistTest() throws Exception {
        mockMvc.perform(get("/wishlist"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist/list"));
    }

    @Test
    @WithMockUser
    // New wishlist item form test
    public void newWishlistItemFormTest() throws Exception {
        mockMvc.perform(get("/wishlist/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist/new"))
                .andExpect(model().attributeExists("wishlistItem"));
    }

    @Test
    @WithMockUser(username = "testuser")
    // Remove and test redirect
    public void removeWishlistItemTest() throws Exception {
        String itemId = "abc123";
        mockMvc.perform(get("/wishlist/remove/" + itemId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/wishlist"));
        verify(wishlistDao, times(1)).remove(itemId);
    }

    @Test
    @WithMockUser(username = "testuser")
    // View wishlist item test
    public void viewWishlistItemTest() throws Exception {
        String itemId = "abc123";
        WishlistItem testItem = new WishlistItem("1234567890", "Test Book");
        testItem.setUsername("testuser");

        when(wishlistDao.find(itemId)).thenReturn(testItem);
        mockMvc.perform(get("/wishlist/" + itemId))
                .andExpect(status().isOk())
                .andExpect(view().name("wishlist/view"))
                .andExpect(model().attributeExists("wishlistItem"))
                .andExpect(model().attribute("wishlistItem", testItem));
    }
}