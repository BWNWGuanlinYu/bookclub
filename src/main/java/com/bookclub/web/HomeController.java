package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;
import com.bookclub.service.impl.RestBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private BookOfTheMonthDao bookOfTheMonthDao;

    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {

        List<BookOfTheMonth> monthlyBooks = bookOfTheMonthDao.list(String.valueOf(LocalDate.now().getMonthValue()));
        StringBuilder isbnBuilder = new StringBuilder();

        for (BookOfTheMonth bookOfTheMonth : monthlyBooks) {
            isbnBuilder.append("ISBN:").append(bookOfTheMonth.getIsbn()).append(",");
        }

        // Default books if no book added in current month
        if (isbnBuilder.length() == 0) {
            isbnBuilder.append("ISBN:9781546171461,ISBN:9780593100912,ISBN:9781250759368,ISBN:9781612680194,ISBN:9780593975091");
        } else {
            isbnBuilder.setLength(isbnBuilder.length() - 1);
        }

        RestBookDao bookDao = new RestBookDao();
        List<Book> books = bookDao.list(isbnBuilder.toString());

        for (Book book : books) {
            System.out.println(book.getTitle());
        }

        model.addAttribute("books", books);

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showHome(@PathVariable("id") String id, Model model) {
        // Ignore favicon.ico requests (notice return in the terminal)
        if (id.equals("favicon.ico")) {
            return "redirect:/";
        }

        String isbn = id;
        System.out.println(isbn);

        RestBookDao bookDao = new RestBookDao();
        Book book = bookDao.find(isbn);

        System.out.println(book.toString());
        model.addAttribute("book", book);

        return "monthly-books/view";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Model model) {
        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model) {
        return "contact";
    }
}
