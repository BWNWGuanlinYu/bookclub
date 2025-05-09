package com.bookclub;

import com.bookclub.model.WishlistItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

public class WishlistItemTest {

    @Test
    @DisplayName("WishlistItem Constructor Test")
    public void testWishlistItemConstructor() {
        String testIsbn = "1234567890";
        String testTitle = "Test Book";

        WishlistItem item = new WishlistItem(testIsbn, testTitle);
        assertEquals(testIsbn, item.getIsbn(), "ISBN not match");
        assertEquals(testTitle, item.getTitle(), "Title not match");
        assertNull(item.getId(), "ID should be NULL");
        assertNull(item.getUsername(), "Username should be NULL");
    }

    @Test
    @DisplayName("WishlistItem Validation Test")
    public void testWishlistItemValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Valid item
        WishlistItem validItem = new WishlistItem("1234567890", "Test Book");
        var violations = validator.validate(validItem);
        assertTrue(violations.isEmpty(), "Valid item has validation errors");

        // Missing ISBN
        WishlistItem missingIsbn = new WishlistItem("", "Test Book");
        violations = validator.validate(missingIsbn);
        assertEquals(1, violations.size(), "No validation error for missing ISBN");

        // Missing title
        WishlistItem missingTitle = new WishlistItem("1234567890", "");
        violations = validator.validate(missingTitle);
        assertEquals(1, violations.size(), "No validation error for missing title");

        // Missing both ISBN and title
        WishlistItem missingBoth = new WishlistItem("", "");
        violations = validator.validate(missingBoth);
        assertEquals(2, violations.size(), "No validation error or less than 2 for missing both ISBN and title");
    }
}
