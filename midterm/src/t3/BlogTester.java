package t3;

public class BlogTester {
    public static void main(String[] args) {
        BlogPost p1 = new BlogPost("A Journey to Kazbegi", "Luka", "Mountains, snow, and serenity...");
        BlogPost p2 = new BlogPost("Summer in Batumi", "Nino", "Beaches, sunsets, and memories...");

        TBMS tbms = new TBMS();

        tbms.addPost(p1);
        tbms.addPost(p2);

        System.out.println("All Blog Posts:");
        tbms.printStorage();

        System.out.println("Search Results for 'beaches':");
        tbms.searchPostsByKeyword("beaches");
    }
}
