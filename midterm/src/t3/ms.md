Travel Blog Management System
Overview
The Travel Blog Management System (TBMS) is a Java-based application designed to manage and organize travel blog posts. It allows users to create, store, and display blog entries, each containing a title, author, and content. The system is structured using object-oriented principles, ensuring scalability and maintainability.​

Package Structure
Package Name: t3

Class Descriptions
BlogPost: Represents a single blog post with attributes: title, author, and content. Includes getter and setter methods for each attribute and overrides the toString() method for formatted output.​

TBMS: Manages a collection of BlogPost instances. Provides methods to add, remove, and display blog posts.​

BlogTester: Contains the main method to demonstrate the functionality of the TBMS by creating sample blog posts and performing operations like add, remove, and display.​

New Feature
Search Functionality:​
Scribd

A new method searchPostsByKeyword(String keyword) has been added to the TBMS class. This method allows users to search for blog posts containing a specific keyword in their title or content. It enhances the system's usability by enabling quick retrieval of relevant posts.​

