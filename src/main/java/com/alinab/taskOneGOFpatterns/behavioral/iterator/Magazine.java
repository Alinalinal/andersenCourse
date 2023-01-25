package com.alinab.taskOneGOFpatterns.behavioral.iterator;

public class Magazine implements Collection {

    private String title;
    private String[] articles;

    public Magazine(String title, String[] articles) {
        this.title = title;
        this.articles = articles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getArticles() {
        return articles;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }

    @Override
    public Iterator getIterator() {
        return new ArticleIterator();
    }

    private class ArticleIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            return index < articles.length;
        }

        @Override
        public Object next() {
            return articles[index++];
        }
    }
}
