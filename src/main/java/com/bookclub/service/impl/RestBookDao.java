package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class RestBookDao implements BookDao {

    // Constructor
    public RestBookDao() {

    }

    // Method to get book details from Open Library API
    private Object getBooksDoc(String isbnString) {
        String openLibraryUrl = "https://openlibrary.org/api/books";

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "details");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = rest.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        // Check if the response is successful (Debug mainly)
        String jsonBooklist = response.getBody();
        System.out.println("API Response: " + jsonBooklist);

        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBooklist);
    }

    @Override
    public List<Book> list(String isbnString) {
        List<Book> books = new ArrayList<>();

        Object document = getBooksDoc(isbnString);

        // Parse the ISBN
        List<String> isbns = new ArrayList<>();
        for (String isbn : isbnString.split(",")) {
            isbns.add(isbn);
        }

        // loop each ISBN and get the details
        for (String isbn : isbns) {
            try {
                String title = JsonPath.read(document, "$." + isbn + ".details.title");
                String infoUrl = JsonPath.read(document, "$." + isbn + ".info_url");
                String cleanIsbn = isbn.replace("ISBN:", "");
                // Create simple Book objects for the list view
                books.add(new Book(cleanIsbn, title, "Click view for details", infoUrl, 0));
            } catch (Exception e) {
                System.out.println("Error processing " + isbn + ": " + e.getMessage());
            }
        }

        return books;
    }

    @Override
    public Book find(String key) {
        String isbnString = "ISBN:" + key;

        Object document = getBooksDoc(isbnString);

        try {
            String isbn = isbnString;
            String title = "N/A";
            String description = "N/A";
            String infoUrl = "N/A";
            int numOfPages = 0;

            try {
                title = JsonPath.read(document, "$." + isbnString + ".details.title");
            } catch (Exception e) {
                System.out.println("Error reading title: " + e.getMessage());
            }

            try {
                description = JsonPath.read(document, "$." + isbnString + ".details.subtitle");
            } catch (Exception e) {
                System.out.println("Error reading subtitle, trying other fields: " + e.getMessage());
                try {
                    description = JsonPath.read(document, "$." + isbnString + ".details.description");
                } catch (Exception ex) {
                    System.out.println("No description found: " + ex.getMessage());
                }
            }

            try {
                infoUrl = JsonPath.read(document, "$." + isbnString + ".info_url");
            } catch (Exception e) {
                System.out.println("Error reading infoUrl: " + e.getMessage());
            }

            try {
                numOfPages = JsonPath.read(document, "$." + isbnString + ".details.number_of_pages");
            } catch (Exception e) {
                System.out.println("Error reading numOfPages: " + e.getMessage());
            }

            return new Book(key, title, description, infoUrl, numOfPages);
        } catch (Exception e) {
            System.out.println("Error processing " + isbnString + ": " + e.getMessage());
            return new Book();
        }
    }
}