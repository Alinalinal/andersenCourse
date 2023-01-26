package com.alinab.taskOneGOFpatterns.behavioral.iterator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class Magazine implements Collection {

    String title;
    String[] articles;

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
