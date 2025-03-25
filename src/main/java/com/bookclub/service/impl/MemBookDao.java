package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

public class MemBookDao implements BookDao {
    private List<Book> books;

    public MemBookDao() {
        books = new ArrayList<>();
        /*
            Ferreira, K. (n.d.). Booklist online: Leading book discovery.
            https://www.booklistonline.com/products/9810294
         */
        this.books.add(new Book("9781546171461",
                "Sunrise on the Reaping.",
                "As the day dawns on the fiftieth annual Hunger Games, fear grips the districts of Panem. This year, in honor of the Quarter Quell, twice as many tributes will be taken from their homes. Back in District 12, Haymitch Abernathy is trying not to think too hard about his chances. All he cares about is making it through the day and being with the girl he loves. When Haymitch's name is called, he can feel all his dreams break. He's torn from his family and his love, shuttled to the Capitol with the three other District 12 tributes: a young friend who's nearly a sister to him, a compulsive oddsmaker, and the most stuck-up girl in town. As the Games begin, Haymitch understands he's been set up to fail. But there's something in him that wants to fight . . . and have that fight reverberate far beyond the deadly arena",
                382,
                List.of("Suzanne Collins")));
        /*
            Herald, D. T. (n.d.). Booklist online: Leading book discovery.
            https://www.booklistonline.com/products/9804348
         */
        this.books.add(new Book("9780593100912",
                "Flirting lessons",
                "Avery Jensen is almost thirty, fresh off a breakup, and she's tired of always being so uptight and well-behaved. She wants to get a hobby, date around (especially other women), flirt with everyone she sees, wear something not from the business casual section of her closet-all the fun stuff normal people do in their twenties. One problem: Avery doesn't know where to start. She doesn't have a lot of dating experience, with men or women, and despite being self-assured at work, she doesn't have a lot of confidence when it comes to romance. Enter Taylor Cameron, Napa Valley's biggest flirt and champion heartbreaker. Taylor just broke up with her most recent girlfriend, and her best friend bet her that she can't make it until Labor Day without sleeping with someone. (Two whole months? Without sex? Taylor?!?!) So, she offers to give Avery flirting lessons. It should keep her busy and stop her from texting people she shouldn't. And it might take her mind off how inadequate she feels compared to her friends, who all seem much more settled and adult than Taylor. At first, Avery is stiff and nervous, but Taylor is patient and encouraging, and soon, Avery looks forward to their weekly lessons. With Taylor's help, Avery finally has the life she always wanted. The only issue is: now she wants Taylor. Their attraction becomes impossible to ignore, despite them both insisting to themselves and everyone else that it isn't serious. When Taylor is forced to confront her feelings for Avery, she doesn't know what to do-and most importantly, if she's already ruined the best thing she's ever had",
                400,
                List.of("Jasmine Guillory")));
        /*
            Martínez, S. (n.d.). Booklist online: Leading book discovery.
            https://www.booklistonline.com/products/9805707
         */
        this.books.add(new Book("9781250759368",
                "Speak to me of home : a novel",
                "What does it mean to call a place home? From #1 New York Times bestselling author Jeanine Cummins comes a deeply felt multigenerational family story On her wedding day in San Juan, Puerto Rico, in 1968, Rafaela Acuña y Daubón has mild misgivings, but she marries Peter Brennan Jr. anyway in a blaze of romantic optimism. She has no way of knowing how dramatically her life will change when she uproots her young family to start over in the American Midwest, unleashing a fleet of disappointments. In the 1980s, against the backdrop of her mother's isolation in St. Louis, Missouri, Rafaela's daughter, Ruth Brennan, wants only to belong. Eager to fit in, Ruth lets go of her language, habits, and childhood memories of Puerto Rico. It's not until decades later when Ruth's own daughter, Daisy, returns to San Juan that her mother and grandmother begin to truly reflect on the choices that have come to define their lives. When a hurricane ravages the island in 2023, leaving Daisy critically injured, Rafaela and Ruth return to the city where it all began. As they gather at Daisy's bedside, we follow them back into the pasts that brought them to this point: we watch as they come of age, fall in love, take risks, and contend with all the heartbreaks, triumphs, and reversals of fortune-both good and bad-that make up a meaningful life. As old memories come to light, so do buried secrets, leaving everyone in the family wondering exactly where it is that they belong. A striking, resonant examination of marriage, family, and identity, Speak to Me of Home is ultimately a story of mothers and daughters that asks: How can three women who share geography and genetics have such wildly different ideas of where they come from? And, more importantly, can they discover a common language to find their way back home?",
                384,
                List.of("Jeanine Cummins")));
        /*
            Roderick, K. (n.d.). Booklist online: Leading book discovery.
            https://www.booklistonline.com/products/9806057
         */
        this.books.add(new Book("9781419753947",
                "The Vanished Kingdom: The war of the maps",
                "Fourteen-year-olds Peter and Sophie fight to save the world of magic from a mysterious group called the League of Maps",
                448,
                List.of("Jonathan Auxier")));

        /*
            Johnson, S. (n.d.). Booklist online: Leading book discovery.
            https://www.booklistonline.com/products/9804349
         */
        this.books.add(new Book("9780593975091",
                "My Name Is Emilia del Valle: A Novel",
                "Allende has created many addictive sagas about the extended del Valle family and their intersections with history and one another. The eponymous Emilia, Allende’s addition to this notable clan, is one adventurous, gutsy woman. The illegitimate daughter of a Chilean aristocrat and the Irish novice nun he seduced, Emilia grows up in San Francisco with her loving stepfather’s support, intrepidly working around gender restrictions. After penning dime novels pseudonymously, she becomes a human-interest columnist for the Daily Examiner and wangles an assignment as international correspondent for the impending Chilean Civil War of 1891, under her own byline. Emilia’s first meeting with her long-lost father in Santiago is quite moving, and her time with the canteen girls who accompany President Balmaceda’s army echoes with their unsung courage. Allende expertly navigates through the violent chaos of battle and how it affects Emilia, whose romantic relationships also showcase her character growth. Fans of Allende’s now-classic Daughter of Fortune (1999) and Portrait in Sepia (2000) will particularly welcome this offering, which is replete with Allende’s customary poetic storytelling.",
                304,
                List.of("Isabel Allende")));

    }

    @Override
    public List<Book> list() {
        return this.books;
    }

    @Override
    public Book find(String key) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }
        return new Book();
    }
}
