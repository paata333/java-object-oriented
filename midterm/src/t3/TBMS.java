package t3;

import java.util.ArrayList;
import java.util.List;

public class TBMS {
    private List<BlogPost> posts;

    public TBMS() {
        posts = new ArrayList<>();
    }

    public void addPost(BlogPost post) {
        posts.add(post);
    }

    public void removePost(BlogPost post) {
        posts.remove(post);
    }

    public void printStorage() {
        for (BlogPost post : posts) {
            System.out.println(post);
            System.out.println();
        }
    }

    /**
     * New Feature: Search Functionality
     * This method allows users to search for blog posts containing a specific keyword
     * in their title or content.
     */
    public void searchPostsByKeyword(String keyword) {
        for (BlogPost post : posts) {
            if (post.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    post.getContent().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(post);
                System.out.println();
            }
        }
    }
}
