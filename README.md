# My Personal Project: BookList

## Description of BookList

What the application will do:
- It will enable the user to add books to several lists (e.g. books to read and book already read)
- The user can remove a book from the library
- The user can see all the books in the library as well books in a list

Who will use it?
- People who want to have a personal database which has records of books they read and books they want to read
Why is this project of interest to me?
- The main reason is that I like reading.
- A couple of years ago I started having the habit of tracking books that I have read, and it’s quite satisfying to see the number of books I have read over the years and how my tastes have changed or stayed the same. 
- However, I have two lists (books to read and books already read) and they're just word document lists with the titles (and date I finished reading).
- It would be really helpful if I was able to merge these two lists into one place (that isn't a super long list in a Word document), and get a recommendation immediately instead of looking through the list then choosing.


## User Stories

- As a user, I want to be able to add books to the database (title, genre/tags, blurb, etc.)
- As a user, I want to be able to view all of my books
- As a user, I want to be able to add books to my lists
- As a user, I want to be able to remove books from my lists
- As a user, I want to be able to view my lists
- As a user, I want to be able to select a list and view the books in the list 
- As a user, I would like the option to load the saved information about my lists and books
- As a user, I would like to save my books and my lists to a database before exiting the application (or choose not to)

## Instructions for Grader

- You can locate my visual component by starting the application
- To load the state of my application from the "Library" file, click on the "Load Library" button
- To save the state of my application to the "Library" file, click on the "Save Library" button
- You can generate the first required event related to adding Xs to a Y by clicking on "Add Book to List", entering all the fields and clicking "Ok"
- You can generate the second required event related to displaying a subset of the Xs by clicking on "Display Books in List" and selecting a list

## Phase 4: Task 2
Thu Dec 01 15:19:52 PST 2022
Added The Great Gatsby
Thu Dec 01 15:19:52 PST 2022
Added The Da Vinci Code
Thu Dec 01 15:19:52 PST 2022
Added Twilight
Thu Dec 01 15:19:52 PST 2022
Added The Lovely Bones
Thu Dec 01 15:20:07 PST 2022
Added Hunger Games
Thu Dec 01 15:20:20 PST 2022
Removed Hunger Games

## Phase 4: Task 3
If I had more time to do the project, I would spend more time making the relationships between the classes clearer.
For example, I would make it more clear whether the app could only open one library or several ones,
and perhaps implement some sort of bidirectional association so that a Book object could only be added to one list at a time.