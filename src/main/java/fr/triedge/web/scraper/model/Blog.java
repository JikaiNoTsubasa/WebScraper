package fr.triedge.web.scraper.model;

public class Blog {
    private String title, imageLink, blogLink, author, authorLink, dateString;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getBlogLink() {
        return blogLink;
    }

    public void setBlogLink(String blogLink) {
        this.blogLink = blogLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorLink() {
        return authorLink;
    }

    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("# ").append(title).append('\n');
        sb.append(" -> ").append(dateString).append('\n');
        sb.append(" -> ").append(author).append('\n');
        sb.append(" -> ").append(authorLink).append('\n');
        sb.append(" -> ").append(imageLink).append('\n');
        sb.append(" -> ").append(blogLink).append('\n');
        return sb.toString();
    }
}
